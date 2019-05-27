package com.kursova.travel.entity.request;

import com.kursova.travel.entity.dictionary.CampaignType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCampaignRequest {

    @NotBlank
    String name;

    @Positive
    @NotNull
    Long instructorId;

    @NotNull
    CampaignType campaignType;

    @NotNull
    Boolean isPlanned;

}
