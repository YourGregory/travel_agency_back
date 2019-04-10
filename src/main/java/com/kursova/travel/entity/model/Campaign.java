package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractVersional;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@ToString(callSuper = true, of = "")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "campaigns"
)
public class Campaign extends AbstractVersional {

    @OneToOne(fetch = FetchType.LAZY)
    Tourist instructor;

    @OneToMany(fetch = FetchType.LAZY)
    List<Route> routes;

    @OneToMany(fetch = FetchType.LAZY)
    List<Tourist> tourists;

    Boolean isPlaned;

}
