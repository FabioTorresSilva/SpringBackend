package io.reflectoring.Sprint3SpringBoot.Models;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user entity in the system.
 * This class is mapped to a database table using JPA annotations.
 * It includes user details such as name, email, password, and role.
 * By default, a new user is assigned the role of Manager.
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@link Entity} - Marks this class as a JPA entity.</li>
 *   <li>{@link Table} - Specifies the database table name.</li>
 *   <li>{@link Id} - Marks the primary key field.</li>
 *   <li>{@link GeneratedValue} - Configures automatic ID generation.</li>
 *   <li>{@link Getter} and {@link Setter} (from Lombok) - Automatically generate getter and setter methods.</li>
 * </ul>
 *
 * @see io.reflectoring.Sprint3SpringBoot.Enums.Role
 * @see io.reflectoring.Sprint3SpringBoot.Dto.FountainDto
 * @see io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Unique identifier for the user, generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Full name of the user.
     */
    private String name;

    /**
     * Email address of the user.
     */
    private String email;

    /**
     * Password for authentication.
     */
    private String password;

    /**
     * Role of the user in the system.
     * Default value is {@link Role#Manager}.
     */
    private Role role;

    /**
     * List of favorite fountains (not persisted in the database).
     */
    @Transient
    private ArrayList<FountainDto> favourites;

    /**
     * List of water analyses associated with the user (not persisted in the database).
     */
    @Transient
    private List<WaterAnalysisDto> waterAnalysis;

    /**
     * Constructs a new {@code User} with the specified name, email, password, and role.
     *
     * @param name the full name of the user
     * @param email the email address of the user
     * @param password the password for the user
     * @param role the role assigned to the user
     */
    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Default constructor that initializes the user with the {@code Role.Manager}.
     */
    public User() {
        this.role = Role.Client;
    }

    /**
     * Gets the user's unique ID.
     *
     * @return the user's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the new user ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's full name.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email address.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's role.
     *
     * @return the user's role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the new role assigned to the user
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the user's favorite fountains.
     *
     * @return a list of favorite fountains
     */
    public ArrayList<FountainDto> getFavourites() {
        return favourites;
    }

    /**
     * Sets the user's favorite fountains.
     *
     * @param favourites the list of new favorite fountains
     */
    public void setFavourites(ArrayList<FountainDto> favourites) {
        this.favourites = favourites;
    }

    /**
     * Gets the list of water analyses associated with the user.
     *
     * @return a list of water analyses
     */
    public List<WaterAnalysisDto> getWaterAnalysis() {
        return waterAnalysis;
    }

    /**
     * Sets the list of water analyses associated with the user.
     *
     * @param waterAnalysis the new list of water analyses
     */
    public void setWaterAnalysis(List<WaterAnalysisDto> waterAnalysis) {
        this.waterAnalysis = waterAnalysis;
    }
}
