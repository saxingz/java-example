package org.saxing.a0041_wemedia.logic.impl;

import cn.hutool.core.lang.UUID;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import org.saxing.a0041_wemedia.domain.enums.DownLoadStatus;
import org.saxing.a0041_wemedia.logic.subtitles.SubFromStart;
import org.saxing.a0041_wemedia.logic.subtitles.SubRipWriter;
import org.saxing.a0041_wemedia.logic.subtitles.SubtitleMerger;
import org.saxing.a0041_wemedia.logic.subtitles.entities.FileOrigin;
import org.saxing.a0041_wemedia.logic.subtitles.entities.Subtitles;
import org.saxing.a0041_wemedia.mapper.VideoMapper;
import org.saxing.a0041_wemedia.logic.IVideoLogic;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.saxing.a0041_wemedia.process.ProcessException;
import org.saxing.a0041_wemedia.process.ProcessRunner;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 原始视频表 服务实现类
 * </p>
 *
 * @author saxing
 * @since 2020-10-18
 */
@Service
@CommonsLog
public class VideoLogicImpl extends ServiceImpl<VideoMapper, VideoDO> implements IVideoLogic {

    // base path
    private static final String BASE_PATH = "D:\\premiere_project\\";
    // youtube dl path
    private static final String YOUTUBE_DL_PATH = BASE_PATH + "0youtube-dl\\youtube-dl.exe";
    // ffmpeg path
    private static final String FFMPEG_PATH = BASE_PATH + "0ffmpeg\\ffmpeg\\bin\\ffmpeg.exe";


