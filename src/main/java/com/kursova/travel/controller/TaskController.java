package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.request.Task1Request;
import com.kursova.travel.service.SchedulerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class TaskController {

    SchedulerService schedulerService;

    public ResponseEntity<List<TouristDTO>> task1(Task1Request request) {
        return ResponseEntity.ok(schedulerService.task1(request));
    }

}
