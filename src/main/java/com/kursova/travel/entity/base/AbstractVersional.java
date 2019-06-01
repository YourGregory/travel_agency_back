package com.kursova.travel.entity.base;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Base class for entities which have to be versioned.
 * By versioned we mean that those entities will have
 * created and updated timestamp. Actual tracking of this values
 * will be delegated to Spring Data Auditing module, so you don't
 * need to update those fields manually.
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public abstract class AbstractVersional extends AbstractIdentifiable {

    @CreatedDate
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
    LocalDateTime updatedAt;

}
