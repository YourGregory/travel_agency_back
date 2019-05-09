package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Group;
import com.kursova.travel.entity.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByTrainer(Tourist trainer);

}
