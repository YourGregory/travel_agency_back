package com.kursova.travel.entity.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateGroupRequest {

    @Positive
    @NotNull
    Long trainerId;

    @NotBlank
    String name;

}
