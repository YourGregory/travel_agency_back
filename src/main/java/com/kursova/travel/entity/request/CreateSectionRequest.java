package com.kursova.travel.entity.request;

import com.kursova.travel.entity.dictionary.SectionType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSectionRequest {

    @NotNull
    Long trainerId;

    @NotNull
    SectionType sectionType;

    @NotBlank
    @Size(min = 4, max = 100)
    String name;

}
