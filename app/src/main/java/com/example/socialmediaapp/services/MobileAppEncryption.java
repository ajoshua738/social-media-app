package com.example.socialmediaapp.services;

import com.example.socialmediaapp.helper.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MobileAppEncryption {

    static String Key_ws = "9F343iQ4MpqNBhhghgksrJtZkRLbj3oR";
    static String IV_ws = "sdjhrDCPGXX4HlSJ";

    static String KEY_WS_DEC = "UiasRxqbGHAiicomemberaEWOqttCalI";

    static String IV_WS_DEC = "T0eAAItje32KHLX0";
    public static String EncryptWS(String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[32];
        byte[] b = Key_ws.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV_ws.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
        return new String(Base64.encodeBytes(results));

    }

    public static String DecryptWS(String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[32];
        byte[] b = KEY_WS_DEC.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV_WS_DEC.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] results = cipher.doFinal(Base64.decode(text, 0));
        return new String(results, "UTF-8");
    }
}
