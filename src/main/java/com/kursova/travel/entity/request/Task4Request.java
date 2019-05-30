package com.kursova.travel.entity.request;

import com.kursova.travel.entity.model.Group;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task4Request {

    Long groupId;
    LocalDate start;
    LocalDate end;

}
