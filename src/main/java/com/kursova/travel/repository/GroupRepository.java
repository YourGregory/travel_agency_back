package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
