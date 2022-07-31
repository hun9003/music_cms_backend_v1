package com.music.music_cms_backend_v1.domain.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Override
    public AdminInfo registerAdmin(AdminCommand.RegisterAdmin command) {
        System.out.println("AdminServiceImpl :: registerAdmin");
        return null;
    }

    @Override
    public AdminInfo getAdminInfo(String adminToken) {
        System.out.println("AdminServiceImpl :: getAdminInfo");
        return null;
    }

    @Override
    public AdminInfo changePermission(AdminCommand.ChangeAdminPermission command) {
        System.out.println("AdminServiceImpl :: changePermission");
        return null;
    }
}
