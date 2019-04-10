package com.kursova.travel.security;

import com.kursova.travel.entity.dictionary.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SystemUser extends User {

    public SystemUser(String username, String password, String role) {
        super(username, password, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }

    public Boolean isAdmin() {
        return containsRole(UserRole.ADMIN);
    }

    public Boolean isSuperAdmin() {
        return containsRole(UserRole.SUPER_ADMIN);
    }

    public Boolean isTrainer() {
        return containsRole(UserRole.TRAINER);
    }

    public Boolean isAmateur() {
        return containsRole(UserRole.AMATEUR);
    }

    public Boolean isSportsman() {
        return containsRole(UserRole.SPORTSMAN);
    }

    public Boolean isInstructor() {
        return containsRole(UserRole.INSTRUCTOR);
    }

    private Boolean containsRole(UserRole userRole) {
        List<String> authorities = getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return authorities.contains(userRole.name());
    }

}
