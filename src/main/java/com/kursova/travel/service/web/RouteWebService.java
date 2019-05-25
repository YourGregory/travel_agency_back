package com.kursova.travel.service.web;

import com.kursova.travel.entity.dto.RouteDTO;
import com.kursova.travel.entity.model.Route;
import com.kursova.travel.entity.request.CreateRouteRequest;
import com.kursova.travel.service.RouteService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteWebService {

    RouteService routeService;

    @Transactional
    public void createRoute(CreateRouteRequest request) {
        Route route = new Route();
        route.setTime(request.getTime());
        route.setEndOfRoute(request.getEndOfRoute());
        route.setStartOfRoute(request.getStartOfRoute());

        routeService.create(route);
    }

    @Transactional(readOnly = true)
    public List<RouteDTO> getAll() {
        return routeService.findAll().stream()
                .map(this::mapToRouteDto)
                .collect(Collectors.toList());
    }

    public RouteDTO mapToRouteDto(Route route) {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setEndOfRoute(route.getEndOfRoute());
        routeDTO.setStartOfRoute(route.getStartOfRoute());
        routeDTO.setTime(route.getTime());

        return routeDTO;
    }
}
