package io.reflectoring.Sprint3SpringBoot.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.UserDto;
import io.reflectoring.Sprint3SpringBoot.Dto.UserFullDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.ParamException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RepositoryException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.UserNotFoundException;
import io.reflectoring.Sprint3SpringBoot.Mapper.UsersMapper;
import io.reflectoring.Sprint3SpringBoot.Models.User;
import io.reflectoring.Sprint3SpringBoot.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing users.
 * Provides endpoints for retrieving, creating, and updating users.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UsersMapper usersMapper;

    /**
     * Constructor for dependency injection.
     *
     * @param userService The service handling user operations.
     * @param usersMapper The mapper for converting User objects to DTOs.
     */
    @Autowired
    public UserController(UserService userService, UsersMapper usersMapper) {
        this.userService = userService;
        this.usersMapper = usersMapper;
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The unique identifier of the user.
     * @return A ResponseEntity containing a {@link UserDto} if found, or an error message with the appropriate HTTP status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(usersMapper.UserToDto(user));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Creates a new user.
     *
     * @param userFullDto A {@link UserFullDto} containing the user details.
     * @return A ResponseEntity containing the created {@link UserDto} or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserFullDto userFullDto) {
        try {
            User newUser = new User();
            newUser.name = userFullDto.name;
            newUser.email = userFullDto.email;
            newUser.password = userFullDto.password;
            newUser.role = userFullDto.role;

            userService.createUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(usersMapper.UserToDto(newUser));
        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Updates an existing user.
     *
     * @param id      The unique identifier of the user to update.
     * @param userDto A {@link UserDto} containing the updated user details.
     * @return A ResponseEntity containing the updated {@link UserDto} or an error message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        try {
            User updatedUser = userService.getUserById(id);
            updatedUser.name = userDto.name;
            updatedUser.email = userDto.email;

            userService.updateUser(id, updatedUser);
            return ResponseEntity.ok(userDto);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ParamException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
