package com.kursova.travel.entity.request;

import com.kursova.travel.entity.dictionary.Gender;
import com.kursova.travel.entity.dictionary.SectionType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task1Request {

    SectionType sectionType;
    Gender gender;
    LocalDate localDate;
    Long sectionId;
    Long groupId;

}
