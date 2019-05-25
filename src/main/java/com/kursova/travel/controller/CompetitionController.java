package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.CompetitionDTO;
import com.kursova.travel.entity.request.CreateCompetitionRequest;
import com.kursova.travel.service.web.CompetitionWebService;
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
public class CompetitionController {

    CompetitionWebService competitionWebService;

    @GetMapping("competitions")
    public ResponseEntity<List<CompetitionDTO>> getAll(){
        return ResponseEntity.ok(competitionWebService.getAll());
    }

    @PutMapping("competitions")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCompetition(CreateCompetitionRequest request) {
        competitionWebService.createCompetition(request);
    }

    @PostMapping("competitions/{competitionId}/tourists")
    public ResponseEntity<CompetitionDTO> addTourists(@PathVariable Long competitionId,
                                                      @RequestParam List<Long> touristsIds) {
        return ResponseEntity.ok(competitionWebService.addTourists(competitionId, touristsIds));
    }

}
