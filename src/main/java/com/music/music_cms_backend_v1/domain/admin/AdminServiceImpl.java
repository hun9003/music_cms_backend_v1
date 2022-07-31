package com.music.music_cms_backend_v1.domain.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminStore adminStore;
    private final AdminReader adminReader;

    @Override
    @Transactional
    public AdminInfo registerAdmin(AdminCommand.RegisterAdmin command) {
        System.out.println("AdminServiceImpl :: registerAdmin");
        var initAdmin = command.toEntity();
        var admin = adminStore.store(initAdmin);
        return new AdminInfo(admin);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminInfo getAdminInfo(String adminToken) {
        System.out.println("AdminServiceImpl :: getAdminInfo");
        var admin = adminReader.getAdmin(adminToken);
        return new AdminInfo(admin);
    }

    @Override
    @Transactional
    public AdminInfo changePermission(AdminCommand.ChangeAdminPermission command) {
        System.out.println("AdminServiceImpl :: changePermission");
        var admin = adminReader.getAdmin(command.getAdminToken());
        admin.changePermission(command.getPermission());
        return new AdminInfo(admin);
    }
}
