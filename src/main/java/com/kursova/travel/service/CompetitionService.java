package com.kursova.travel.service;

import com.kursova.travel.entity.model.Competition;
import com.kursova.travel.repository.CompetitionRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompetitionService extends DefaultCrudSupport<Competition> {

    CompetitionRepository competitionRepository;

    public CompetitionService(CompetitionRepository competitionRepository) {
        super(competitionRepository);
        this.competitionRepository = competitionRepository;
    }

}
