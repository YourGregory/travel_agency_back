package com.kursova.travel.service;

import com.kursova.travel.entity.dto.GroupDTO;
import com.kursova.travel.entity.model.Group;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.repository.GroupRepository;
import com.kursova.travel.repository.TouristRepository;
import com.kursova.travel.security.SystemUser;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupService extends DefaultCrudSupport<Group> {

    final GroupRepository groupRepository;
    TouristRepository touristRepository;

    public GroupService(GroupRepository repository, TouristRepository touristRepository) {
        super(repository);
        groupRepository = repository;
        this.touristRepository = touristRepository;
    }

    public List<Group> getGroupsByAdmin() {
        return groupRepository.findAll();
    }

    public List<Group> getGroupsByTrainer(SystemUser systemUser) {
        Tourist tourist = touristRepository.findByEmail(systemUser.getUsername()).get();
        return groupRepository.findAllByTrainer(tourist);
    }

    public Integer getCountOfTraining(Long trainerId) {
        return 4;
    }
}
