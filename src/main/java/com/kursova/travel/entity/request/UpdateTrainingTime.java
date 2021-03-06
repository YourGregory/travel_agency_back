package com.kursova.travel.entity.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateTrainingTime {

    @NotNull
    @Positive
    Long trainingId;

    @NotNull
    LocalDateTime timeOfTraining;

}
