package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Campaign;
import com.kursova.travel.entity.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

//    @Query(value = "SELECT tr from Section s inner join s.group")
//    List<Training> getAllTraining(Long sectionId);

}
