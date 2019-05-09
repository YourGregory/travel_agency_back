package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractVersional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.management.openmbean.CompositeType;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(callSuper = true, of = "")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "competitions"
)
public class Competition extends AbstractVersional {

    @Column(nullable = false, length = 100)
    String name;

    @OneToMany(fetch = FetchType.LAZY)
    List<Tourist> tourists;

    @Column(nullable = false)
    LocalDate time;

}
