package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.CompetitionDTO;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.dto.TrainingDTO;
import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.request.Task1Request;
import com.kursova.travel.entity.request.Task3Request;
import com.kursova.travel.entity.request.Task4Request;
import com.kursova.travel.entity.request.Task61Request;
import com.kursova.travel.service.CampaignService;
import com.kursova.travel.service.GroupService;
import com.kursova.travel.service.SectionService;
import com.kursova.travel.service.TrainingService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class TaskController {

    SectionService sectionService;
    TrainingService trainingService;
    GroupService groupService;
    CampaignService campaignService;


    @PostMapping(value = "taks1")
    public ResponseEntity<List<TouristDTO>> task1(@RequestBody Task1Request request) {
        return ResponseEntity.ok(sectionService.getAllTouristByRequest(request));
    }

    @PostMapping(value = "taks2")
    public ResponseEntity<List<TouristDTO>> task2(@RequestBody Task1Request request) {
        return ResponseEntity.ok(sectionService.getAllTrainersByRequest(request));
    }

    @PostMapping(value = "taks3")
    public ResponseEntity<List<CompetitionDTO>> task3(@RequestBody Task3Request request) {
        return ResponseEntity.ok(sectionService.getCompetitionTask(request));
    }

    @PostMapping(value = "taks4")
    public ResponseEntity<List<TouristDTO>> task4(@RequestBody Task4Request request) {
        return ResponseEntity.ok(trainingService.getAllTrainersByRequest(request));
    }

    @Transactional
    @PostMapping(value = "taks61")
    public ResponseEntity<List<AdminUser>> task61() {
        return ResponseEntity.ok(sectionService.getAllAdmins());
    }

    @Transactional
    @PostMapping(value = "taks62")
    public ResponseEntity<List<AdminUser>> task62(@RequestBody Task61Request request) {
        return ResponseEntity.ok(sectionService.getAllAdminsBirthday(request.getBirthday()));
    }

    @Transactional
    @PostMapping(value = "taks63")
    public ResponseEntity<List<AdminUser>> task63(@RequestBody Task61Request request) {
        return ResponseEntity.ok(sectionService.getAllAdminsCreatedAt(request.getBirthday()));
    }

    @PostMapping(value = "taks71/{trainerId}")
    public ResponseEntity<Long> task71(@PathVariable Long trainerId) {
        return ResponseEntity.ok(sectionService.getCountOfTraining(trainerId));
    }

    @PostMapping(value = "taks81/{sectionId}")
    public ResponseEntity<List<TrainingDTO>> task81(@PathVariable Long sectionId) {
        return ResponseEntity.ok(campaignService.getTrainings(sectionId));
    }

}
