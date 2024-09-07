package com.fjb.sunrise.dtos.responses;

import com.fjb.sunrise.enums.EStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
