package com.kursova.travel.service.web;

import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.request.CreateTouristRequest;
import com.kursova.travel.service.TouristService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationWebService {

    TouristService touristService;

    ModelMapper modelMapper;
    PasswordEncoder passwordEncoder;
    EmailWebService emailWebService;

    @Transactional
    public void createTourist(CreateTouristRequest request) {
        Tourist tourist = mapToTourist(request);
        String password = new RandomString(10).nextString();
        tourist.setPassword(passwordEncoder.encode(password));
        touristService.create(tourist);
        emailWebService.sendFirstPassword(tourist, password);
    }

    private Tourist mapToTourist(CreateTouristRequest request) {
        return modelMapper.map(request, Tourist.class);
    }

}
