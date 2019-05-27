package com.kursova.travel.entity.dto;

import com.kursova.travel.entity.dictionary.CampaignType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignDTO {

    Long id;
    String name;
    List<TouristDTO> touristDTOS;
    List<RouteDTO> routeDTOS;
    TouristDTO instructor;
    boolean isPlaned;
    CampaignType campaignType;

}
