package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractVersional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(callSuper = true, of = "")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "schedulers"
)
public class Scheduler extends AbstractVersional {

    @OneToMany(fetch = FetchType.LAZY)
    List<Training> training = new ArrayList<>();

    String place;

}
