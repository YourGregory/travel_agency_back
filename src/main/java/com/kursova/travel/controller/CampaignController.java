package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.CampaignDTO;
import com.kursova.travel.entity.request.CreateCampaignRequest;
import com.kursova.travel.service.web.CampaignWebService;
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
public class CampaignController {

    CampaignWebService campaignWebService;

    @GetMapping(value = "campaign")
    public ResponseEntity<List<CampaignDTO>> getAll() {
        return ResponseEntity.ok(campaignWebService.getAllCampaign());
    }

    @PutMapping(value = "campaign")
    public void createCampaign(@RequestBody CreateCampaignRequest request) {
        campaignWebService.createCampaign(request);
    }


    @PostMapping(value = "campaign/{id}/tourists")
    public ResponseEntity<CampaignDTO> addTourists(@PathVariable Long id, @RequestParam List<Long> touristIds) {
        return ResponseEntity.ok(campaignWebService.addTourists(id, touristIds));
    }

    @PostMapping(value = "campaign/{id}/routes")
    public ResponseEntity<CampaignDTO> addRoutes(@PathVariable Long id, @RequestParam List<Long> routesIds) {
        return ResponseEntity.ok(campaignWebService.addRoutes(id, routesIds));
    }

}
