package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.request.CreateTouristRequest;
import com.kursova.travel.service.PermissionService;
import com.kursova.travel.service.web.RegistrationWebService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class RegistrationController {

    RegistrationWebService registrationWebService;

    PermissionService permissionService;

    @PostMapping(value = "tourists")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("@permissionService.isAdmin() || @permissionService.isSuperAdmin()")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Tourist created"),
            @ApiResponse(code = 401, message = "Bad credentials"),
            @ApiResponse(code = 403, message = "You are not allowed to create user")
    })
    public void createTourist(@RequestBody @Validated CreateTouristRequest request) {
        permissionService.canCreateWithEmail(request.getEmail());
        registrationWebService.createTourist(request);
    }

}
