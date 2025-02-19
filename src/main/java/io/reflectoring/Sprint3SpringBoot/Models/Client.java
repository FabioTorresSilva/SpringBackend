package io.reflectoring.Sprint3SpringBoot.Models;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Represents a client user in the system.
 * A client extends the {@link User} class and has a role of {@link Role#Client}.
 * Clients can also have a list of favorite fountains.
 */
@Entity
public class Client extends User {

    /**
     * A transient list of favorite fountains associated with the client.
     * This field is not persisted in the database.
     */
    @Transient
    public ArrayList<FountainDto> favourites;

    /**
     * Constructs a new Client with the specified name, email, and password.
     * The role is automatically set to {@link Role#Client}, and the favorites list is initialized.
     *
     * @param name     The name of the client.
     * @param email    The email of the client.
     * @param password The password of the client.
     */
    public Client(String name, String email, String password) {
        super(name, email, password);
        role = Role.Client;
        favourites = new ArrayList<>();
    }

    /**
     * Default constructor.
     * Initializes the client role to {@link Role#Client}.
     */
    public Client() {
        role = Role.Client;
    }

    public ArrayList<FountainDto> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<FountainDto> favourites) {
        this.favourites = favourites;
    }
}
