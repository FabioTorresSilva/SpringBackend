package io.reflectoring.Sprint3SpringBoot.Mapper;

import io.reflectoring.Sprint3SpringBoot.Dto.UserDto;
import io.reflectoring.Sprint3SpringBoot.Models.*;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

    public UsersMapper(){}

    public UserDto UserToDto(User user){
        return new UserDto(user.getName(), user.getEmail(), user.getRole(), user.getPassword());
    }

    //public UserDto ClientToDto(Client user){
        //return new UserDto(user.name, user.email, user.role);
    //}

    //public UserDto TesterToDto(Tester user){
        //return new UserDto(user.name, user.email, user.role);
    //}
}
