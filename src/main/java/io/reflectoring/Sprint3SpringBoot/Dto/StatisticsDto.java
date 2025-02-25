package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsDto {

    // Removido o static
    @JsonProperty("averageRadonLevel")
    private double average;

    @JsonProperty("maxRadonLevel")
    private double max;

    @JsonProperty("minRadonLevel")
    private double min;

    @JsonProperty("totalAnalysis")
    private int totalAnalyses;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    // Construtor vazio para o Jackson
    public StatisticsDto() {
    }

    // Construtor com parâmetros
    public StatisticsDto(double average, double max, double min, int totalAnalyses, LocalDate date) {
        this.average = average;
        this.max = max;
        this.min = min;
        this.totalAnalyses = totalAnalyses;
        this.date = date;
    }

    // Getters e Setters sem static
    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public int getTotalAnalyses() {
        return totalAnalyses;
    }

    public void setTotalAnalyses(int totalAnalyses) {
        this.totalAnalyses = totalAnalyses;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

