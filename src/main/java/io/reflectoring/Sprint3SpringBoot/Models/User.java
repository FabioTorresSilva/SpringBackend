package io.reflectoring.Sprint3SpringBoot.Models;

import io.reflectoring.Sprint3SpringBoot.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a user entity in the system.
 * This class is mapped to a database table using JPA annotations.
 * It includes user details such as name, email, password, and role.
 * By default, a new user is assigned the role of Manager.
 *
 * <p>Annotations used:
 * <ul>
 *   <li>{@link Entity} - Marks this class as a JPA entity.</li>
 *   <li>{@link Table} (optional) - Can be used to specify a custom table name.</li>
 *   <li>{@link Id} - Marks the primary key field.</li>
 *   <li>{@link GeneratedValue} - Configures automatic ID generation.</li>
 *   <li>{@link Getter} and {@link Setter} (from Lombok) - Automatically generate getter and setter methods.</li>
 * </ul>
 * </p>
 *
 */
@Entity
@Getter
@Setter
public class User {

    /**
     * Unique identifier for the user, generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    /**
     * Full name of the user.
     */
    public String name;

    /**
     * Email address of the user.
     */
    public String email;

    /**
     * Password for authentication.
     */
    public String password;

    /**
     * Role of the user in the system.
     * Default value is {@link Role#Manager}.
     */
    public Role role;

    /**
     * Constructs a new {@code User} with the specified name, email, and password.
     * The role is set to {@code Role.Manager} by default.
     *
     * @param name the full name of the user
     * @param email the email address of the user
     * @param password the password for the user
     */
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.Manager;
    }

    /**
     * Default constructor that initializes the user with the {@code Role.Manager}.
     */
    public User() {
        this.role = Role.Manager;
    }
}

