package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.service.web.TouristWebService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class AmateurController {

    TouristWebService touristWebService;

    @GetMapping(value = "amateurs")
    public ResponseEntity<List<TouristDTO>> getAllAmateurs() {
        return ResponseEntity.ok(touristWebService.getAllAmateurs());
    }

}
