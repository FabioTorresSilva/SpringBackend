package io.reflectoring.Sprint3SpringBoot.Dto;

import io.reflectoring.Sprint3SpringBoot.Enums.Role;

public class SigninDto {

    public Role role;

    public String token;

    public SigninDto(Role role, String token){
        this.role = role;
        this.token = token;
    }
}
