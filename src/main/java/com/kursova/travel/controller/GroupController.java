package com.kursova.travel.controller;

import com.kursova.travel.constants.Constant;
import com.kursova.travel.entity.dto.GroupDTO;
import com.kursova.travel.entity.dto.SectionDTO;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.request.TouristsToGroup;
import com.kursova.travel.entity.request.CreateGroupRequest;
import com.kursova.travel.service.web.GroupWebService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequestMapping(value = Constant.API_V1_PREFIX)
public class GroupController {

    GroupWebService groupWebService;

    @PutMapping(value = "groups")
    public void createGroup(@RequestBody CreateGroupRequest request) {
        groupWebService.createGroup(request);
    }

    @PutMapping(value = "groups/add/tourists")
    public void addTourists(@RequestBody TouristsToGroup request) {
        groupWebService.addTouristToGroup(request);
    }

    @PutMapping(value = "groups/remove/tourists")
    public void removeTourists(@RequestBody TouristsToGroup request) {
        groupWebService.removeTouristToGroup(request);
    }

    @PostMapping(value = "/groups/{groupId}/trainer/{trainerId}")
    public ResponseEntity<GroupDTO> changeTrainer(@PathVariable Long groupId, @PathVariable Long trainerId) {
        return ResponseEntity.ok(groupWebService.changeTrainer(groupId, trainerId));
    }

    @GetMapping(value = "/groups/{groupId}/tourist/")
    public ResponseEntity<List<TouristDTO>> getTouristsByGroup(@PathVariable Long groupId) {
        return ResponseEntity.ok(groupWebService.getTouristsByGroup(groupId));
    }

}
