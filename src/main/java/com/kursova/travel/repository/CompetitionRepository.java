package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Competition;
import com.kursova.travel.entity.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findAllByTouristsContains(Tourist tourist);
}
