package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.request.JwtAuthRequest;
import com.kursova.travel.entity.request.RefreshTokenRequest;
import com.kursova.travel.service.AuthService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class AuthController {

    AuthService authService;

    @PostMapping(value = "auth", headers = "Application-Type=ADMIN")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Admin is login"),
            @ApiResponse(code = 401, message = "Bad credentials"),
            @ApiResponse(code = 403, message = "You are not allowed to login")
    })
    public ResponseEntity<String> adminUserLogIn(@Validated @RequestBody JwtAuthRequest request) {
        return ResponseEntity.ok(authService.authenticateAdmin(request));
    }

    @PostMapping(value = "auth", headers = "Application-Type=TOURIST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tourist is login"),
            @ApiResponse(code = 401, message = "Bad credentials"),
            @ApiResponse(code = 403, message = "You are not allowed to login")
    })
    public ResponseEntity<String> touristUserLogIn(@Validated @RequestBody JwtAuthRequest request) {
        return ResponseEntity.ok(authService.authenticateTourist(request));
    }

    @PostMapping(value = "auth/refresh")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Token has been refreshed"),
            @ApiResponse(code = 401, message = "Bad credentials"),
            @ApiResponse(code = 403, message = "You are not allowed to refresh token")
    })
    public ResponseEntity<String> refreshToken(@Validated @RequestBody RefreshTokenRequest request) {
        String body = authService.refreshToken(request);

        return StringUtils.isNotEmpty(body)
                ? ResponseEntity.ok(body)
                : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
