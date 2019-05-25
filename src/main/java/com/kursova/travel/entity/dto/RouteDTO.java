package com.kursova.travel.entity.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteDTO {

    LocalDate time;
    String startOfRoute;
    String endOfRoute;
}
