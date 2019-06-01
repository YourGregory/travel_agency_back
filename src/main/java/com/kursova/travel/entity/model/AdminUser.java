package com.kursova.travel.entity.model;

import com.kursova.travel.entity.base.AbstractUser;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * user.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
@ToString(callSuper = true, of = "")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(
        name = "admin_users",
        indexes = @Index(columnList = "email"),
        uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
@Data
@NoArgsConstructor
public class AdminUser extends AbstractUser {

}
