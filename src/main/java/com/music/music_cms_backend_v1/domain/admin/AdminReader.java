package com.music.music_cms_backend_v1.domain.admin;

public interface AdminReader {
    Admin getAdmin(Long adminId);
    Admin getAdmin(String adminToken);
}
