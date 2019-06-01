package com.kursova.travel.entity.base;

import com.kursova.travel.entity.dictionary.EntityStatus;
import com.kursova.travel.entity.dictionary.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * This user is able to login to application.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public abstract class AbstractUser extends AbstractVersional {

    @Column(nullable = false, length = 100)
    String password;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false, length = 50)
    String email;

    @Column(nullable = false)
    LocalDate birthday;

    @Column(length = 25)
    String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    EntityStatus userStatus = EntityStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    UserRole userRole;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

}
