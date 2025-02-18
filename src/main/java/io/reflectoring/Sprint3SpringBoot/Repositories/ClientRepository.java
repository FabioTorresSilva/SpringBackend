package io.reflectoring.Sprint3SpringBoot.Repositories;

import io.reflectoring.Sprint3SpringBoot.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
