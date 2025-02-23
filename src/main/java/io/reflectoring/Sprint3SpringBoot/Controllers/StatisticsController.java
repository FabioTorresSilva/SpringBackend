package io.reflectoring.Sprint3SpringBoot.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.StatisticsDto;
import io.reflectoring.Sprint3SpringBoot.Services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping
    public StatisticsDto createStatistics(@RequestParam(required = false) LocalDate date) {
        return statisticsService.createStatistics(date);
    }
}
