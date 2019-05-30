package com.kursova.travel.service.web;

import com.kursova.travel.entity.dto.CampaignDTO;
import com.kursova.travel.entity.dto.RouteDTO;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.model.Campaign;
import com.kursova.travel.entity.model.Route;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.request.CreateCampaignRequest;
import com.kursova.travel.service.CampaignService;
import com.kursova.travel.service.RouteService;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CampaignWebService {

    CampaignService campaignService;
    TouristService touristService;
    RouteService routeService;

    ModelMapper modelMapper;

    @Transactional
    public void createCampaign(CreateCampaignRequest request) {
        Tourist tourist = touristService.getById(request.getInstructorId());
        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setInstructor(tourist);
        campaign.setCampaignType(request.getCampaignType());
        campaign.setIsPlaned(request.getIsPlanned());

        campaignService.create(campaign);
    }


    @Transactional
    public CampaignDTO addTourists(Long id, List<Long> touristIds) {
        Campaign campaign = campaignService.getById(id);
        List<Tourist> touristList = touristIds.stream()
                .map(touristService::getById)
                .collect(Collectors.toList());

        campaign.getTourists().addAll(touristList);
        Campaign updatedCampaign = campaignService.update(campaign);

        return mapToCampaignDto(updatedCampaign);
    }

    private CampaignDTO mapToCampaignDto(Campaign updatedCampaign) {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setCampaignType(updatedCampaign.getCampaignType());
        campaignDTO.setId(updatedCampaign.getId());
        campaignDTO.setName(updatedCampaign.getName());
        campaignDTO.setPlaned(updatedCampaign.getIsPlaned());

        List<TouristDTO> touristDTOS = updatedCampaign.getTourists().stream()
                .map(this::mapTouristToTrainerDto)
                .collect(Collectors.toList());
        campaignDTO.setTouristDTOS(touristDTOS);
        campaignDTO.setInstructor(mapTouristToTrainerDto(updatedCampaign.getInstructor()));
        List<RouteDTO> routeDTOS = updatedCampaign.getRoutes().stream()
                .map(this::mapToRouteDto)
                .collect(Collectors.toList());
        campaignDTO.setRouteDTOS(routeDTOS);
        return campaignDTO;
    }

    private TouristDTO mapTouristToTrainerDto(Tourist tourist) {
        return modelMapper.map(tourist, TouristDTO.class);
    }

    public RouteDTO mapToRouteDto(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setEndOfRoute(route.getEndOfRoute());
        routeDTO.setStartOfRoute(route.getStartOfRoute());
        routeDTO.setTime(route.getTime());

        return routeDTO;
    }

    @Transactional
    public CampaignDTO addRoutes(Long id, List<Long> routesIds) {
        Campaign campaign = campaignService.getById(id);
        List<Route> routes = routesIds.stream()
                .map(routeService::getById)
                .collect(Collectors.toList());
        campaign.getRoutes().addAll(routes);
        Campaign updateCampaign = campaignService.update(campaign);
        return mapToCampaignDto(updateCampaign);
    }

    @Transactional(readOnly = true)
    public List<CampaignDTO> getAllCampaign() {
        return campaignService.findAll()
                .stream()
                .map(this::mapToCampaignDto)
                .collect(Collectors.toList());
    }
}
