package com.music.music_cms_backend_v1.infrastructure.admin;

import com.music.music_cms_backend_v1.common.exception.InvalidParamException;
import com.music.music_cms_backend_v1.domain.admin.Admin;
import com.music.music_cms_backend_v1.domain.admin.AdminStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminStoreImpl implements AdminStore {
    private final AdminRepository adminRepository;

    @Override
    public Admin store(Admin admin) {
        System.out.println("AdminStoreImpl :: store");
        if (StringUtils.isEmpty(admin.getAdminToken())) throw new InvalidParamException("admin.getAdminToken()");
        if (StringUtils.isEmpty(admin.getAdminName())) throw new InvalidParamException("admin.getAdminName()");
        if (StringUtils.isEmpty(admin.getAdminPassword())) throw new InvalidParamException("admin.getAdminPassword()");
        if (StringUtils.isEmpty(admin.getEmail())) throw new InvalidParamException("admin.getEmail()");
        if (admin.getPermission() == null) throw new InvalidParamException("admin.getPermission()");
        return adminRepository.save(admin);
    }
}
