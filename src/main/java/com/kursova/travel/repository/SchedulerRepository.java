package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Scheduler;
import com.kursova.travel.entity.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {

}
