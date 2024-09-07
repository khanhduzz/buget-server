package com.fjb.sunrise.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO extends UserUpdateDTO {

    @NotEmpty()
    private String password;
}
