package org.saxing.qisi.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * image utils
 *
 * Created by saxing on 2018/5/6.
 */
public class ImageUtils {

    /**
     * save
     * @param multipartFile
     * @param path
     * @return
     */
    public static String saveImg(MultipartFile multipartFile, String path, String fileName) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1){
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

    /**
     * get suffix
     *
     * @param fileOriginName
     * @return
     */
    public static String getSuffix(String fileOriginName) {
        String[] words = fileOriginName.split("\\.");
        return words[words.length - 1];
    }

    /**
     * compress
     */
    public static void compressPic(String originalFile) throws IOException {
        Thumbnails.of(originalFile)
                .scale(1f).outputQuality(.5f).toFile(originalFile);
    }
}
