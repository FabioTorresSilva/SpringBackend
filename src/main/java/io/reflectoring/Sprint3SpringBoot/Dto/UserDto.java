package io.reflectoring.Sprint3SpringBoot.Dto;

import io.reflectoring.Sprint3SpringBoot.Enums.Role;

public class UserDto {

    public String name;

    public String email;

    public Role role;

    public UserDto(String name, String email, Role role){
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
