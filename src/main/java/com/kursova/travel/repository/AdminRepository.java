package com.kursova.travel.repository;

import com.kursova.travel.entity.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminUser, Long> {

    Optional<AdminUser> findByEmail(String email);

}
