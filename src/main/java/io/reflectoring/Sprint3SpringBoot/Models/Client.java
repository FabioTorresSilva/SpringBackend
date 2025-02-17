package io.reflectoring.Sprint3SpringBoot.Models;

import io.reflectoring.Sprint3SpringBoot.Dto.FountainDto;
import io.reflectoring.Sprint3SpringBoot.Enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Client extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int idClient;

    public List<FountainDto> favourites;

    public Client(String name, String email, String password) {
        super(name, email, password);
        role = Role.Client;
        favourites = new ArrayList<FountainDto>();
    }

    public Client() {
        role = Role.Client;
    }
}
