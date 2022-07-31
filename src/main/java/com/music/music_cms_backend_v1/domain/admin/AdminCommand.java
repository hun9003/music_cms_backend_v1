package com.music.music_cms_backend_v1.domain.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


public class AdminCommand {

    @Getter
    @Builder
    @ToString
    public static class RegisterAdmin {
        private String adminName;
        private String adminPassword;
        private String email;

        public Admin toEntity() {
            return Admin.builder()
                    .adminName(adminName)
                    .adminPassword(adminPassword)
                    .email(email)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class ChangeAdminPermission {
        private String adminToken;
        private Admin.Permission permission;
    }



}
