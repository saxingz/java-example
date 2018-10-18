package org.saxing.syspro;

import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.EncodedKeySpec;
import java.sql.SQLOutput;

public class Passtest {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {


        System.out.println(
//                DigestUtils.sha1Hex("adfasdf123456dsayouyoucheckyoudf;a")
//                DigestUtils.sha1Hex("RkUqI8wTqaz@1234dsayouyoucheckyoudf;a")
                DigestUtils.sha1Hex("mSumFx2Vqaz@1234dsayouyoucheckyoudf;a")
        );

//        System.out.println(EncoderByMd5("qaz@1234"));


        //mSumFx2V$c87f28753a3685ad97b424580b19c560de1e6f99
    }

    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest sha1=MessageDigest.getInstance("SHA1");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(sha1.digest((str+"mSumFx2Vdsayouyoucheckyoudf;").getBytes()));
        return newstr;
    }

}
