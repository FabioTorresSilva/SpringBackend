package io.reflectoring.Sprint3SpringBoot.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Dto.UserDto;
import io.reflectoring.Sprint3SpringBoot.Dto.UserFullDto;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Enums.Role;
import io.reflectoring.Sprint3SpringBoot.Exceptions.*;
import io.reflectoring.Sprint3SpringBoot.Mapper.UsersMapper;
import io.reflectoring.Sprint3SpringBoot.Models.User;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.FountainService;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.WaterAnalysisService;
import io.reflectoring.Sprint3SpringBoot.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing users.
 * Provides endpoints for retrieving, creating, and updating users.
 */
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final FountainService fountainService;
    private final WaterAnalysisService waterAnalysisService;
    private final UsersMapper usersMapper;

    /**
     * Constructor for dependency injection.
     *
     * @param userService The service handling user operations.
     * @param usersMapper The mapper for converting User objects to DTOs.
     * @param fountainService The service handling fountain operations.
     * @param waterAnalysisService The service handling waterAnalysis operations.
     */
    @Autowired
    public UserController(UserService userService, UsersMapper usersMapper, FountainService fountainService, WaterAnalysisService waterAnalysisService) {
        this.userService = userService;
        this.usersMapper = usersMapper;
        this.fountainService = fountainService;
        this.waterAnalysisService = waterAnalysisService;
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
            newUser.setName(userFullDto.name);
            newUser.setEmail(userFullDto.email);
            newUser.setPassword(userFullDto.password);
            newUser.setRole(userFullDto.role);

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
            if(!Objects.equals(userDto.name, updatedUser.getName()))
                updatedUser.setName(userDto.name);
            if(!Objects.equals(userDto.email, updatedUser.getEmail()))
                updatedUser.setEmail(userDto.email);
            if(!Objects.equals(userDto.password, updatedUser.getPassword()))
                updatedUser.setPassword(userDto.password);

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

    /**
     * Retrieves a user's favorite fountains.
     *
     * @param id The unique identifier of the user.
     * @return A ResponseEntity containing a list of {@link FountainDto}.
     */
    @GetMapping("/favorites/{id}")
    public ResponseEntity<?> getUserFavourites(@PathVariable int id){
        try{
            return ResponseEntity.ok(userService.getUserFavourites(id));

        } catch (UserNotFoundException | RoleNotAcepted e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/favorites/{id}/{i}")
    public ResponseEntity<?> getXFavourites(@PathVariable int id, @PathVariable int i){
        try{
            return ResponseEntity.ok(userService.getXFavourites(id, i));

        } catch (UserNotFoundException | RoleNotAcepted | ParamException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Retrieves a tester's water analyses.
     *
     * @param id The unique identifier of the tester.
     * @return A ResponseEntity containing a list of {@link WaterAnalysisDto}.
     */
    @GetMapping("/analysis/{id}")
    public ResponseEntity<?> getTesterWaterAnalysis(@PathVariable int id){
        try{
            return ResponseEntity.ok(userService.getTesterWaterAnalysis(id));

        } catch (UserNotFoundException | RoleNotAcepted e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Retrieves all users by role.
     *
     * @param role The role of users to retrieve.
     * @return A ResponseEntity containing a list of {@link User}.
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<?> getAllUsersByRole(@PathVariable Role role){
        try{
            return ResponseEntity.ok(userService.getAllUsersByRole(role));

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Adds a fountain to the user's list of favorites.
     *
     * @param id The unique identifier of the user.
     * @param fountainId The unique identifier of the fountain to be added as a favorite.
     * @return A ResponseEntity containing the added {@link FountainDto} or an error message.
     * @throws UserNotFoundException If the user is not found.
     * @throws RoleNotAcepted If the user does not have the required role to perform this action.
     * @throws RetrofitException If an error occurs while retrieving the fountain data.
     */
    @PostMapping("/addfavorite/{id}/{fountainId}")
    public ResponseEntity<?> addFavourite(@PathVariable int id, @PathVariable int fountainId) {
        try {
            FountainDto fountain = fountainService.getFountainById(fountainId);
            if (fountain == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fountain not found");
            }
            return ResponseEntity.ok(userService.addFavourite(id, fountainId));
        } catch (UserNotFoundException | RoleNotAcepted | RetrofitException | ParamException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Removes a fountain from the user's list of favorites.
     *
     * @param id The unique identifier of the user.
     * @param fountainId The unique identifier of the fountain to be removed from favorites.
     * @return A ResponseEntity containing the removed {@link FountainDto} or an error message.
     * @throws UserNotFoundException If the user is not found.
     * @throws RoleNotAcepted If the user does not have the required role to perform this action.
     * @throws RetrofitException If an error occurs while retrieving the fountain data.
     */
    @PostMapping("/removefavorite/{id}/{fountainId}")
    public ResponseEntity<?> removeFavourite(@PathVariable int id, @PathVariable int fountainId) {
        try {
            FountainDto fountain = fountainService.getFountainById(fountainId);
            if (fountain == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fountain not found");
            }
            return ResponseEntity.ok(userService.removeFavourite(id, fountainId));
        } catch (UserNotFoundException | RoleNotAcepted | RetrofitException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Adds a water analysis to the tester's records.
     *
     * @param id The unique identifier of the tester.
     * @param waterAnalysisId The unique identifier of the water analysis to be added.
     * @return A ResponseEntity containing the added {@link WaterAnalysisDto} or an error message.
     * @throws UserNotFoundException If the tester is not found.
     * @throws RoleNotAcepted If the user does not have the required tester role.
     * @throws RetrofitException If an error occurs while retrieving the water analysis data.
     */
    @PostMapping("/addwaterAnalysis/{id}/{waterAnalysisId}")
    public ResponseEntity<?> addWaterAnalysis(@PathVariable int id, @PathVariable int waterAnalysisId) {
        try {
            WaterAnalysisDto waterAnalysis = waterAnalysisService.getWaterAnalysisById(waterAnalysisId);
            if (waterAnalysis == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Water Analysis not found");
            }
            return ResponseEntity.ok(userService.addWaterAnalysis(id, waterAnalysisId));
        } catch (UserNotFoundException | RoleNotAcepted | RetrofitException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("userfavs/{idUser}/{idFountain}")
    public ResponseEntity<?> isFountainFavorite(@PathVariable int idUser, @PathVariable int idFountain)
    {
        try{
            return ResponseEntity.ok(userService.isFountainFavorite(idUser, idFountain));
        }catch (RoleNotAcepted | ParamException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
