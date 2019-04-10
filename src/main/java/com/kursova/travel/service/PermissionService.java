package com.kursova.travel.service;

import com.kursova.travel.common.PermissionChecker;
import com.kursova.travel.entity.base.AbstractUser;
import com.kursova.travel.entity.dictionary.EntityStatus;
import com.kursova.travel.entity.dictionary.UserRole;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.security.SystemUser;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PermissionService {

    TouristService touristService;
    AdminService adminService;

    @Transactional(readOnly = true)
    public void canBeAuthorized(SystemUser systemUser) {

    }

    public Boolean isAdmin() {
        return hasRole(UserRole.ADMIN);
    }

    public Boolean isSuperAdmin() {
        return hasRole(UserRole.SUPER_ADMIN);
    }

    public Boolean isSportsman() {
        return hasRole(UserRole.SPORTSMAN);
    }

    public Boolean isTrainer() {
        return hasRole(UserRole.TRAINER);
    }

    public Boolean isAmateur() {
        return hasRole(UserRole.AMATEUR);
    }

    public Boolean isInstructor() {
        return hasRole(UserRole.INSTRUCTOR);
    }

    public void checkUserStatus(AbstractUser user) {
        boolean isUserInactive = EntityStatus.INACTIVE.equals(user.getUserStatus());
        PermissionChecker.check(BooleanUtils.isFalse(isUserInactive), "User is inactive");
    }

    private Boolean hasRole(UserRole userRole) {
        SystemUser systemUser = (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return systemUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(userRole.name()::equals);
    }

    @Transactional(readOnly = true)
    public void canUpdateEmail(Long id, String email) {
        Tourist foundTourist = touristService.getById(id);

        if (BooleanUtils.isFalse(foundTourist.getEmail().equals(email))) {
            Optional<Tourist> touristOptional = touristService.findByEmail(email);

            PermissionChecker.check(touristOptional.isEmpty(), "Email is already in use");
        }
    }

    @Transactional(readOnly = true)
    public void canUpdateEmail(SystemUser systemUser, String email) {
        Tourist foundTourist = touristService.getByEmail(email);

        if (BooleanUtils.isFalse(foundTourist.getEmail().equals(email))) {
            Optional<Tourist> touristOptional = touristService.findByEmail(email);

            PermissionChecker.check(touristOptional.isEmpty(), "Email is already in use");
        }
    }

    @Transactional(readOnly = true)
    public void canCreateWithEmail(String email) {
        Optional<Tourist> touristOptional = touristService.findByEmail(email);

        PermissionChecker.check(touristOptional.isEmpty(), "Email is already in use");
    }
}
