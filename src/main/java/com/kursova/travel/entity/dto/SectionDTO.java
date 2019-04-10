package com.kursova.travel.entity.dto;

import com.kursova.travel.entity.dictionary.SectionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SectionDTO {

    Long id;
    String name;
    SectionType sectionType;
    String trainerName;
    String trainerEmail;

}
