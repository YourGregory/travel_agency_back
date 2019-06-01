package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractVersional;
import com.kursova.travel.entity.dictionary.SectionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString(callSuper = true, of = "")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "sections"
)
public class Section extends AbstractVersional {

    @Column(nullable = false)
    String name;

    @OneToOne(fetch = FetchType.EAGER)
    AdminUser adminUser;

    @OneToOne(fetch = FetchType.LAZY)
    Tourist trainer;

    @Enumerated(value = EnumType.STRING)
    SectionType sectionType;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    Scheduler scheduler;

}
