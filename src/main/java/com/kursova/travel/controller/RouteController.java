package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.request.CreateRouteRequest;
import com.kursova.travel.service.web.RouteWebService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class RouteController {

    RouteWebService routeWebService;


    @PutMapping("routes")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoute(CreateRouteRequest request) {
        routeWebService.createRoute(request);
    }

}
