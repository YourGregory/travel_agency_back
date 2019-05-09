package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractVersional;
import com.kursova.travel.entity.dictionary.CampaignType;
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

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    CampaignType campaignType;

}
