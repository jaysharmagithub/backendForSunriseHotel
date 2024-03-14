package com.projects.sunrise_hotel.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginReq {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
