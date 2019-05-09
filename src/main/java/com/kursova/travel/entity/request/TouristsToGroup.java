package com.kursova.travel.entity.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TouristsToGroup {

    @NotEmpty
    List<Long> ids;

    @Positive
    @NotNull
    Long groupId;
}
