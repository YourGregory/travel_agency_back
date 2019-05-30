package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.request.Task1Request;
import com.kursova.travel.entity.request.Task3Request;
import com.kursova.travel.service.SchedulerService;
import com.kursova.travel.service.SectionService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class TaskController {

    SectionService sectionService;

    @PostMapping(value = "taks1")
    public ResponseEntity<List<TouristDTO>> task1(@RequestBody Task1Request request) {
        return ResponseEntity.ok(sectionService.getAllTouristByRequest(request));
    }

    @PostMapping(value = "taks2")
    public ResponseEntity<List<TouristDTO>> task2(@RequestBody Task1Request request) {
        return ResponseEntity.ok(sectionService.getAllTrainersByRequest(request));
    }

    @PostMapping(value = "taks3")
    public ResponseEntity<List<TouristDTO>> task3(@RequestBody Task3Request request) {
        return ResponseEntity.ok(sectionService.getAllSportsmansByRequest(request));
    }

}
