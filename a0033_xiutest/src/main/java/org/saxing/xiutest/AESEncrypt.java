package org.saxing.xiutest;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class AESEncrypt {

    private String password;

    public AESEncrypt(String password){
        this.password = password;
    }
    public byte[] decrypt(byte[] b)throws BadPaddingException {
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, this.setKey());
            b = c.doFinal(b);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                IllegalBlockSizeException | InvalidKeyException ex) {

        } catch (BadPaddingException ex) {
            throw new BadPaddingException("Wrong password");
        }
        return b;
    }

    private SecretKeySpec setKey(){
        byte [] k = password.getBytes();
        SecretKeySpec key = null;
        try {
            k= MessageDigest.getInstance("SHA-1").digest(k);
            k = Arrays.copyOf(k,16);
            key = new SecretKeySpec(k,"AES");
        } catch (NoSuchAlgorithmException ex) {

        }
        return key;
    }

}
