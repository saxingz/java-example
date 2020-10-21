package org.saxing.a0041_wemedia.logic.subtitles.file_validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
@Getter
public class OutputFileInfo {
    private File file;

    private OutputFileNotValidReason notValidReason;
}
