package com.music.music_cms_backend_v1.common.util;


import com.music.music_cms_backend_v1.common.exception.InvalidParamException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.*;
import java.sql.Timestamp;

public class HashGenerator {
    private static final String SALT = "(^H1^A2^t3^S4^U5^N6^E7^M8^i9^k10^U1^S12@E13@E14@U15@U16*N17*i18*V18*O20*C21*A22*L23*O24*i25*D26*OwO)";
    private static final String aesKey = "Muesli Co., Ltd. Inc. Your Inc Maker";

    public static String getSalt() {
        return SALT;
    }

    public static String sha1(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1"); // 이 부분을 SHA-256, MD5로만 바꿔주면 된다.
        md.update(msg.getBytes());

        byte[] byteData = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte byteDatum : byteData) {
            sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    /**
     * SHA-256으로 해싱하는 메소드
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha256(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(msg.getBytes());

        return bytesToHex(md.digest());
    }

    /**
     * MD5로 해싱하는 메소드
     * @param msg
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String msg) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5"); // 이 부분을 SHA-256, MD5로만 바꿔주면 된다.
        md.update(msg.getBytes());

        byte[] byteData = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte byteDatum : byteData) {
            sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    /**
     * 바이트를 헥스값으로 변환한다
     *
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public static Key getAESKey() throws Exception {
        String iv;
        Key keySpec;

        iv = aesKey.substring(0, 16);
        byte[] keyBytes = new byte[16];
        byte[] b = md5(aesKey).getBytes("UTF-8");

        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }

        System.arraycopy(b, 0, keyBytes, 0, len);
        keySpec = new SecretKeySpec(keyBytes, "AES");

        return keySpec;
    }

    // 암호화
    public static String encAES(String str) throws Exception {
        Key keySpec = getAESKey();
        String key = md5(aesKey);
        String iv = key.substring(0, 16);
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));

        return enStr;
    }

    // 복호화
    public static String decAES(String str) throws Exception {
        Key keySpec = getAESKey();
        String key = md5(aesKey);
        String iv = key.substring(0, 16);
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
        byte[] byteStr = Base64.decodeBase64(str.getBytes("UTF-8"));
        String decStr = new String(c.doFinal(byteStr), "UTF-8");

        return decStr;
    }

    // 비밀번호 암호화
    public static String passwordEncoder(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    // 암호화된 코드 이메일 가져오기
    public static String getEmailByKey(String key) throws Exception {
        String keyDecAES = decAES(key);
        return keyDecAES.substring(keyDecAES.indexOf("\"")+1, keyDecAES.indexOf("\"",2));
    }
}
