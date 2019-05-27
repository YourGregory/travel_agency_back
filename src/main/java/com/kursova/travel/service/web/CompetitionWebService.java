package com.kursova.travel.service.web;

import com.kursova.travel.entity.dto.CompetitionDTO;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.model.Competition;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.request.CreateCompetitionRequest;
import com.kursova.travel.service.CompetitionService;
import com.kursova.travel.service.TouristService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompetitionWebService {

    CompetitionService competitionService;
    TouristService touristService;

    ModelMapper modelMapper;

    @Transactional
    public void createCompetition(CreateCompetitionRequest request) {
        Competition competition = new Competition();
        competition.setName(request.getName());
        competition.setTime(request.getTime());

        competitionService.create(competition);
    }

    @Transactional
    public CompetitionDTO addTourists(Long competitionId, List<Long> touristsIds) {
        Competition competition = competitionService.getById(competitionId);
        List<Tourist> touristList = touristsIds.stream()
                .map(touristService::getById)
                .collect(Collectors.toList());

        competition.setTourists(touristList);

        Competition updatedCompetition = competitionService.update(competition);
        return mapToCompetitionDto(updatedCompetition);
    }

    private CompetitionDTO mapToCompetitionDto(Competition competition) {
        CompetitionDTO competitionDTO = new CompetitionDTO();
        competitionDTO.setId(competition.getId());
        competitionDTO.setTime(competition.getTime());
        competitionDTO.setName(competition.getName());
        List<TouristDTO> list = competition.getTourists().stream()
                .map(this::mapTouristToTouritstDto)
                .collect(Collectors.toList());
        competitionDTO.setTourists(list);

        return competitionDTO;
    }

    private TouristDTO mapTouristToTouritstDto(Tourist tourist) {
        return modelMapper.map(tourist, TouristDTO.class);
    }

    @Transactional(readOnly = true)
    public List<CompetitionDTO> getAll() {
        return competitionService.findAll().stream()
                .map(this::mapToCompetitionDto)
                .collect(Collectors.toList());
    }

}
