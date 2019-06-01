package com.kursova.travel.entity.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTrainingRequest {

    @Positive
    @NotNull
    Long sectionId;

    @Positive
    @NotNull
    Long groupId;

    @NotNull
    LocalDate timeOfTraining;

}
