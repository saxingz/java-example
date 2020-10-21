package org.saxing.a0041_wemedia.logic.subtitles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.Nullable;
import org.saxing.a0041_wemedia.logic.subtitles.entities.*;
import org.saxing.a0041_wemedia.logic.subtitles.file_validation.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@CommonsLog
public class SubFromStart {

    private InputSubtitlesInfo upperSubtitlesInfo;
    private InputSubtitlesInfo lowerSubtitlesInfo;

    public static void main(String[] args) throws InterruptedException, IOException {
        InputSubtitlesInfo up = new SubFromStart().getInputSubtitlesInfo("F:\\temp\\en.srt", InputSubtitlesType.UPPER, FileOrigin.TEXT_FIELD);
        InputSubtitlesInfo low = new SubFromStart().getInputSubtitlesInfo("F:\\temp\\zh.srt", InputSubtitlesType.LOWER, FileOrigin.TEXT_FIELD);
        MergedSubtitlesFileInfo mergedSubtitlesFileInfo = getMergedSubtitlesFileInfo("F:\\temp\\mixed.srt", FileOrigin.TEXT_FIELD);


        Subtitles subtitles = SubtitleMerger.mergeSubtitles(
                up.getSubtitles(),
                low.getSubtitles()
        );
        FileUtils.writeStringToFile(
                mergedSubtitlesFileInfo.getFile(),
                SubRipWriter.toText(subtitles, true),
                StandardCharsets.UTF_8
        );
    }

    @Nullable
    private InputSubtitlesInfo getInputSubtitlesInfo(
            String path,
            InputSubtitlesType subtitleType,
            FileOrigin fileOrigin
    ) {
        InputFileValidationOptions validationOptions = InputFileValidationOptions.builder()
                .allowedExtensions(SubtitleFormat.SUB_RIP.getExtensions())
                .allowEmpty(false)
                .maxAllowedSize(LogicConstants.INPUT_SUBTITLE_FILE_LIMIT_MEGABYTES * 1024 * 1024L)
                .loadContent(true)
                .build();
        InputFileInfo fileInfo = FileValidator.getInputFileInfo(path, validationOptions);
        if (fileInfo.getNotValidReason() == InputFileNotValidReason.PATH_IS_EMPTY) {
            return null;
        }

        if (fileInfo.getNotValidReason() != null) {
            return new InputSubtitlesInfo(
                    path,
                    fileInfo.getFile(),
                    fileOrigin,
                    fileInfo.getNotValidReason(),
                    isDuplicate(fileInfo.getFile(), subtitleType),
                    null
            );
        }

        InputSubtitlesInfo inputSubtitlesInfo = new InputSubtitlesInfo(
                path,
                fileInfo.getFile(),
                fileOrigin,
                null,
                isDuplicate(fileInfo.getFile(), subtitleType),
                SubtitlesAndInput.from(fileInfo.getContent(), StandardCharsets.UTF_8)
        );

        return inputSubtitlesInfo;
    }

    private boolean isDuplicate(File file, InputSubtitlesType subtitleType) {
        InputSubtitlesInfo otherSubtitlesInfo;
        if (subtitleType == InputSubtitlesType.UPPER) {
            if (lowerSubtitlesInfo == null) {
                return false;
            }

            otherSubtitlesInfo = lowerSubtitlesInfo;
        } else if (subtitleType == InputSubtitlesType.LOWER) {
            if (upperSubtitlesInfo == null) {
                return false;
            }

            otherSubtitlesInfo = upperSubtitlesInfo;
        } else {
            log.error("unexpected subtitle type: " + subtitleType + ", most likely a bug");
            throw new IllegalStateException();
        }

        return Objects.equals(file, otherSubtitlesInfo.getFile());
    }

    @Nullable
    private static MergedSubtitlesFileInfo getMergedSubtitlesFileInfo(String path, FileOrigin fileOrigin) {
        OutputFileValidationOptions validationOptions = new OutputFileValidationOptions(
                SubtitleFormat.SUB_RIP.getExtensions(),
                true
        );
        OutputFileInfo validatorFileInfo = FileValidator.getOutputFileInfo(path, validationOptions);
        if (validatorFileInfo.getNotValidReason() == OutputFileNotValidReason.PATH_IS_EMPTY) {
            return null;
        }

        return new MergedSubtitlesFileInfo(
                path,
                validatorFileInfo.getFile(),
                fileOrigin,
                validatorFileInfo.getNotValidReason()
        );
    }

    @AllArgsConstructor
    @Getter
    private static class InputSubtitlesInfo {
        private String path;

        private File file;

        private FileOrigin fileOrigin;

        private InputFileNotValidReason notValidReason;

        @Setter
        private boolean isDuplicate;

        @Setter
        private SubtitlesAndInput subtitlesAndInput;

        @Nullable
        Subtitles getSubtitles() {
            return subtitlesAndInput != null ? subtitlesAndInput.getSubtitles() : null;
        }

        @Nullable
        Charset getEncoding() {
            return subtitlesAndInput != null ? subtitlesAndInput.getEncoding() : null;
        }

        boolean incorrectFormat() {
            return subtitlesAndInput != null && !subtitlesAndInput.isCorrectFormat();
        }
    }

    @AllArgsConstructor
    @Getter
    private enum InputSubtitlesType {
        UPPER(SubtitleType.UPPER),

        LOWER(SubtitleType.LOWER);

        private SubtitleType broaderType;
    }

    @AllArgsConstructor
    @Getter
    private static class MergedSubtitlesFileInfo {
        private String path;

        private File file;

        private FileOrigin fileOrigin;

        private OutputFileNotValidReason notValidReason;
    }

    private enum SubtitleType {
        UPPER,
        LOWER,
        MERGED
    }
}
