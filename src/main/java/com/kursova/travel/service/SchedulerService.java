package com.kursova.travel.service;

import com.kursova.travel.entity.model.Scheduler;
import com.kursova.travel.repository.SchedulerRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SchedulerService extends DefaultCrudSupport<Scheduler> {

    SchedulerRepository schedulerRepository;

    public SchedulerService(SchedulerRepository repository) {
        super(repository);
        this.schedulerRepository = repository;
    }



}
