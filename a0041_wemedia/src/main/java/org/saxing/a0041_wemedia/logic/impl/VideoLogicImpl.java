package org.saxing.a0041_wemedia.logic.impl;

import cn.hutool.core.lang.UUID;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.collections4.CollectionUtils;
import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import org.saxing.a0041_wemedia.mapper.VideoMapper;
import org.saxing.a0041_wemedia.logic.IVideoLogic;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.saxing.a0041_wemedia.process.ProcessException;
import org.saxing.a0041_wemedia.process.ProcessRunner;
import org.springframework.stereotype.Service;

import java.io.*;
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
    private static final String BASE_PATH = "F:\\premiere_project\\";
    // youtube dl path
    private static final String YOUTUBE_DL_PATH = "F:\\premiere_project\\0youtube-dl\\youtube-dl.exe";


    @Override
    public Boolean downloadVideo(Long id) throws IOException {
        VideoDO videoDO = this.getById(id);
        if (Objects.isNull(videoDO)) {
            return false;
        }
        // 判断目录下是否有数据
        return true;
    }

//    F:\premiere_project\0youtube-dl
    public static void main(String[] args) throws InterruptedException, ProcessException, IOException {
        Long id = 1L;
        String videoId = "jnMfNYNQRUU";
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

        // 校验下载项
        if ((Objects.isNull(enVtt)
                || Objects.isNull(zhHansvtt)
                || Objects.isNull(originVideoFile))
                && !isFirstNull
        ) {
            // 删除改名这个文件夹，重新下载
            new File(videoPathStr).renameTo(new File(videoPathStr + "-" + UUID.fastUUID().toString()));
            if (!Files.exists(videoPath)) {
                Files.createDirectories(videoPath);
            }
        }
        // 校验下载项
        if (Objects.isNull(enVtt)
                || Objects.isNull(zhHansvtt)
                || Objects.isNull(originVideoFile)
        ) {
            String outputFile = videoPathStr + "\\%(title)s.%(ext)s";
            // 删除改名这个文件夹，重新下载
            List<String> arguments = new ArrayList<>(
                    Arrays.asList(
                            YOUTUBE_DL_PATH,
                            "--write-sub",
                            "--write-auto-sub",
                            "--sub-lang",
                            "en,zh-Hans",
                            "--sub-format",
                            "vtt",
                            "--no-check-certificate",
                            "-o",
                            "\"" + outputFile + "\"",
                            "--proxy",
                            "socks5://127.0.0.1:10808/",
                            "https://www.youtube.com/watch?v=" + videoId
                    )
            );
            ProcessRunner.run(arguments);
            return;
        }

        // 校验下载项
        if (Objects.isNull(enVtt)
                || Objects.isNull(zhHansvtt)
                || Objects.isNull(originVideoFile)
        ) {
            // 下载失败，返回
            log.error("下载失败，返回");
            return;
        } else {
            log.info("下载成功");
        }

        List<String> arg2 = new ArrayList<>(
                Arrays.asList(
                        YOUTUBE_DL_PATH,
                        "./youtube-dl.exe --write-sub --write-auto-sub --sub-lang en,zh-Hans " +
                                "--sub-format vtt " +
                                "--no-check-certificate " +
                                "--proxy socks5://127.0.0.1:10808/  " +
                                "https://www.youtube.com/watch?v=jnMfNYNQRUU"
                )
        );
//        ProcessRunner.run(arguments);
    }

    private static void youtubeDl(String videoId, MediaType type) throws InterruptedException, ProcessException {
        switch (type) {
            case EN_SUBTITLE:

                break;
            case ZH_SUBTITLE:
                break;
            case VIDEO:
                break;
            case AUDIO:
                break;
            default:
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
