package com.kursova.travel.service;

import com.kursova.travel.entity.base.AbstractIdentifiable;
import com.kursova.travel.entity.dto.CompetitionDTO;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.model.*;
import com.kursova.travel.entity.request.Task1Request;
import com.kursova.travel.entity.request.Task3Request;
import com.kursova.travel.repository.CompetitionRepository;
import com.kursova.travel.repository.SectionRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SectionService extends DefaultCrudSupport<Section> {

    SectionRepository sectionRepository;

    @Autowired
    CompetitionRepository competitionRepository;

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

    @Transactional(readOnly = true)
    public List<CompetitionDTO> getCompetitionTask(Task3Request request) {
        return sectionRepository.findAll().stream()
                .filter(section -> section.getSectionType().equals(request.getSectionType()))
                .filter(section -> Objects.nonNull(section.getScheduler()))
                .map(Section::getScheduler)
                .map(Scheduler::getTraining)
                .flatMap(List::stream)
                .map(Training::getGroup)
                .map(Group::getTourists)
                .flatMap(List::stream)
                .map(competitionRepository::findAllByTouristsContains)
                .flatMap(List::stream)
                .map(this::mapToCompetitionDto)
                .collect(Collectors.toList());
    }

    private CompetitionDTO mapToCompetitionDto(Competition competition) {
        CompetitionDTO competitionDTO = new CompetitionDTO();
        competitionDTO.setId(competition.getId());
        competitionDTO.setTime(competition.getTime());
        competitionDTO.setName(competition.getName());
        List<TouristDTO> list = competition.getTourists().stream()
                .map(this::mapTouristToTrainerDto)
                .collect(Collectors.toList());
        competitionDTO.setTourists(list);

        return competitionDTO;
    }

    public List<AdminUser> getAllAdmins() {
        return sectionRepository.findAll().stream().map(Section::getAdminUser).collect(Collectors.toList());
    }

    public List<AdminUser> getAllAdminsBirthday(LocalDate birthday) {
        return sectionRepository.findAll().stream().map(Section::getAdminUser).filter(adminUser -> adminUser.getBirthday().equals(birthday)).collect(Collectors.toList());
    }

    public List<AdminUser> getAllAdminsCreatedAt(LocalDate createdAt) {
        return sectionRepository.findAll().stream().map(Section::getAdminUser).filter(adminUser -> adminUser.getCreatedAt().toLocalDate().equals(createdAt)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long getCountOfTraining(Long trainerId) {
        return sectionRepository.findAll().stream()
                .filter(section -> Objects.nonNull(section.getScheduler()))
                .map(Section::getScheduler)
                .map(Scheduler::getTraining)
                .flatMap(List::stream)
                .map(Training::getGroup)
                .map(Group::getTrainer)
                .map(AbstractIdentifiable::getId)
                .filter(trainerId::equals)
                .count();
    }

}
