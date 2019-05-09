package com.kursova.travel.service;

import com.kursova.travel.entity.model.Training;
import com.kursova.travel.repository.TrainingRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingService extends DefaultCrudSupport<Training> {

    TrainingRepository repository;

    public TrainingService(TrainingRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
