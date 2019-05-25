package com.kursova.travel.repository;

import com.kursova.travel.entity.model.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<Scheduler, Long> {
}
