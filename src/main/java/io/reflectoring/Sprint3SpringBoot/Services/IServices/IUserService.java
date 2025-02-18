package io.reflectoring.Sprint3SpringBoot.Services.IServices;

import io.reflectoring.Sprint3SpringBoot.Models.User;

/**
 * Service interface for managing user operations.
 * This interface defines methods for getting, updating, and creating users.
 */
public interface IUserService {

    /**
     * Retrieves a user by its unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return A {@link User} object representing the user.
     */
    User getUserById(int id);

    /**
     * Updates an existing user with the provided data.
     *
     * @param id   The unique identifier of the user to be updated.
     * @param user A {@link User} object containing the updated user data.
     * @return The updated {@link User} object.
     */
    User updateUser(int id, User user);

    /**
     * Creates a new user.
     *
     * @param user A {@link User} object containing the data for the new user.
     * @return The newly created {@link User} object.
     */
    User createUser(User user);
}
