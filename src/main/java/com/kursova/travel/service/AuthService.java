package com.kursova.travel.service;

import com.kursova.travel.common.PermissionChecker;
import com.kursova.travel.entity.request.JwtAuthRequest;
import com.kursova.travel.entity.request.RefreshTokenRequest;
import com.kursova.travel.security.SystemUser;
import com.kursova.travel.service.web.JwtWebService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthService {

    AuthenticationManager authenticationManager;
    JwtWebService jwtWebService;
    AdminService adminService;
    TouristService touristService;
    PermissionService permissionService;

    public String authenticateAdmin(JwtAuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));

        String result = null;

        if (Objects.nonNull(authentication)) {
            SystemUser systemAdminUser = (SystemUser) authentication.getPrincipal();
            boolean isAdmin = systemAdminUser.isAdmin() || systemAdminUser.isSuperAdmin();
            PermissionChecker.check(isAdmin, "You are not allowed to login");
            result = jwtWebService.generateToken(systemAdminUser);
        }

        return result;
    }

    public String authenticateTourist(JwtAuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));

        String result = null;

        if (Objects.nonNull(authentication)) {
            SystemUser systemUser = (SystemUser) authentication.getPrincipal();
            Boolean isTourist
                    = systemUser.isTrainer() || systemUser.isAmateur() || systemUser.isInstructor() || systemUser.isSportsman();
            PermissionChecker.check(isTourist, "You are not allowed to login");
            result = jwtWebService.generateToken(systemUser);
        }

        return result;
    }

    public String refreshToken(RefreshTokenRequest request) {
        Boolean isTokenAboutToExpire = jwtWebService.isTokenAboutToExpire(request.getAccessToken());
        return isTokenAboutToExpire
                ? jwtWebService.generateToken(jwtWebService.getSystemUser(request.getAccessToken()))
                : null;
    }

    public Boolean isTokenValid(String token, String applicationType) {

        var isTokenValid = jwtWebService.isTokenValid(token, applicationType);

        if (isTokenValid) {
            SystemUser systemUser = jwtWebService.getSystemUser(token);

            var abstractUser = systemUser.isAdmin() ? adminService.findByEmail(systemUser.getUsername())
                    : touristService.findByEmail(systemUser.getUsername());

            if (abstractUser.isPresent()) {
                permissionService.checkUserStatus(abstractUser.get());
            } else {
                isTokenValid = false;
            }
        }

        return isTokenValid;
    }

}
