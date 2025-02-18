package io.reflectoring.Sprint3SpringBoot.Repositories;

import io.reflectoring.Sprint3SpringBoot.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
