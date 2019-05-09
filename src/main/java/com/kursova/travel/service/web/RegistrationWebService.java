package com.kursova.travel.service.web;

import com.kursova.travel.entity.dictionary.UserRole;
import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.request.CreateAdminRequest;
import com.kursova.travel.entity.request.CreateTouristRequest;
import com.kursova.travel.service.AdminService;
import com.kursova.travel.service.TouristService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationWebService {

    TouristService touristService;
    AdminService adminService;

    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;
    EmailWebService emailWebService;

    @Transactional
    public void createTourist(CreateTouristRequest request) {
        Tourist tourist = mapToTourist(request);
        String password = new RandomString(10).nextString();
        tourist.setPassword(passwordEncoder.encode(password));
        touristService.create(tourist);
        emailWebService.sendFirstPassword(tourist, password);
    }

    @Transactional
    public void createAdmin(CreateAdminRequest request) {
        AdminUser adminUser = new AdminUser();
        adminUser.setUserRole(UserRole.ADMIN);
        adminUser.setBirthday(request.getBirthday());
        adminUser.setEmail(request.getEmail());
        adminUser.setFirstName(request.getFirstName());
        adminUser.setLastName(request.getLastName());
        adminUser.setPhone(request.getPhone());

        String password = new RandomString(10).nextString();
        adminUser.setPassword(passwordEncoder.encode(password));

        AdminUser createdAdmin = adminService.create(adminUser);
        emailWebService.sendFirstPassword(createdAdmin, password);
    }

    private Tourist mapToTourist(CreateTouristRequest request) {
        return modelMapper.map(request, Tourist.class);
    }

}
