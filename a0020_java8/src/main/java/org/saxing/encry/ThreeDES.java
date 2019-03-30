package org.saxing.encry;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.net.URLEncoder;

/**
 * 3des加密
 *
 * @author saxing 2019/3/29 21:54
 */
@SuppressWarnings({ "restriction" })
public class ThreeDES {

    private static final String IV = "1234567-";
    public static final String KEY = "uatspdbcccgame2014061800";

    /**
     * DESCBC加密
     *
     * @param src
     *            数据源
     * @param key
     *            密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     * @throws Exception
     */
    public static String encryptDESCBC(final String src, final String key) throws Exception {

        // --生成key,同时制定是des还是DESede,两者的key长度要求不同
        final DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        final SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        // --加密向量
        final IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));

        // --通过Chipher执行加密得到的是一个byte的数组,Cipher.getInstance("DES")就是采用ECB模式,cipher.init(Cipher.ENCRYPT_MODE,
        // secretKey)就可以了.
        final Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        final byte[] b = cipher.doFinal(src.getBytes("UTF-8"));

        // --通过base64,将加密数组转换成字符串
        final BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b);
    }

    /**
     * DESCBC解密
     *
     * @param src
     *            数据源
     * @param key
     *            密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     * @throws Exception
     */
    public static String decryptDESCBC(final String src, final String key) throws Exception {
        // --通过base64,将字符串转成byte数组
        final BASE64Decoder decoder = new BASE64Decoder();
        final byte[] bytesrc = decoder.decodeBuffer(src);

        // --解密的key
        final DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        final SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

        // --向量
        final IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));

        // --Chipher对象解密Cipher.getInstance("DES")就是采用ECB模式,cipher.init(Cipher.DECRYPT_MODE,
        // secretKey)就可以了.
        final Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        final byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte);

    }

    // 3DESECB加密,key必须是长度大于等于 3*8 = 24 位哈
    public static String encryptThreeDESECB(final String src, final String key) throws Exception {
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        final byte[] b = cipher.doFinal(src.getBytes());

        final BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");

    }

    // 3DESECB解密,key必须是长度大于等于 3*8 = 24 位哈
    public static String decryptThreeDESECB(final String src, final String key) throws Exception {
        // --通过base64,将字符串转成byte数组
        final BASE64Decoder decoder = new BASE64Decoder();
        final byte[] bytesrc = decoder.decodeBuffer(src);
        // --解密的key
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        // --Chipher对象解密
        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        final byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte);
    }

    public static long timeIntecept(){
        long current = System.currentTimeMillis();
        System.out.println(current);
        return (current+12)*7+234;
    }

    public static void main2(String[] args) throws Exception {
        final String key = "61990364-80ae-45da-b0d6-29bbb123ddbb";
        // 加密流程
        String telePhone = String.valueOf(timeIntecept());
        String telePhone_encrypt = "";
        telePhone_encrypt = encryptThreeDESECB(URLEncoder.encode(telePhone, "UTF-8"), key);
        System.out.println(telePhone_encrypt);// nWRVeJuoCrs8a+Ajn/3S8g==

        // 解密流程
        String tele_decrypt = decryptThreeDESECB(telePhone_encrypt, key);
        System.out.println("模拟代码解密:" + tele_decrypt);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            telePhone_encrypt = encryptThreeDESECB(URLEncoder.encode(telePhone, "UTF-8"), key);
        }
        long end = System.currentTimeMillis();
        System.out.println("加密时间： " + (end -  start));

        for (int i = 0; i < 1000; i++) {
            tele_decrypt = decryptThreeDESECB(telePhone_encrypt, key);
        }

        long end2 = System.currentTimeMillis();
        System.out.println("解密时间： " + (end2 - end));

        System.out.println("解密结果： " + tele_decrypt);
        long res = 0;
        if (StringUtils.isNumeric(tele_decrypt)){
            res = ((Long.valueOf(tele_decrypt) - 234)/7-12);
        }
        System.out.println("最终结果： " + res);

        Thread.sleep(2000);

        long current = System.currentTimeMillis();
        long abs = Math.abs(current - res);
        System.out.println("abs: " + abs);
        abs = Math.abs(res - current);
        System.out.println("abs: " + abs);

        if (abs < 2000){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }


    /**
     * MD5方法
     * 669ms / 100000次
     */
    public static String md5_2(String text, String key) {
        String k = "78cdabb3-5a8f-4899-9214-cf37b6e7caab";
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(text + key + k);
    }

    /**
     * MD5方法
     * 197ms / 100000次
     */
    public static String md5(String text, String key) {
        String k = "78cdabb3-5a8f-4899-9214-cf37b6e7caab";
        String r =DigestUtils.md5DigestAsHex(text.getBytes());
        return r;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            md5("fds;lkvcx;ljkflwjelfkvjcxlkfjlaksvmcxlkjflsdkjlkvcfdsvcxdf", "fff");
        }
        String result = "";
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            result = md5("fds;lkvcx;ljkflwjelfkvjcxlkfjlaksvmcxlkjflsdkjlkvcfdsvcxdf", "fff");
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);

        System.out.println(result);

    }

}
