package com.music.music_cms_backend_v1.infrastructure.admin;

import com.music.music_cms_backend_v1.domain.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAdminToken(String adminToken);
}
