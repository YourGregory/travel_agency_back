package com.kursova.travel.service;

import com.kursova.travel.entity.model.Campaign;
import com.kursova.travel.repository.CampaignRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CampaignService extends DefaultCrudSupport<Campaign> {

    CampaignRepository repository;

    public CampaignService(CampaignRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
