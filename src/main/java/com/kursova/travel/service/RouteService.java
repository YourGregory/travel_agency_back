package com.kursova.travel.service;

import com.kursova.travel.entity.model.Route;
import com.kursova.travel.repository.RouteRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteService extends DefaultCrudSupport<Route> {

    RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        super(routeRepository);
        this.routeRepository = routeRepository;
    }

}
