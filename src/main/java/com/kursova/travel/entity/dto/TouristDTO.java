package com.kursova.travel.entity.dto;

import com.kursova.travel.entity.dictionary.Category;
import com.kursova.travel.entity.dictionary.EntityStatus;
import com.kursova.travel.entity.dictionary.Gender;
import com.kursova.travel.entity.dictionary.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TouristDTO {

    Long id;

    Category category;

    Gender gender;

    String fullName;

    String email;

    UserRole userRole;

    EntityStatus userStatus;

    LocalDate birthday;

    String phone;

}
