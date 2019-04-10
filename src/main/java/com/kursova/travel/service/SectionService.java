package com.kursova.travel.service;

import com.kursova.travel.entity.model.AdminUser;
import com.kursova.travel.entity.model.Section;
import com.kursova.travel.entity.model.Tourist;
import com.kursova.travel.repository.SectionRepository;
import com.kursova.travel.service.base.DefaultCrudSupport;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.BitSet;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SectionService extends DefaultCrudSupport<Section> {

    SectionRepository sectionRepository;

    public SectionService(SectionRepository repository) {
        super(repository);
        sectionRepository = repository;
    }

    @Transactional(readOnly = true)
    public List<Section> getAllByAdmin(AdminUser adminUser) {
        return sectionRepository.findAllByAdminUser(adminUser);
    }

    @Transactional(readOnly = true)
    public List<Section> getAllByTrainer(Tourist trainer) {
        return sectionRepository.findAllByTrainer(trainer);
    }
}
