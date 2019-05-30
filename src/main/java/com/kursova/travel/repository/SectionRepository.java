package com.kursova.travel.repository;

import com.kursova.travel.entity.dictionary.Gender;
import com.kursova.travel.entity.dictionary.SectionType;
import com.kursova.travel.entity.dictionary.UserRole;
import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.model.Competition;
import com.kursova.travel.entity.model.Section;
import com.kursova.travel.entity.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {

    List<Section> findAllByAdminUser(AdminUser adminUser);

    List<Section> findAllByTrainer(Tourist trainer);

    @Query(value = "SELECT tr from Section s inner join s.scheduler sc inner join sc.training t inner join t.group g" +
            " inner join g.tourists tr where s.sectionType =?1 and tr.gender =?2 and tr.birthday =?3")
    List<Tourist> getAllByRequest(SectionType sectionType, Gender gender, LocalDate localDate);

    @Query(value = "SELECT tr from Section s inner join s.trainer tr" +
            " where s.sectionType =?1 and tr.gender =?2 and tr.birthday =?3")
    List<Tourist> getAllTrainersByRequest(SectionType sectionType, Gender gender, LocalDate localDate);

    @Query(value = "SELECT cm from Section s inner join s.scheduler sc inner join sc.training t inner join t.group g" +
            " inner join g.tourists sp inner join Campaign cm on cm.tourists = g.tourists where s.sectionType =?1 and sp.userRole =?2")
    List<Competition> getAllSportsmanByRequest(SectionType sectionType, UserRole userRole);

}
