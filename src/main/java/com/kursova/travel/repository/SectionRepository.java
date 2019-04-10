package com.kursova.travel.repository;

import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.model.Section;
import com.kursova.travel.entity.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findAllByAdminUser(AdminUser adminUser);

    List<Section> findAllByTrainer(Tourist trainer);

}
