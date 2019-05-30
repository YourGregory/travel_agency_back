package com.kursova.travel.entity.request;

import com.kursova.travel.entity.dictionary.SectionType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task3Request {
    @NotNull
    SectionType sectionType;
}
