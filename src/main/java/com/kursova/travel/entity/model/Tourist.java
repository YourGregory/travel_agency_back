package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractUser;
import com.kursova.travel.entity.dictionary.Category;
import com.kursova.travel.entity.dictionary.Gender;
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
        name = "tourists",
        indexes = @Index(columnList = "email"),
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class Tourist extends AbstractUser {

    @Enumerated(value = EnumType.STRING)
    Category category;

    @Enumerated(value = EnumType.STRING)
    Gender gender;
}
