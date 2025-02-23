package io.reflectoring.Sprint3SpringBoot.Services;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Enums.Role;
import io.reflectoring.Sprint3SpringBoot.Exceptions.*;
import io.reflectoring.Sprint3SpringBoot.Models.User;
import io.reflectoring.Sprint3SpringBoot.Repositories.UserRepository;
import io.reflectoring.Sprint3SpringBoot.Retrofit.IService.IFountainService;
import io.reflectoring.Sprint3SpringBoot.Services.IServices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 * This class implements the methods defined in the IUserService interface to perform CRUD operations on users.
 */
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final IFountainService fountainService;

    /**
     * Constructor for dependency injection.
     *
     * @param userRepository The repository for user operations.
     */
    @Autowired
    public UserService(UserRepository userRepository, IFountainService fountainService) {
        this.userRepository = userRepository;
        this.fountainService = fountainService;
    }

    /**
     * Retrieves a user by its unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return A {@link User} object representing the user, or null if the user is not found.
     */
    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        return user.get();
    }

    /**
     * Updates an existing user with the provided data.
     * This method ensures that the provided ID matches the ID of the user to be updated,
     * retrieves the user from the database, updates the fields, and saves the changes.
     * If the provided ID does not match the user's ID, a {@link ParamException} will be thrown.
     * If an error occurs during saving to the repository, a {@link RepositoryException} will be thrown.
     *
     * @param id   The unique identifier of the user to be updated.
     * @param user A {@link User} object containing the updated user data.
     * @return The updated {@link User} object.
     * @throws ParamException      If the provided ID does not match the user ID.
     * @throws RepositoryException If an error occurs while saving the updated user to the repository.
     */
    @Override
    public User updateUser(int id, User user) {

        if (id != user.getId())
            throw new ParamException("Ids do not match.");

        if (userRepository.findUserByEmail(user.getEmail()) != null)
            throw new ParamException("Email already exists");

        User updateUser = getUserById(id);

        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());

        try {
            userRepository.save(updateUser);
        } catch (Exception e) {
            throw new RepositoryException("Error updating repository");
        }

        return user;
    }

    /**
     * Creates a new user.
     *
     * @param user A {@link User} object containing the data for the new user.
     * @return The newly created {@link User} object.
     */
    @Override
    public User createUser(User user) {

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RepositoryException("Error creating user in repository");
        }
        return user;
    }

    /**
     * Retrieves a user's list of favorite fountains.
     *
     * @param id The unique identifier of the user.
     * @return A list of {@link FountainDto} representing the user's favorite fountains.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * @throws RoleNotAcepted        If the user is not a client.
     */
    @Override
    public List<FountainDto> getUserFavourites(int id) {
        User user = getUserById(id);

        if (user.getRole() != Role.Client)
            throw new RoleNotAcepted("User with ID " + id + " is not a client.");

        return user.getFavourites();
    }

    /**
     * Adds a fountain to a user's list of favorites.
     *
     * @param id          The unique identifier of the user.
     * @param fountainDto The fountain to be added as a favorite.
     * @return The added {@link FountainDto}.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * @throws RoleNotAcepted        If the user is not a client.
     * @throws ParamException        If the provided fountain is null.
     */
    @Override
    public FountainDto addFavourite(int id, FountainDto fountainDto) {

        if (fountainDto == null)
            throw new ParamException("Fountain does not exist");

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        if (user.get().getRole() != Role.Client)
            throw new RoleNotAcepted("User with ID " + id + " is not a client.");

        User usr = user.get();
        usr.getFavourites().add(fountainDto);

        return fountainDto;
    }

    /**
     * Removes a fountain from a user's list of favorites.
     *
     * @param id          The unique identifier of the user.
     * @param fountainDto The fountain to be removed from favorites.
     * @return The removed {@link FountainDto}.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * @throws RoleNotAcepted        If the user is not a client.
     * @throws ParamException        If the provided fountain is null.
     */
    @Override
    public FountainDto removeFavourite(int id, FountainDto fountainDto) {
        if (fountainDto == null)
            throw new ParamException("Fountain does not exist");

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        if (user.get().getRole() != Role.Client)
            throw new RoleNotAcepted("User with ID " + id + " is not a client.");

        User usr = user.get();
        usr.getFavourites().remove(fountainDto);

        return fountainDto;
    }

    /**
     * Retrieves a tester's list of water analyses.
     *
     * @param id The unique identifier of the tester.
     * @return A list of {@link WaterAnalysisDto} representing the tester's water analyses.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * @throws RoleNotAcepted        If the user is not a tester.
     */
    @Override
    public List<WaterAnalysisDto> getTesterWaterAnalysis(int id) {
        User user = getUserById(id);

        if (user.getRole() != Role.Tester)
            throw new RoleNotAcepted("User with ID " + id + " is not a tester.");

        return user.getWaterAnalysis();
    }

    /**
     * Adds a water analysis to a tester's records.
     *
     * @param id               The unique identifier of the tester.
     * @param waterAnalysisDto The water analysis to be added.
     * @return The added {@link WaterAnalysisDto}.
     * @throws UserNotFoundException If the tester with the given ID is not found.
     * @throws RoleNotAcepted        If the user is not a tester.
     * @throws ParamException        If the provided water analysis is null.
     */
    @Override
    public WaterAnalysisDto addWaterAnalysis(int id, WaterAnalysisDto waterAnalysisDto) {

        if (waterAnalysisDto == null) {
            throw new ParamException("Water analysis does not exist.");
        }

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("Tester with ID " + id + " not found.");
        }

        if (user.get().getRole() != Role.Tester)
            throw new RoleNotAcepted("User is not a tester.");

        User usr = user.get();
        usr.getWaterAnalysis().add(waterAnalysisDto);

        return waterAnalysisDto;
    }

    /**
     * Retrieves all users with the specified role.
     *
     * @param role The role of the users to be retrieved.
     * @return A list of {@link User} objects with the specified role.
     * @throws UserNotFoundException If no users with the given role are found.
     */
    @Override
    public List<User> getAllUsersByRole(Role role) {

        List<User> allUsers = userRepository.findAll();

        if (allUsers.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        }

        List<User> usersRole = new ArrayList<>();

        for (User u : allUsers) {
            if (u.getRole() == role)
                usersRole.add(u);
        }

        if (usersRole.isEmpty()) {
            throw new UserNotFoundException("No users with role '" + role + "' found.");
        }

        return usersRole;
    }

    /**
     * Retrieves a list of the user's favorite fountains, up to a specified limit.
     *
     * @param id The unique identifier of the user whose favorites are being retrieved.
     * @param i  The maximum number of favorite fountains to return.
     * @return A list of {@link FountainDto} objects representing the user's favorite fountains.
     * @throws ParamException If the provided limit {@code i} is less than or equal to 0.
     * @throws RoleNotAcepted If the user is not a client.
     */
    @Override
    public List<FountainDto> getXFavourites(int id, int i) {
        List<FountainDto> listFavourites = new ArrayList<>();

        User user = getUserById(id);

        if (i <= 0)
            throw new ParamException("Param invalid.");

        if (user.getRole() != Role.Client)
            throw new RoleNotAcepted("User with ID " + id + " is not a client.");

        int j = 0;
        for (FountainDto f : user.getFavourites()) {
            listFavourites.add(f);
            j++;
            if (j == i)
                break;
        }

        return listFavourites;
    }

    @Override
    public boolean isFountainFavorite(int idUser, int idFountain) {

        User user = getUserById(idUser);

        if (user.getRole() != Role.Client)
            throw new RoleNotAcepted("User with ID " + idUser + " is not a client.");

        try {
            Response<FountainDto> fountainDto = fountainService.getFountainById(idFountain).execute();

            if ((fountainDto.isSuccessful()))
                if (fountainDto.body() == null)
                    throw new ParamException("Fountain does not exist.");
                else {
                    throw new RetrofitException("Error fetching fountains " + fountainDto.code());
                }
            for (FountainDto f : user.getFavourites()) {
                if (f.getId() == idFountain)
                    return true;
            }
            return false;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}