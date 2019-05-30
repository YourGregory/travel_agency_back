package com.kursova.travel.service;

import com.kursova.travel.entity.dictionary.UserRole;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.model.Section;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.request.Task1Request;
import com.kursova.travel.entity.request.Task3Request;
import com.kursova.travel.entity.request.Task4Request;
import com.kursova.travel.repository.SectionRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SectionService extends DefaultCrudSupport<Section> {

    SectionRepository sectionRepository;

    ModelMapper modelMapper;

    public SectionService(SectionRepository repository, ModelMapper modelMapper) {
        super(repository);
        sectionRepository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<Section> getAllByAdmin(AdminUser adminUser) {
        return sectionRepository.findAllByAdminUser(adminUser);
    }

    @Transactional(readOnly = true)
    public List<Section> getAllByTrainer(Tourist trainer) {
        return sectionRepository.findAllByTrainer(trainer);
    }

    @Transactional(readOnly = true)
    public List<TouristDTO> getAllTouristByRequest(Task1Request request) {
        return sectionRepository.getAllByRequest(request.getSectionType(), request.getGender(), request.getLocalDate()).stream()
                .map(this::mapTouristToTrainerDto)
                .collect(Collectors.toList());
    }

    private TouristDTO mapTouristToTrainerDto(Tourist tourist) {
        return modelMapper.map(tourist, TouristDTO.class);
    }

    @Transactional(readOnly = true)
    public List<TouristDTO> getAllTrainersByRequest(Task1Request request) {
        return sectionRepository.getAllTrainersByRequest(request.getSectionType(), request.getGender(), request.getLocalDate()).stream()
                .map(this::mapTouristToTrainerDto)
                .collect(Collectors.toList());
    }

    public List<TouristDTO> getAllSportsmansByRequest(Task3Request request) {

        return sectionRepository.getAllSportsmanByRequest(request.getSectionType(), UserRole.SPORTSMAN).stream()
                .map(this::mapTouristToTrainerDto)
                .collect(Collectors.toList());
    }

}
