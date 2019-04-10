package com.kursova.travel.entity.request;

import com.kursova.travel.entity.dictionary.Category;
import com.kursova.travel.entity.dictionary.Gender;
import com.kursova.travel.entity.dictionary.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTouristByAdminRequest {

    @NotNull
    @Positive
    Long id;

    @NotBlank
    @Size(min = 4, max = 100)
    String firstName;

    @NotBlank
    @Size(min = 4, max = 100)
    String lastName;

    @NotBlank
    @Size(min = 4, max = 100)
    String email;

    @NotBlank
    @Size(min = 4, max = 100)
    String phone;

    @NotNull
    UserRole userRole;

    @NotNull
    Category category;

    @NotNull
    Gender gender;

    @NotNull
    @Past
    LocalDate birthday;

}
