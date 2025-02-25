package io.reflectoring.Sprint3SpringBoot.Services;

import io.reflectoring.Sprint3SpringBoot.Dto.StatisticsDto;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Exceptions.UserNotFoundException;
import io.reflectoring.Sprint3SpringBoot.Models.Statistics;
import io.reflectoring.Sprint3SpringBoot.Models.User;
import io.reflectoring.Sprint3SpringBoot.Repositories.StatisticsRepository;
import io.reflectoring.Sprint3SpringBoot.Repositories.UserRepository;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.WaterAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService{


    private final WaterAnalysisService waterAnalysisService;
    private final StatisticsRepository statisticsRepository;
    private final UserRepository userRepository;

    @Autowired
    public StatisticsService(WaterAnalysisService waterAnalysisService, StatisticsRepository statisticsRepository, UserRepository userRepository) {
        this.waterAnalysisService = waterAnalysisService;
        this.statisticsRepository = statisticsRepository;
        this.userRepository = userRepository;
    }
    /**
     * Calculates statistics (average, max, min, and total count) for water analyses.
     * If a date is provided, it filters the analyses by that date.
     *
     * @param date The date to filter the analyses. If null, all analyses are considered.
     * @return A StatisticsDto object containing the calculated statistics.
     * @throws IllegalArgumentException If the WaterAnalysisService returns null or an error occurs during processing.
     */
    public Statistics createStatistics(LocalDate date) {
        try {
            // Get all water analyses from the service

            List<WaterAnalysisDto> allAnalyses = waterAnalysisService.getAllWaterAnalyses();

            // Check if the list of analyses is null (service error)
            if (allAnalyses == null) {
                throw new IllegalArgumentException("WaterAnalysisService returned null.");
            }
            // Filter analyses by date if a date is provided
            List<WaterAnalysisDto> analyses = date != null
                    ? allAnalyses.stream()
                    .filter(analysis -> analysis.getDate().equals(date))
                    .toList()
                    : allAnalyses;
            // If no analyses are found, return default statistics
            if (analyses.isEmpty()) {

                throw new IllegalArgumentException("WaterAnalysis list returned empty.");

            }

            // Calculate statistics
            double sum = 0;
            double max = Double.MIN_VALUE;
            double min = Double.MAX_VALUE;

            for (WaterAnalysisDto analysis : analyses) {
                double radonLevel = analysis.getRadonConcentration();
                sum += radonLevel;
                max = Math.max(max, radonLevel);
                min = Math.min(min, radonLevel);
            }

            double average = sum / analyses.size();
            Statistics statistics = new Statistics(average, max, min, analyses.size(), date);
            statisticsRepository.save(statistics);

            return  statistics;

            } catch (Exception e) {
            // Log the exception and rethrow it as a runtime exception
            throw new IllegalArgumentException("An error occurred while creating statistics: " + e.getMessage(), e);
        }
    }

    public List<Statistics> getMonthStatistics(int month) {

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month out of range: " + month);
        }

        List<Statistics> all = statisticsRepository.findAll();

        List<Statistics> monthStatistics = new ArrayList<>();
        for (Statistics statistics : all) {
            if (statistics.date.getMonthValue() == month && statistics.date.getYear() == LocalDate.now().getYear()) {
                monthStatistics.add(statistics);
            }
        }

        return monthStatistics;
    }

    public List<Statistics> getYearStatistics(int year) {

        if (year < 1900 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Month out of range: " + year);
        }

        List<Statistics> all = statisticsRepository.findAll();

        List<Statistics> yearStatistics = new ArrayList<>();
        for (Statistics statistics : all) {
            if (statistics.date.getYear() == year) {
                yearStatistics.add(statistics);
            }
        }

        return yearStatistics;
    }

    public Statistics getStatisticsById(int id) {
        Statistics statistics = statisticsRepository.findById(id);

        if (statistics == null) {
            throw new UserNotFoundException("statistics with ID " + id + " not found.");
        }

        return statistics;
    }

}

