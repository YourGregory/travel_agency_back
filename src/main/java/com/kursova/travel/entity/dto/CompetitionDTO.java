package com.kursova.travel.entity.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompetitionDTO {

    Long id;
    String name;
    LocalDate time;
    List<TouristDTO> tourists;

}
