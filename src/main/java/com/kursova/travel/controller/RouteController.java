package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.RouteDTO;
import com.kursova.travel.entity.request.CreateRouteRequest;
import com.kursova.travel.service.web.RouteWebService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("routes")
    public ResponseEntity<List<RouteDTO>> getAll(){
        return ResponseEntity.ok(routeWebService.getAll());
    }

}
