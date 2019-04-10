package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractVersional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString(callSuper = true, of = "")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "routes"
)
public class Route extends AbstractVersional {

    @Column(nullable = false)
    LocalDate time;

    @Column(nullable = false, length = 100)
    String startOfRoute;

    @Column(nullable = false, length = 100)
    String endOfRoute;
}
