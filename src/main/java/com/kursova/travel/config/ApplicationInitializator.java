package com.kursova.travel.config;

import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.dictionary.UserRole;
import com.kursova.travel.service.AdminService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationInitializator implements ApplicationListener<ApplicationReadyEvent> {

    AdminService adminService;

    PasswordEncoder passwordEncoder;

    static final String ADMIN_EMAIL = "super-admin@gmail.com";
    static final String DEFAULT_PASSWORD = "secret";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        createAdminUser();
    }

    private void createAdminUser() {
        if (adminService.findAll().size() == NumberUtils.INTEGER_ZERO) {
            AdminUser adminUser = new AdminUser();
            adminUser.setEmail(ADMIN_EMAIL);
            adminUser.setFirstName("Admin");
            adminUser.setUserRole(UserRole.ADMIN);
            adminUser.setLastName("LastName");
            adminUser.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
            adminUser.setPhone("0000000000");
            adminUser.setBirthday(LocalDate.of(1999,12,12));
            adminService.create(adminUser);
        }
    }

}
