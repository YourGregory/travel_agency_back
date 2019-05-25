package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
