package com.kursova.travel.entity.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDTO {

    Long id;

    String name;

    String trainerName;

    Integer countOfParticular;

}
