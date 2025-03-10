package io.reflectoring.Sprint3SpringBoot.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Config.LocalDateDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "[statistics]")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public double averageRadonLevel;
    public double maxRadonLevel;
    public double minRadonLevel;
    public int totalAnalysis;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate date;

    public Statistics(double average, double max, double min, int totalAnalysis, LocalDate date) {
        this.averageRadonLevel = average;
        this.maxRadonLevel = max;
        this.minRadonLevel = min;
        this.totalAnalysis = totalAnalysis;
        this.date = date;
    }

    public Statistics() {

    }
}