    @Override
    public Boolean downloadVideo(Long id) throws IOException {
        VideoDO videoDO = this.getById(id);
        if (Objects.isNull(videoDO)) {
            return false;
        }
        String videoId = videoDO.getVideoId();
        String videoPathStr = BASE_PATH + id;
        Path videoPath = Paths.get(videoPathStr);
        boolean isFirstNull = false;
        if (!Files.exists(videoPath)) {
            isFirstNull = true;
            Files.createDirectories(videoPath);
        }
        List<Path> fileList = Files.list(videoPath).collect(Collectors.toList());
        // 英文字幕
        Path enVtt = fileList.stream().filter(p -> p.getFileName().toString().endsWith("en.vtt"))
                .findFirst().orElse(null);
        // 中文字幕
        Path zhHansvtt = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".zh-Hans.vtt"))
                .findFirst().orElse(null);
        // 原始视频文件
        Path originVideoFile = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".mp4"))
                .findFirst().orElse(null);
        // 原始音频文件
        Path originAudioFile = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".webm")
                || p.getFileName().toString().endsWith(".m4a")).findFirst().orElse(null);
        // 校验下载项
        if ((Objects.isNull(enVtt)
                || Objects.isNull(zhHansvtt)
                || Objects.isNull(originVideoFile)
                || Objects.isNull(originAudioFile))
                && !isFirstNull
        ) {
            // 如果文件不为空，改名这个文件夹，重新下载
            if (Files.list(videoPath).count() > 0) {
                new File(videoPathStr).renameTo(new File(videoPathStr + "-" + UUID.fastUUID().toString()));
                if (!Files.exists(videoPath)) {
                    Files.createDirectories(videoPath);
                }
            }
        }
        // 下载
        if (Objects.isNull(enVtt)
                || Objects.isNull(zhHansvtt)
                || Objects.isNull(originVideoFile)
                || Objects.isNull(originAudioFile)
        ) {
            String outputFile = videoPathStr + "\\%(title)s.%(ext)s";
            // 重新下载
            List<String> arguments = new ArrayList<>(
                    Arrays.asList(
                            YOUTUBE_DL_PATH,
                            "--write-sub",
                            "--write-auto-sub",
                            "--sub-lang",
                            "en,zh-Hans",
                            "--sub-format",
                            "vtt",
                            "-f",
                            "\"bestvideo,bestaudio\"",
                            "--no-check-certificate",
                            "-o",
                            "\"" + outputFile + "\"",
                            "--proxy",
                            "socks5://127.0.0.1:10808/",
                            "https://www.youtube.com/watch?v=" + videoId
                    )
            );
            try {
                ProcessRunner.run(arguments);
            } catch (ProcessException | InterruptedException e) {
                e.printStackTrace();
                log.error(e.toString());
            }
        }

        // 校验下载项
        if (Objects.isNull(enVtt)
                || Objects.isNull(zhHansvtt)
                || Objects.isNull(originVideoFile)
                || Objects.isNull(originAudioFile)
        ) {
            // 下载失败，返回
            log.error("下载失败，返回");
            return false;
        } else {
            log.info("下载成功");
            videoDO.setDownloadStatus(DownLoadStatus.DOWNLOADED.getCode());
            this.saveOrUpdate(videoDO);
            return true;
        }
    }

    @Override
    public Boolean rebuild(Long id) throws IOException {
        VideoDO videoDO = this.getById(id);
        if (Objects.isNull(videoDO) || !Objects.equals(videoDO.getDownloadStatus(), DownLoadStatus.DOWNLOADED.getCode())) {
            log.error("视频不存在或未下载, video表id: " + id);
            return false;
        }
        String videoPathStr = BASE_PATH + id;
        Path videoPath = Paths.get(videoPathStr);
        if (!Files.exists(videoPath)) {
            Files.createDirectories(videoPath);
        }
        List<Path> fileList = Files.list(videoPath).collect(Collectors.toList());
        // 英文字幕
        Path enVtt = fileList.stream().filter(p -> p.getFileName().toString().endsWith("en.vtt"))
                .findFirst().orElse(null);
        // 中文字幕
        Path zhHansvtt = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".zh-Hans.vtt"))
                .findFirst().orElse(null);
        // 原始视频文件
        Path originVideoFile = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".mp4"))
                .findFirst().orElse(null);
        // 原始音频文件
        Path originAudioFile = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".webm")
                || p.getFileName().toString().endsWith(".m4a")).findFirst().orElse(null);
        if (Objects.isNull(originAudioFile)) {
            log.error("没有音频文件");
            return false;
        }
        // 1. convert to mp3
        convertToMp3(originAudioFile);
        // 2. subtitle vtt -> srt
        convertToSrt(zhHansvtt, "zh");
        convertToSrt(enVtt, "en");
        // 3. merge subtitle
        Path enSrt = fileList.stream().filter(p -> p.getFileName().toString().endsWith("en.srt"))
                .findFirst().orElse(null);
        Path zhSrt = fileList.stream().filter(p -> p.getFileName().toString().endsWith("zh.srt"))
                .findFirst().orElse(null);
        if (Objects.isNull(enSrt) || Objects.isNull(zhSrt)) {
            log.error("字幕不全");
            return false;
        }





        return null;
    }

    public static void main(String[] args) {
        Path enVtt = Paths.get("D:\\premiere_project\\1\\en.srt");
        Path zhVtt = Paths.get("D:\\premiere_project\\1\\zh.srt");

    }

    /**
     * 转换字幕格式
     */
    public static void convertToSrt(Path origin, String tag) {
        System.out.println(origin);
        List<String> arguments = new ArrayList<>(
                Arrays.asList(
                        FFMPEG_PATH,
                        "-i",
                        origin.toString(),
                        origin.getParent().toString() + "\\" + origin.getFileName().toString() + "." + tag + ".srt"
                )
        );
        try {
            ProcessRunner.run(arguments);
        } catch (ProcessException | InterruptedException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    /**
     * 合并字幕
     * @param en
     * @throws InterruptedException
     * @throws IOException
     */
    public static void mergeSubtitle(Path en) throws InterruptedException, IOException {
        SubFromStart.InputSubtitlesInfo up = new SubFromStart()
                .getInputSubtitlesInfo("D:\\temp\\temp1\\en.srt", SubFromStart.InputSubtitlesType.UPPER, FileOrigin.TEXT_FIELD);
        SubFromStart.InputSubtitlesInfo low = new SubFromStart()
                .getInputSubtitlesInfo("D:\\temp\\temp1\\zh.srt", SubFromStart.InputSubtitlesType.LOWER, FileOrigin.TEXT_FIELD);
        SubFromStart.MergedSubtitlesFileInfo mergedSubtitlesFileInfo =
                SubFromStart.getMergedSubtitlesFileInfo("D:\\temp\\temp1\\mixed.srt", FileOrigin.TEXT_FIELD);


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

    /**
     * conver to mp3
     * @param mp3Path
     */
    public static void convertToMp3(Path mp3Path) {
        System.out.println(mp3Path);
        List<String> arguments = new ArrayList<>(
                Arrays.asList(
                        FFMPEG_PATH,
                        "-i",
                        mp3Path.toString(),
                        "-acodec",
                        "libmp3lame",
                        mp3Path.getParent().toString() + "\\" + mp3Path.getFileName().toString() + ".mp3"
                )
        );
        try {
            ProcessRunner.run(arguments);
        } catch (ProcessException | InterruptedException e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }


    enum MediaType{
        // 英文字幕
        EN_SUBTITLE,
        // 中文字幕
        ZH_SUBTITLE,
        // 视频
        VIDEO,
        // 音频
        AUDIO
    }
}
