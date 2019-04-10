package com.kursova.travel.email;

import com.kursova.travel.entity.base.AbstractUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailBuilder {

    String subject;
    Map<String, String> model;
    AbstractUser abstractUser;

}