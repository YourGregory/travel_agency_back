package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractVersional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString(callSuper = true, of = "")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "trainings"
)
public class Training extends AbstractVersional {

    @ManyToOne(fetch = FetchType.LAZY)
    Group group;

    @Column(nullable = false)
    LocalDateTime timeOfTraining;

}
