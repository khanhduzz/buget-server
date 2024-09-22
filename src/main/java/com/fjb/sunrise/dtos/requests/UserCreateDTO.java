package com.fjb.sunrise.dtos.requests;

import com.fjb.sunrise.enums.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO extends UserUpdateDTO {

    @NotEmpty()
    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;
}
