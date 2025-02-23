package io.reflectoring.Sprint3SpringBoot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "[statistics]")
public class Statistics {
    @Id
    private int id;

    double averageRadonLevel;
    double maxRadonLevel;
    double minRadonLevel;
    int totalAnalysis;
    LocalDate date;

    public Statistics(double average, double max, double min, int totalAnalysis, LocalDate date) {
        this.averageRadonLevel = average;
        this.maxRadonLevel = max;
        this.minRadonLevel = min;
        this.totalAnalysis = totalAnalysis;
        this.date = date;
    }
}
