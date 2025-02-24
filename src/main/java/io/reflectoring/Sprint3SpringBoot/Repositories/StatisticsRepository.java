package io.reflectoring.Sprint3SpringBoot.Repositories;

import io.reflectoring.Sprint3SpringBoot.Models.Statistics;
import io.reflectoring.Sprint3SpringBoot.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Integer> {
}
