package com.kursova.travel.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtAuthRequest {

    @Email
    @NotBlank
    @ApiModelProperty(required = true)
    String username;

    @NotBlank
    @Size(min = 6, max = 32)
    @ApiModelProperty(required = true)
    String password;

}
