package com.kursova.travel.repository;

import com.kursova.travel.entity.dictionary.UserRole;
import com.kursova.travel.entity.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TouristRepository extends JpaRepository<Tourist, Long> {

    Optional<Tourist> findByEmail(String email);

    List<Tourist> findAllByUserRole(UserRole userRole);

}
