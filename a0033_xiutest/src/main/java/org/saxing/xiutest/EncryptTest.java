package org.saxing.xiutest;

import javax.crypto.BadPaddingException;
import java.util.Base64;

public class EncryptTest {


    public static void main(String[] args) throws BadPaddingException {
        String str = "FUfDno1wCDqEijTB+3xyVvdGXuLhMTY0KsAdU49T2h3DsXt0WGCkHYss1QLxLPwk3eicZK+JZIxSUasAkaIFtc8Rd3sbKwuFEQ9FGxtTfAenoTudYFVsQtEWcR+kIVCsnIgsVbOwh/7IeGr+5/Y/XSvUbMW+cYGmBTE6wq0EqMbA+dsxFVOC3zsuBUke2XZVoBgTOnRrzhoJZN92TZ05skHcA1yR93a6WxaCEfP7MHfhroRO2ulUo9xoEF4rHsJI5S1/JZbp2EObrIpZp1p5rLHpxg2YgMzkSU7NxqI+mKNj5MihsgqO46kA93iJL3wEjXq99f1rcD8dNN0D3tdgx/qRyy/hl4HJlh5bDXtxxpzBRbyCLBgSPG84IK9CGjISnrRbBWq2anqMZ4QP7CI8uw==";
        String key = "85938608380313609";
        byte[] decode = Base64.getDecoder().decode(str);
        byte[] decrypt = new AESEncrypt(key).decrypt(decode);
        System.out.println(new String(decrypt));
    }

}
