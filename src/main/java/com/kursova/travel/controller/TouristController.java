package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.request.UpdateTouristByAdminRequest;
import com.kursova.travel.entity.request.UpdateTouristProfileRequest;
import com.kursova.travel.security.SystemUser;
import com.kursova.travel.service.PermissionService;
import com.kursova.travel.service.web.TouristWebService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class TouristController {

    TouristWebService touristWebService;

    PermissionService permissionService;

    @PostMapping(value = "admin/tourist/update")
    @PreAuthorize("@permissionService.isAdmin() || @permissionService.isSuperAdmin()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "You are not allowed to create user")
    })
    public ResponseEntity<TouristDTO> updateByAdmin(@RequestBody UpdateTouristByAdminRequest request) {
        permissionService.canUpdateEmail(request.getId(), request.getEmail());
        return ResponseEntity.ok(touristWebService.updateByAdmin(request));
    }

    @GetMapping(value = "admin/tourist/{id}")
    public ResponseEntity<TouristDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(touristWebService.getById(id));
    }

    @PostMapping(value = "admin/tourist/delete/{id}")
    @PreAuthorize("@permissionService.isAdmin() || @permissionService.isSuperAdmin()")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "You are not allowed to delete user")
    })
    public void deleteByAdmin(@PathVariable Long id) {
        touristWebService.deleteByAdmin(id);
    }

    @PostMapping(value = "tourist/profile")
    @PreAuthorize("@permissionService.isAdmin() || @permissionService.isSuperAdmin()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "You are not allowed to create user")
    })
    public ResponseEntity<TouristDTO> updateProfile(@AuthenticationPrincipal SystemUser systemUser,
                                                    UpdateTouristProfileRequest request) {
        permissionService.canUpdateEmail(systemUser, request.getEmail());
        return ResponseEntity.ok(touristWebService.updateProfile(systemUser, request));
    }

    @GetMapping(value = "tourists")
    public ResponseEntity<List<TouristDTO>> getAllTourist() {
        return ResponseEntity.ok(touristWebService.getAllTourists());
    }

}
