package com.kursova.travel.service.web;

import com.kursova.travel.entity.dto.SectionDTO;
import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.model.Section;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.request.CreateSectionRequest;
import com.kursova.travel.entity.request.UpdateSectionRequest;
import com.kursova.travel.security.SystemUser;
import com.kursova.travel.service.AdminService;
import com.kursova.travel.service.SectionService;
import com.kursova.travel.service.TouristService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SectionWebService {

    SectionService sectionService;
    AdminService adminService;
    TouristService touristService;

    @Transactional
    public void createSection(CreateSectionRequest request, SystemUser systemUser) {
        AdminUser adminUser = adminService.getByEmail(systemUser.getUsername());
        Optional<Tourist> trainerOptional = touristService.findById(request.getTrainerId());
        trainerOptional.ifPresent(trainer -> {
            Section section = new Section();
            section.setAdminUser(adminUser);
            section.setName(request.getName());
            section.setTrainer(trainer);
            section.setSectionType(request.getSectionType());
            sectionService.create(section);
        });
    }

    @Transactional(readOnly = true)
    public List<SectionDTO> getSectionsForAdmin(SystemUser systemUser) {
        AdminUser adminUser = adminService.getByEmail(systemUser.getUsername());
        return sectionService.getAllByAdmin(adminUser).stream()
                .map(this::mapToSectionDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<SectionDTO> getSectionsForTrainer(SystemUser systemUser) {
        Tourist trainer = touristService.getByEmail(systemUser.getUsername());
        return sectionService.getAllByTrainer(trainer).stream()
                .map(this::mapToSectionDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public SectionDTO changeTrainer(Long sectionId, Long trainerId) {
        Section section = sectionService.getById(sectionId);
        Tourist trainer = touristService.getById(trainerId);

        section.setTrainer(trainer);

        Section updatedSection = sectionService.update(section);
        return mapToSectionDto(updatedSection);
    }

    @Transactional(readOnly = true)
    public SectionDTO getById(Long id) {
        Section section = sectionService.getById(id);
        return mapToSectionDto(section);
    }

    @Transactional
    public SectionDTO updateSection(UpdateSectionRequest request) {
        Section section = sectionService.getById(request.getSectionId());
        section.setName(request.getName());
        section.setSectionType(request.getSectionType());
        Section updatedSection = sectionService.update(section);
        return mapToSectionDto(updatedSection);
    }

    private SectionDTO mapToSectionDto(Section section) {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setSectionType(section.getSectionType());
        sectionDTO.setTrainerName(section.getTrainer().getFullName());
        sectionDTO.setTrainerEmail(section.getTrainer().getEmail());
        sectionDTO.setName(section.getName());
        return sectionDTO;
    }

}
