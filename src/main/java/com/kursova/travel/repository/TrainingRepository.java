package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query(value = "SELECT tr FROM Training t inner join t.group g inner join g.trainer tr where g.id =?1 " +
            "and (t.timeOfTraining >= ?2 and t.timeOfTraining <= ?3)")
    List<Tourist> getAllTrainersByRequest(Long groupId, LocalDateTime start, LocalDateTime end);

}
