package io.reflectoring.Sprint3SpringBoot.Dto;

import java.time.LocalDate;

public class StatisticsDto {

    private static double average;
    private static double max;
    private static double min;
    private static int totalAnalyses;
    private static LocalDate date; // Opcional, usado para filtros por data

    public StatisticsDto() {
    }

    public StatisticsDto(double average, double max, double min, int totalAnalyses, LocalDate date) {
        this.average = average;
        this.max = max;
        this.min = min;
        this.totalAnalyses = totalAnalyses;
        this.date = date;
    }

    public static double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public static double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public static double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public static int getTotalAnalyses() {
        return totalAnalyses;
    }

    public void setTotalAnalyses(int totalAnalyses) {
        this.totalAnalyses = totalAnalyses;
    }

    public static LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

