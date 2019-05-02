package com.kursova.travel.service;

import com.kursova.travel.entity.model.Group;
import com.kursova.travel.repository.GroupRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupService extends DefaultCrudSupport<Group> {

    GroupRepository groupRepository;

    public GroupService(GroupRepository repository) {
        super(repository);
        groupRepository = repository;
    }

}
