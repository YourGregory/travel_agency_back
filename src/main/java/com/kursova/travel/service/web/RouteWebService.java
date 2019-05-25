package com.kursova.travel.service.web;

import com.kursova.travel.entity.model.Route;
import com.kursova.travel.entity.request.CreateRouteRequest;
import com.kursova.travel.service.RouteService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
