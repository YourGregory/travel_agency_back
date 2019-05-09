package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.service.web.TouristWebService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class TrainerController {

    TouristWebService touristWebService;

    @GetMapping("trainers")
    @PreAuthorize("@permissionService.isAdmin() || @permissionService.isTrainer()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all Trainers", response = TouristDTO.class, responseContainer = "List"),
            @ApiResponse(code = 403, message = "You are not allowed to get trainers")
    })
    public ResponseEntity<List<TouristDTO>> getAllTrainers() {
        return ResponseEntity.ok(touristWebService.getAllTrainers());
    }

}
