package com.kursova.travel.service.web;

import com.kursova.travel.entity.dto.GroupDTO;
import com.kursova.travel.entity.dto.TouristDTO;
import com.kursova.travel.entity.model.Group;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.entity.request.CreateGroupRequest;
import com.kursova.travel.entity.request.TouristsToGroup;
import com.kursova.travel.security.SystemUser;
import com.kursova.travel.service.GroupService;
import com.kursova.travel.service.TouristService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupWebService {

    GroupService groupService;
    TouristService touristService;

    ModelMapper modelMapper;

    @Transactional
    public void createGroup(CreateGroupRequest request) {
        Tourist trainer = touristService.getById(request.getTrainerId());
        Group group = new Group();
        group.setName(request.getName());
        group.setTrainer(trainer);
        groupService.create(group);
    }

    @Transactional
    public void addTouristToGroup(TouristsToGroup request) {
        Group group = groupService.getById(request.getGroupId());

        List<Tourist> tourists = request.getIds().stream()
                .map(touristService::getById).collect(Collectors.toList());

        group.getTourists().addAll(tourists);

        groupService.save(group);
    }

    @Transactional
    public void removeTouristToGroup(TouristsToGroup request) {
        Group group = groupService.getById(request.getGroupId());

        List<Tourist> tourists = request.getIds().stream()
                .map(touristService::getById).collect(Collectors.toList());

        group.getTourists().removeAll(tourists);

        groupService.save(group);
    }

    @Transactional
    public GroupDTO changeTrainer(Long groupId, Long trainerId) {
        Group group = groupService.getById(groupId);
        Tourist trainer = touristService.getById(trainerId);
        group.setTrainer(trainer);

        Group savedGroup = groupService.save(group);
        return mapToGroupDto(savedGroup);
    }

    private GroupDTO mapToGroupDto(Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());
        groupDTO.setTrainerName(group.getTrainer().getFullName());
        groupDTO.setCountOfParticular(group.getTourists().size());

        return groupDTO;
    }

    @Transactional(readOnly = true)
    public List<TouristDTO> getTouristsByGroup(Long groupId) {
        return groupService.getById(groupId).getTourists().stream()
                .map(this::mapTouristToTrainerDto)
                .collect(Collectors.toList());
    }

    private TouristDTO mapTouristToTrainerDto(Tourist tourist) {
        return modelMapper.map(tourist, TouristDTO.class);
    }

    @Transactional(readOnly = true)
    public List<GroupDTO> getGroups(SystemUser systemUser) {
        List<Group> result = null;

        if (systemUser.isAdmin() || systemUser.isSuperAdmin()) {
            result = groupService.getGroupsByAdmin();
        } else if (systemUser.isTrainer()) {
            result = groupService.getGroupsByTrainer(systemUser);
        }

        return result.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GroupDTO getGroupById(Long groupId) {
        return mapToGroupDto(groupService.getById(groupId));
    }
}
