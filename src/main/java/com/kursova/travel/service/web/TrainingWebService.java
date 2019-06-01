package com.kursova.travel.service.web;

import com.kursova.travel.entity.dto.TrainingDTO;
import com.kursova.travel.entity.model.Group;
import com.kursova.travel.entity.model.Section;
import com.kursova.travel.entity.model.Training;
import com.kursova.travel.entity.request.CreateTrainingRequest;
import com.kursova.travel.entity.request.UpdateTrainingTime;
import com.kursova.travel.service.GroupService;
import com.kursova.travel.service.SectionService;
import com.kursova.travel.service.TrainingService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingWebService {

    SectionService sectionService;
    GroupService groupService;
    TrainingService trainingService;

    @Transactional
    public void createTraining(CreateTrainingRequest request) {
        Section section = sectionService.getById(request.getSectionId());
        Group group = groupService.getById(request.getGroupId());

        Training training = new Training();
        training.setGroup(group);
        training.setTimeOfTraining(LocalDateTime.of(request.getTimeOfTraining(), LocalTime.MIN));
        Training createdTraining = trainingService.create(training);

        section.getScheduler().getTraining().add(createdTraining);

        sectionService.update(section);
    }

    @Transactional
    public TrainingDTO updateTrainingTime(UpdateTrainingTime request) {
        Training training = trainingService.getById(request.getTrainingId());
        training.setTimeOfTraining(request.getTimeOfTraining());
        return mapToTrainingDto(trainingService.update(training));
    }

    private TrainingDTO mapToTrainingDto(Training training) {
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTrainingId(training.getId());
        trainingDTO.setGroupId(training.getGroup().getId());
        trainingDTO.setGroupName(training.getGroup().getName());
        trainingDTO.setTimeOfTraining(training.getTimeOfTraining());
        return trainingDTO;
    }


    @Transactional(readOnly = true)
    public List<TrainingDTO> getAllBySectionId(Long sectionId) {
        Section section = sectionService.getById(sectionId);
        return section.getScheduler().getTraining().stream()
                .map(this::mapToTrainingDto)
                .collect(Collectors.toList());
    }
}
