package com.music.music_cms_backend_v1.domain.admin;

import com.music.music_cms_backend_v1.common.exception.InvalidParamException;
import com.music.music_cms_backend_v1.common.util.HashGenerator;
import com.music.music_cms_backend_v1.common.util.TokenGenerator;
import com.music.music_cms_backend_v1.domain.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Slf4j
@Getter
@Entity
@NoArgsConstructor
@Table(name = "admin")
public class Admin extends AbstractEntity {
    private static final String PREFIX_PARTNER = "a_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adminToken;
    private String adminName;
    private String adminPassword;
    private String email;

    @Enumerated(EnumType.STRING)
    private Permission permission;

    @Getter
    @RequiredArgsConstructor
    public enum Permission {
        SUPER_ADMIN("최고 관리자"),
        ADMINISTRATOR("관리자"),
        EDITOR("편집자"),
        CONTRIBUTOR("외부자"),
        SUBSCRIBER("일반");
        private final String description;
    }

    @Builder
    public Admin(String adminName, String adminPassword, String email) {
        if (StringUtils.isEmpty(adminName)) throw new InvalidParamException("empty adminName");
        if (StringUtils.isEmpty(adminPassword)) throw new InvalidParamException("empty adminPassword");
        if (StringUtils.isEmpty(email)) throw new InvalidParamException("empty email");
        this.adminToken = TokenGenerator.randomCharacterWithPrefix(PREFIX_PARTNER);
        this.adminName = adminName;
        this.adminPassword = HashGenerator.passwordEncoder(adminPassword);
        this.email = email;
        this.permission = Permission.SUBSCRIBER;
    }

    public void changePermission(Permission permission) {
        this.permission = permission;
    }

}
