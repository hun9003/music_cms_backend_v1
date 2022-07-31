package com.music.music_cms_backend_v1.common.util;

import java.util.UUID;

public class UUIDGenerator {
    public static String makeUUID() {
        return UUID.randomUUID().toString();
    }
}
