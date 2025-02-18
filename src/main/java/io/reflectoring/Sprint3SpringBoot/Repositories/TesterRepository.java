package io.reflectoring.Sprint3SpringBoot.Repositories;

import io.reflectoring.Sprint3SpringBoot.Models.Tester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesterRepository extends JpaRepository<Tester, Integer> {
}
