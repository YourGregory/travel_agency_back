package com.kursova.travel.service.web;

import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.request.UpdateTouristByAdminRequest;
import com.kursova.travel.entity.request.UpdateTouristProfileRequest;
import com.kursova.travel.security.SystemUser;
import com.kursova.travel.service.TouristService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TouristWebService {

    TouristService touristService;

    ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<TouristDTO> getAllTrainers() {
        List<Tourist> tourists = touristService.findAllTrainers();
        return tourists.stream()
                .map(this::mapTouristToTrainerDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public TouristDTO updateByAdmin(UpdateTouristByAdminRequest request) {
        Tourist foundTourist = touristService.getById(request.getId());
        foundTourist.setFirstName(request.getFirstName());
        foundTourist.setLastName(request.getLastName());
        foundTourist.setCategory(request.getCategory());
        foundTourist.setGender(request.getGender());
        foundTourist.setBirthday(request.getBirthday());
        foundTourist.setEmail(request.getEmail());
        foundTourist.setPhone(request.getPhone());

        return mapTouristToTrainerDto(touristService.update(foundTourist));
    }

    @Transactional
    public TouristDTO updateProfile(SystemUser systemUser, UpdateTouristProfileRequest request) {
        Tourist foundTourist = touristService.getByEmail(systemUser.getUsername());

        foundTourist.setFirstName(request.getFirstName());
        foundTourist.setLastName(request.getLastName());
        foundTourist.setEmail(request.getEmail());
        foundTourist.setPhone(request.getPhone());
        foundTourist.setBirthday(request.getBirthday());
        foundTourist.setGender(request.getGender());

        return mapTouristToTrainerDto(touristService.update(foundTourist));
    }

    @Transactional
    public void deleteByAdmin(Long id) {
        touristService.delete(id);
    }

    private TouristDTO mapTouristToTrainerDto(Tourist tourist) {
        return modelMapper.map(tourist, TouristDTO.class);
    }

    @Transactional(readOnly = true)
    public TouristDTO getById(Long id) {
        Tourist trainer = touristService.getById(id);
        return mapTouristToTrainerDto(trainer);
    }
}
