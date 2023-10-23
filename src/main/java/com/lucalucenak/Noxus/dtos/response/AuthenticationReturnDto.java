package com.lucalucenak.Noxus.dtos.response;

import com.lucalucenak.Noxus.enums.RoleEnum;
import com.lucalucenak.Noxus.models.StatusModel;

import java.time.LocalDateTime;

public class AuthenticationReturnDto {

    private String jwtToken;

    public AuthenticationReturnDto() {

    }

    public AuthenticationReturnDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
