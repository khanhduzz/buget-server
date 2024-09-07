package com.fjb.sunrise.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class CategoryCreateDto {

    @NotBlank()
    @Size(max = 50)
    private String name;
}
