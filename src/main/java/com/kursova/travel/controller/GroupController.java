package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.request.CreateGroupRequest;
import com.kursova.travel.service.web.GroupWebService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class GroupController {

    GroupWebService groupWebService;

    @PutMapping(value = "groups")
    public void createGroup(@RequestBody CreateGroupRequest request) {

    }

}
