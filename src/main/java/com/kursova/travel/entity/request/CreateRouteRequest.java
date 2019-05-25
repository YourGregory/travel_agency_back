package com.kursova.travel.entity.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateRouteRequest {

    @NotNull
    LocalDate time;

    @NotBlank
    String startOfRoute;

    @NotBlank
    String endOfRoute;

}
