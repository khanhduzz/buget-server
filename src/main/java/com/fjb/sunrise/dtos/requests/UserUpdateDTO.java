package com.fjb.sunrise.dtos.requests;

import com.fjb.sunrise.enums.EStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {

    @Size(max = 250)
    @NotBlank()
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private String firstname;

    @Size(max = 250)
    @NotBlank()
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private String lastname;

    @Email()
    @NotBlank()
    private String email;

    @NotBlank()
    @Size(max = 10)
    private String phone;

    @Enumerated(EnumType.STRING)
    private EStatus status;
}
