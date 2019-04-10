package com.kursova.travel.service;

import com.kursova.travel.common.PermissionChecker;
import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.dictionary.EntityStatus;
import com.kursova.travel.security.SystemUser;
import com.kursova.travel.security.dictionary.SecurityConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    AdminService adminService;
    TouristService touristService;

    HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String applicationType = request.getHeader(SecurityConstants.APPLICATION_TYPE);
        UserDetails userDetails;

        switch (applicationType) {
            case "ADMIN":
                userDetails = getAdminDetails(username);
                break;
            case "TOURIST":
                userDetails = getTouristDetails(username);
                break;
            default:
                throw new IllegalArgumentException("Not valid application type");
        }

        return userDetails;
    }

    private UserDetails getTouristDetails(String username) {
        Tourist foundTourist = touristService.getByEmail(username);
        validateUserStatus(foundTourist.getUserStatus());
        return new SystemUser(foundTourist.getEmail(), foundTourist.getPassword(), foundTourist.getUserRole().name());
    }

    private UserDetails getAdminDetails(String username) {
        AdminUser foundAdminUser = adminService.getByEmail(username);
        validateUserStatus(foundAdminUser.getUserStatus());
        return new SystemUser(foundAdminUser.getEmail(), foundAdminUser.getPassword(), foundAdminUser.getUserRole().name());
    }

    private void validateUserStatus(EntityStatus userStatus) {
        PermissionChecker.check(BooleanUtils.isFalse(EntityStatus.isInactive(userStatus)), "User is inactive");
    }
}
