package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.TrainingDTO;
import com.kursova.travel.entity.request.CreateTrainingRequest;
import com.kursova.travel.entity.request.UpdateTrainingTime;
import com.kursova.travel.service.web.TrainingWebService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class TrainingController {

    TrainingWebService trainingWebService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "trainings")
    public void createTraining(@RequestBody CreateTrainingRequest request) {
        trainingWebService.createTraining(request);
    }

    @PutMapping(value = "trainings")
    public ResponseEntity<TrainingDTO> updateTrainingTime(@RequestBody UpdateTrainingTime request) {
        return ResponseEntity.ok(trainingWebService.updateTrainingTime(request));
    }

    @GetMapping(value = "section/{sectionId}/trainigs")
    public ResponseEntity<List<TrainingDTO>> getAllBySectionId(@PathVariable Long sectionId) {
        return ResponseEntity.ok(trainingWebService.getAllBySectionId(sectionId));
    }

}
