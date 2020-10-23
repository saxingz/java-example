package org.saxing.a0041_wemedia.logic.impl;

import cn.hutool.core.lang.UUID;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.saxing.a0041_wemedia.domain.entity.VideoDO;
import org.saxing.a0041_wemedia.domain.enums.DownLoadStatus;
import org.saxing.a0041_wemedia.domain.enums.RebuildStatus;
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
    private static final String BASE_PATH = "F:\\premiere_project\\";
    // youtube dl path
    private static final String YOUTUBE_DL_PATH = BASE_PATH + "0youtube-dl\\youtube-dl.exe";
    // ffmpeg path
    private static final String FFMPEG_PATH = BASE_PATH + "0ffmpeg\\ffmpeg\\bin\\ffmpeg.exe";
    // water mark path
    private static final String WATER_LOGO_PATH = BASE_PATH + "0basic\\0watermark\\watermark.png";
    // video head path
    private static final String VIDEO_HEAD_PATH = BASE_PATH + "0basic\\0head\\mylogo\\head1.ts";
    // video tail path
    private static final String VIDEO_TAIL_PATH = BASE_PATH + "0basic\\0tail\\three.ts";



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

        // 英文字幕
        enVtt = fileList.stream().filter(p -> p.getFileName().toString().endsWith("en.vtt"))
                .findFirst().orElse(null);
        // 中文字幕
        zhHansvtt = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".zh-Hans.vtt"))
                .findFirst().orElse(null);
        // 原始视频文件
        originVideoFile = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".mp4"))
                .findFirst().orElse(null);
        // 原始音频文件
        originAudioFile = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".webm")
                || p.getFileName().toString().endsWith(".m4a")).findFirst().orElse(null);

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
        log.info("1. convert to mp3");
        convertToMp3(originAudioFile);
        // 2. subtitle vtt -> srt
        log.info("2. subtitle vtt -> srt");
        convertToSrt(zhHansvtt, "zh");
        convertToSrt(enVtt, "en");
        // 3. merge subtitle
        log.info("3. merge subtitle");
        fileList = Files.list(videoPath).collect(Collectors.toList());
        Path enSrt = fileList.stream().filter(p -> p.getFileName().toString().endsWith("en.srt"))
                .findFirst().orElse(null);
        Path zhSrt = fileList.stream().filter(p -> p.getFileName().toString().endsWith("zh.srt"))
                .findFirst().orElse(null);
        if (Objects.isNull(enSrt) || Objects.isNull(zhSrt)) {
            log.error("字幕不全");
            return false;
        }
        try {
            mergeSubtitle(enSrt, zhSrt);
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("字幕合并失败");
        }
        // 4. merge subtitle watermark video
        log.info("4. merge subtitle watermark video");
        fileList = Files.list(videoPath).collect(Collectors.toList());
        Path mixedSrt = fileList.stream().filter(p -> p.getFileName().toString().endsWith("mixed.srt"))
                .findFirst().orElse(null);
        mergeSubtitleAndVideo(originVideoFile, mixedSrt);
        // 5. merge video audio
        log.info("5. merge video audio");
        fileList = Files.list(videoPath).collect(Collectors.toList());
        Path subtitleVideo = fileList.stream().filter(p -> p.getFileName().toString().endsWith("subtitle.mp4"))
                .findFirst().orElse(null);
        Path mp3Audio = fileList.stream().filter(p -> p.getFileName().toString().endsWith(".mp3"))
                .findFirst().orElse(null);
        if (Objects.isNull(subtitleVideo) || Objects.isNull(mp3Audio)) {
            log.error("subtitleVideo 或 mp3Audio 不存在");
            return false;
        }
        mergeVideoAndAudio(subtitleVideo, mp3Audio);
        // 6. gen ts
        log.info("6. gen ts");
        genTs(subtitleVideo);
        // 7. merge head and tail
        log.info("7. merge head and tail");
        fileList = Files.list(videoPath).collect(Collectors.toList());
        Path tsVideo = fileList.stream().filter(p -> p.getFileName().toString().endsWith("video.ts"))
                .findFirst().orElse(null);
        if (Objects.isNull(tsVideo)) {
            log.error("tsVideo生成失败");
            return false;
        }
        mergeHeadTail(tsVideo, Paths.get(VIDEO_HEAD_PATH), Paths.get(VIDEO_TAIL_PATH));
        // 8. del ts
        log.info("8. del ts");
        new File(tsVideo.toUri()).delete();
        // 9. 校验生成视频
        log.info("9. 校验生成视频");
        fileList = Files.list(videoPath).collect(Collectors.toList());
        Path finalVideo = fileList.stream().filter(p -> p.getFileName().toString().endsWith("final.mp4"))
                .findFirst().orElse(null);
        if (Objects.isNull(finalVideo)) {
            log.error("视频最后生成失败");
            return false;
        }
        // 标记生成成功
        videoDO.setRebuildStatus(RebuildStatus.REBUILDED.getCode());
        this.updateById(videoDO);
        log.info("视频生成成功！！！");
        return true;
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        Path tsVideo = Paths.get("F:\\premiere_project\\1\\video.ts");
//        Path head = Paths.get(VIDEO_HEAD_PATH);
//        Path tail = Paths.get(VIDEO_TAIL_PATH);
//        mergeHeadTail(tsVideo, head, tail);
//    }

    /**
     * 生成ts
     * /ffmpeg -i head1.mp4 -c copy -bsf:v h264_mp4toannexb head1.ts
     */
    private static void genTs(Path video) {
        List<String> arguments = new ArrayList<>(
                Arrays.asList(
                        FFMPEG_PATH,
                        "-i",
                        video.toString(),
                        "-c",
                        "copy",
                        "-bsf:v",
                        "h264_mp4toannexb",
                        video.getParent().toString() + "\\" + "video.ts"
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
     * 拼接片头片尾
     *  //视频尺寸要一致
     *     ffmpeg -i concat:"1.mpg|2.mpg|3.mpg" -c copy output.mp4
     *
     *     ffmpeg -i video1.mp4 -i video2.flv -filter_complex \
     * "[0:v][0:a][1:v][1:a] concat=n=2:v=1:a=1 [outv] [outa]" \
     * -map "[outv]" -map "[outa]" out.mp4
     *
     *
     * // use libx265 video and aac audio codec
     * ffmpeg -i inputFile.avi -c:v libx265 -c:a aac outputFile.mp4
     * ffmpeg -i inputFile.avi -c:v libx265 -c:a aac -b:v 7000k -b:a 320k outputFile.mp4      // with higher bitrates
     *
     * // use vp9 video and mp3 audio codec
     * ffmpeg -i inputFile.avi -c:v vp9 -c:a libmp3lame outputFile.mp4
     * ffmpeg -i inputFile.avi -c:v vp9 -c:a libmp3lame -b:v 7000k -b:a 320k outputFile.mp4    // with higher bitrates
     *
     * /**
     *  * -i => Input file
     *  * -c:v => Video codec to use. (alternatively, use option -vcodec)
     *  * -c:a => Audio codec to use. (alternatively, use option -acodec)
     *  * -b:v => Bitrate of the Video codec. [in kbps(k) or mbps(m)]
     *  * -b:a => Bitrate of the Audio codec. [in kbps(k)]
     *  https://digitalfortress.tech/tricks/encode-videos-with-ffmpeg/
     *  Note: Sometimes, this might not work as expected with certain formats (like MP4 files) when the files to be joined have been encoded differently or have a different resolution. In this case, you will be required to convert each input file into an intermediate format and then join those intermediate files together.
     *
     * // convert each file
     * ffmpeg -i file1.mp4 -c copy -bsf:v h264_mp4toannexb temp1.ts
     * ffmpeg -i file2.mp4 -c copy -bsf:v h264_mp4toannexb temp2.ts
     * // Then join the intermediate files
     * ffmpeg -i "concat:temp1.ts|temp2.ts" -c copy -bsf:a aac_adtstoasc output.mp4
     *
     *  * -i => Input file
     *  * -c => codecs (audio+video)
     *  * -bsf:v => Bitstream filter for video
     *  * -bsf:a => Bitstream filter for audio
     *
     *  ./ffmpeg -i 1.mp4 -i 2.mp4 -i 3.mp4 \
     * -filter_complex "[0:v:0][0:a:0][1:v:0][1:a:0][2:v:0][2:a:0]concat=n=3:v=1:a=1[outv][outa]" \
     * -map "[outv]" -map "[outa]" out80.mp4
     *
     * http://trac.ffmpeg.org/wiki/Concatenate
     *
     * ./ffmpeg -i head1.mp4 -c copy -bsf:v h264_mp4toannexb head1.ts
     * ./ffmpeg -i 1.mp4 -c copy -bsf:v h264_mp4toannexb 1.ts
     * ./ffmpeg -i three.mp4 -c copy -bsf:v h264_mp4toannexb three1.ts
     *
     * ./ffmpeg -i "concat:head1.ts|1.ts|three1.ts" -c copy -bsf:a aac_adtstoasc out88.mp4
     */
    private static void mergeHeadTail(Path video, Path head, Path tail) {


        List<String> arguments = new ArrayList<>(
                Arrays.asList(
                        FFMPEG_PATH,
                        "-i",
                        "\"concat:" + head.toString() + "|" + video.toString() + "|" + tail.toString() + "\"",
                        "-c",
                        "copy",
                        "-bsf:a",
                        "aac_adtstoasc",
                        video.getParent().toString() + "\\" + "final.mp4"
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
     * 合并音频
     * D:/saprogram/ffmpeg -i F:\premiere_project\1\Part1.mp4 -i F:/premiere_project/1/Part1.mp3 -map 0 -map 1 -c:v copy -c:a copy -c:s copy -y F:\premiere_project\1\Part1_封装版.mp4
     * @param video
     * @param audio
     */
    private static void mergeVideoAndAudio(Path video, Path audio) {
        List<String> arguments = new ArrayList<>(
                Arrays.asList(
                        FFMPEG_PATH,
                        "-i",
                        video.toString(),
                        "-i",
                        audio.toString(),
                        "-map",
                        "0",
                        "-map",
                        "1",
                        "-c:v",
                        "copy",
                        "-c:a",
                        "copy",
                        "-y",
                        video.getParent().toString() + "\\" + video.getFileName().toString() + ".audio.mp4"
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
     * 合并字幕和视频
     * ./ffmpeg -i Part1.mp4 -vf subtitles=zh.srt sub-2.mp4
     * 加水印
     * ./ffmpeg -i Part1.mp4 -i watermark.png -filter_complex 'overlay=x=300:y=main_h-overlay_h-300' part1_9.mp4
     *
     * 同时水印和字幕
     * ./ffmpeg -i Part1.mp4 -i watermark.png  -filter_complex "[0:0][1:0]overlay=main_w-overlay_w-300:300,subtitles=zh.srt"  -c:v h264  -c:a copy sub-5.mp4
     * ./ffmpeg -i Part1.mp4 -i watermark.png  -filter_complex "[0:0][1:0]overlay=x=300:y=main_h-overlay_h-300,subtitles=zh.srt"  -c:v h264  -c:a copy sub-6.mp4
     */
    public static void mergeSubtitleAndVideo(Path video, Path subtitle) {
        System.out.println(video);
        System.out.println(subtitle);

        Path root = subtitle.getRoot();
        String rootChar = root.toString().substring(0, 1);
        String backPath = subtitle.toString()
                .substring(subtitle.toString()
                        .indexOf(subtitle.getRoot().toString()) + subtitle.getRoot().toString().length() - 1);
        backPath = backPath.replaceAll("\\\\", "/");

        List<String> arguments = new ArrayList<>(
                Arrays.asList(
                        FFMPEG_PATH,
                        "-i",
                        video.toString(),
                        "-i",
                        WATER_LOGO_PATH,
                        "-filter_complex",
//                        "[0:0][1:0]overlay=x=300:y=main_h-overlay_h-300,subtitles='F\\:/premiere_project/1/zh.srt'",
                        "[0:0][1:0]overlay=x=300:y=main_h-overlay_h-300,subtitles='" + rootChar + "\\:" + backPath + "'",
                        "-c:v",
                        "h264",
                        "-c:a",
                        "copy",
                        video.getParent().toString() + "\\" + video.getFileName().toString() + ".subtitle.mp4"
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
    public static void mergeSubtitle(Path en, Path zh) throws InterruptedException, IOException {
        SubFromStart.InputSubtitlesInfo up = new SubFromStart()
                .getInputSubtitlesInfo(en.toString(), SubFromStart.InputSubtitlesType.UPPER, FileOrigin.TEXT_FIELD);
        SubFromStart.InputSubtitlesInfo low = new SubFromStart()
                .getInputSubtitlesInfo(zh.toString(), SubFromStart.InputSubtitlesType.LOWER, FileOrigin.TEXT_FIELD);
        SubFromStart.MergedSubtitlesFileInfo mergedSubtitlesFileInfo =
                SubFromStart.getMergedSubtitlesFileInfo(en.getParent() + "\\mixed.srt", FileOrigin.TEXT_FIELD);


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
