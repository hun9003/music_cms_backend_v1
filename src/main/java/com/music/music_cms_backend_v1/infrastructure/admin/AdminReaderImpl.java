package com.music.music_cms_backend_v1.infrastructure.admin;

import com.music.music_cms_backend_v1.common.exception.EntityNotFoundException;
import com.music.music_cms_backend_v1.domain.admin.Admin;
import com.music.music_cms_backend_v1.domain.admin.AdminReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminReaderImpl implements AdminReader {
    private final AdminRepository adminRepository;

    @Override
    public Admin getAdmin(Long adminId) {
        System.out.println("AdminReaderImpl :: getAdmin");
        return adminRepository.findById(adminId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Admin getAdmin(String adminToken) {
        System.out.println("AdminReaderImpl :: getAdmin");
        return adminRepository.findByAdminToken(adminToken)
                .orElseThrow(EntityNotFoundException::new);
    }
}
