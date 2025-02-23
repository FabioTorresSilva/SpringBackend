package io.reflectoring.Sprint3SpringBoot.Services;

import io.reflectoring.Sprint3SpringBoot.Dto.StatisticsDto;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.WaterAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticsService{


    private final WaterAnalysisService waterAnalysisService;

    @Autowired
    public StatisticsService(WaterAnalysisService waterAnalysisService) {
        this.waterAnalysisService = waterAnalysisService;

    }
    /**
     * Calculates statistics (average, max, min, and total count) for water analyses.
     * If a date is provided, it filters the analyses by that date.
     *
     * @param date The date to filter the analyses. If null, all analyses are considered.
     * @return A StatisticsDto object containing the calculated statistics.
     * @throws IllegalArgumentException If the WaterAnalysisService returns null or an error occurs during processing.
     */
    public StatisticsDto createStatistics(LocalDate date) {
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
             return new StatisticsDto(0, 0, 0, 0, date);

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
            return  new StatisticsDto(average, max, min, analyses.size(), date);

            } catch (Exception e) {
            // Log the exception and rethrow it as a runtime exception
            throw new IllegalArgumentException("An error occurred while creating statistics: " + e.getMessage(), e);
        }
    }
}

