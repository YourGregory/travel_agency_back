package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.SectionDTO;
import com.kursova.travel.entity.request.CreateSectionRequest;
import com.kursova.travel.entity.request.UpdateSectionRequest;
import com.kursova.travel.security.SystemUser;
import com.kursova.travel.service.web.SectionWebService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class SectionController {

    SectionWebService sectionWebService;

    @PutMapping(value = "sections")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("@permissionService.isAdmin()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Section has been created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "You are not allowed to create.")
    })
    public void createSection(@Valid @RequestBody CreateSectionRequest request, @AuthenticationPrincipal SystemUser systemUser) {
        sectionWebService.createSection(request, systemUser);
    }

    @PostMapping(value = "sections")
    public ResponseEntity<SectionDTO> updateSection(@Valid @RequestBody UpdateSectionRequest request) {
        return ResponseEntity.ok(sectionWebService.updateSection(request));
    }

    @GetMapping(value = "sections/{id}")
    public ResponseEntity<SectionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sectionWebService.getById(id));
    }

    @PostMapping(value = "section/{sectionId}/trainer/{trainerId}")
    public ResponseEntity<SectionDTO> changeTrainer(@PathVariable Long sectionId, @PathVariable Long trainerId) {
        return ResponseEntity.ok(sectionWebService.changeTrainer(sectionId, trainerId));
    }

    @GetMapping("admin/sections")
    @PreAuthorize("@permissionService.isAdmin()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all Trainers", response = SectionDTO.class, responseContainer = "List"),
            @ApiResponse(code = 403, message = "You are not allowed to get trainers")
    })
    public ResponseEntity<List<SectionDTO>> getSectionsForAdmin(@AuthenticationPrincipal SystemUser systemUser) {
        return ResponseEntity.ok(sectionWebService.getSectionsForAdmin(systemUser));
    }

    @GetMapping("trainer/sections")
    @PreAuthorize("@permissionService.isAdmin()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all Trainers", response = SectionDTO.class, responseContainer = "List"),
            @ApiResponse(code = 403, message = "You are not allowed to get trainers")
    })
    public ResponseEntity<List<SectionDTO>> getSectionsForTrainer(@AuthenticationPrincipal SystemUser systemUser) {
        return ResponseEntity.ok(sectionWebService.getSectionsForTrainer(systemUser));
    }

}
