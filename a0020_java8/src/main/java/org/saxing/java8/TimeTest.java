package org.saxing.java8;

import org.springframework.util.DigestUtils;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;

public class TimeTest {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String str = "java.security.NoSuchAlgorithmException21432534534142";


        byte[] md5s = MessageDigest.getInstance("MD5").digest();
        System.out.println(new String(md5s));

        byte[] bytes = DigestUtils.md5Digest(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(bytes);

        String base64 = Base64.getMimeEncoder().encodeToString(str.getBytes());
        System.out.println(base64);

        String base642 = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println(base642);




        String sha256Hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(str);
        System.out.println(sha256Hex);


        String resultMd5 = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(resultMd5);

    }

    public static void main2(String[] args) {

        Long current = System.currentTimeMillis();
        // 1596789092056
        // 1600833600000
        System.out.println(current);


        Long dateTime = new Date().getTime();
        // 1596789092057
        System.out.println(dateTime);

        // 1592536526
        //
        // 1596777250822


        LocalDateTime localDateTime = LocalDateTime.now();
        long currentSecond = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        LocalDateTime past8Minutes = localDateTime.minusMinutes(8);
        long pastTimeSecond = past8Minutes.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();

        System.out.println(currentSecond);
        System.out.println(pastTimeSecond);
    }

}
