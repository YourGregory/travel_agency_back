package com.kursova.travel.entity.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CreateCompetitionRequest {

    @Size(min = 2, max = 100)
    @NotBlank
    String name;

    @NotNull
    LocalDate time;

}
