package com.kursova.travel.service;

import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.model.Training;
import com.kursova.travel.entity.request.Task4Request;
import com.kursova.travel.repository.TrainingRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingService extends DefaultCrudSupport<Training> {

    TrainingRepository repository;

    ModelMapper modelMapper;

    public TrainingService(TrainingRepository repository, ModelMapper modelMapper) {
        super(repository);
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<TouristDTO> getAllTrainersByRequest(Task4Request request) {
        return repository.getAllTrainersByRequest(request.getGroupId(), request.getStart(), request.getEnd()).stream()
                .map(this::mapTouristToTrainerDto)
                .collect(Collectors.toList());
    }

    private TouristDTO mapTouristToTrainerDto(Tourist tourist) {
        return modelMapper.map(tourist, TouristDTO.class);
    }

}
