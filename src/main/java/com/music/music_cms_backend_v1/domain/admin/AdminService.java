package com.music.music_cms_backend_v1.domain.admin;

public interface AdminService {
    AdminInfo registerAdmin(AdminCommand.RegisterAdmin command);
    AdminInfo getAdminInfo(String adminToken);
    AdminInfo changePermission(AdminCommand.ChangeAdminPermission command);
}
