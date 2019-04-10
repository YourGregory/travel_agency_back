package com.kursova.travel.entity.request;

import com.kursova.travel.entity.dictionary.Gender;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTouristProfileRequest {

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
    Gender gender;

    @NotNull
    @Past
    LocalDate birthday;

}
