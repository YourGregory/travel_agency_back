package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Campaign;
import com.kursova.travel.entity.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query(value = "SELECT tr from Section s inner join s.scheduler sc inner join sc.training tr inner join tr.group g inner join  g.tourists inner join Campaign cm on g.tourists = cm.tourists where s.id = ?1")
    List<Training> getAllTraining(Long sectionId);

}
