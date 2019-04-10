package com.kursova.travel.service;

import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.repository.AdminRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminService extends DefaultCrudSupport<AdminUser> {

    AdminRepository repository;

    public AdminService(AdminRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<AdminUser> findByEmail(@NonNull final String email) {
        return repository.findByEmail(email.toLowerCase());
    }

    public AdminUser getByEmail(@NonNull final String email) {
        return findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find user with email: %s", email)));
    }

}
