package com.kursova.travel.service;


import com.kursova.travel.entity.dictionary.UserRole;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.repository.TouristRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TouristService extends DefaultCrudSupport<Tourist> {

    TouristRepository repository;

    public TouristService(TouristRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<Tourist> findByEmail(@NonNull final String email) {
        return repository.findByEmail(email.toLowerCase());
    }

    public Tourist getByEmail(@NonNull final String email) {
        return findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find user with email: %s", email)));
    }

    @Transactional(readOnly = true)
    public List<Tourist> findAllTrainers() {
        return repository.findAllByUserRole(UserRole.TRAINER);
    }

    @Transactional(readOnly = true)
    public List<Tourist> findAllSportsmans() {
        return repository.findAllByUserRole(UserRole.SPORTSMAN);
    }

    @Transactional(readOnly = true)
    public List<Tourist> findAllAmateurs() {
        return repository.findAllByUserRole(UserRole.AMATEUR);
    }

    public List<Tourist> findAllTourist() {
        List<Tourist> tourists = findAllSportsmans();
        tourists.addAll(findAllAmateurs());
        return tourists;
    }
}
