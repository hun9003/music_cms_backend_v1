package com.music.music_cms_backend_v1.domain.admin;

import lombok.Getter;

@Getter
public class AdminInfo {
    private final Long id;
    private final String adminToken;
    private final String adminName;
    private final String email;
    private final Admin.Permission permission;

    public AdminInfo(Admin admin) {
        this.id = admin.getId();
        this.adminToken = admin.getAdminToken();
        this.adminName = admin.getAdminName();
        this.email = admin.getEmail();
        this.permission = admin.getPermission();
    }
}
