package io.reflectoring.Sprint3SpringBoot.Dto;

import io.reflectoring.Sprint3SpringBoot.Enums.Role;

public class SigninDto {

    public int id;

    public Role role;

    public String token;

    public SigninDto(int id, Role role, String token){
        this.id = id;
        this.role = role;
        this.token = token;
    }
}
