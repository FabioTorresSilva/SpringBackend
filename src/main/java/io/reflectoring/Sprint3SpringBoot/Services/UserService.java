package io.reflectoring.Sprint3SpringBoot.Services;

import io.reflectoring.Sprint3SpringBoot.Exceptions.ParamException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.RepositoryException;
import io.reflectoring.Sprint3SpringBoot.Exceptions.UserNotFoundException;
import io.reflectoring.Sprint3SpringBoot.Models.User;
import io.reflectoring.Sprint3SpringBoot.Repositories.UserRepository;
import io.reflectoring.Sprint3SpringBoot.Services.IServices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class for managing users.
 * This class implements the methods defined in the IUserService interface to perform CRUD operations on users.
 */
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    /**
     * Constructor for dependency injection.
     *
     * @param userRepository The repository for user operations.
     */
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
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
     * @throws ParamException If the provided ID does not match the user ID.
     * @throws RepositoryException If an error occurs while saving the updated user to the repository.
     */
    @Override
    public User updateUser(int id, User user) {

        if (id != user.id)
            throw new ParamException("Ids do not match.");

        if (userRepository.findUserByEmail(user.email) != null)
            throw new ParamException("Email already exists");

        User updateUser = getUserById(id);

        updateUser.name = user.name;
        updateUser.email = user.email;
        updateUser.password = user.password;

        try{
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

        try{
            userRepository.save(user);
        } catch (Exception e) {
            throw new RepositoryException("Error creating user in repository");
        }
        return user;
    }
}
