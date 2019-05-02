package com.kursova.travel.entity.request;

import com.kursova.travel.entity.dictionary.SectionType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateSectionRequest {

    @Positive
    @NotNull
    Long sectionId;

    @NotBlank
    String name;

    @NotNull
    SectionType sectionType;

}
