package io.reflectoring.Sprint3SpringBoot.Models;

import io.reflectoring.Sprint3SpringBoot.Enums.Role;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a Tester, which extends the {@link User} class.
 * A Tester is responsible for conducting water analysis.
 */
@Entity
@Getter
@Setter
public class Tester extends User {

    /**
     * A transient list of water analyses associated with the Tester.
     * This field is not persisted in the database.
     */
    @Transient
    public List<WaterAnalysisDto> waterAnalysis;

    /**
     * Constructs a new Tester with the specified name, email, and password.
     * The role is automatically set to {@link Role#Tester}, and an empty list of water analyses is initialized.
     *
     * @param name     The name of the Tester.
     * @param email    The email address of the Tester.
     * @param password The password of the Tester.
     */
    public Tester(String name, String email, String password) {
        super(name, email, password);
        role = Role.Tester;
        waterAnalysis = new ArrayList<>();
    }

    /**
     * Default constructor for creating a Tester instance.
     * The role is automatically set to {@link Role#Tester}.
     */
    public Tester() {
        role = Role.Tester;
    }
}
