package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractVersional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(callSuper = true, of = "")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "groups"
)
public class Group extends AbstractVersional {

    @Column(nullable = false, length = 100, unique = true)
    String name;

    @OneToOne(fetch = FetchType.LAZY)
    Tourist trainer;

    @OneToMany(fetch = FetchType.LAZY)
    List<Tourist> tourists;
}
