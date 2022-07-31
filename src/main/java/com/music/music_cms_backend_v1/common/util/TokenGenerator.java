package com.music.music_cms_backend_v1.common.util;

import com.music.music_cms_backend_v1.common.exception.BaseException;
import com.music.music_cms_backend_v1.common.response.ErrorCode;
import com.music.music_cms_backend_v1.domain.admin.Admin;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class TokenGenerator {
    private static final int TOKEN_LENGTH = 32;
    private static final String SECRET_KEY = "This incredible, perfect SECRET_KEY was created by a wonderful Rateye";

//    public static String createToken(String subject, long expTime) {
//
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
//        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
//
//        return Jwts.builder()
//                .setSubject(subject)
//                .signWith(signingKey, signatureAlgorithm)
//                .setExpiration(new Date(System.currentTimeMillis() + expTime))
//                .compact();
//    }

//    public static String getSubject(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }


    public static String randomCharacter(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String randomCharacterWithPrefix(String prefix) {
        return prefix + randomCharacter(TOKEN_LENGTH - prefix.length());
    }

    public static String getHeaderToken(String authorization) {
        return authorization.replace("Bearer ", "").trim();
    }
}