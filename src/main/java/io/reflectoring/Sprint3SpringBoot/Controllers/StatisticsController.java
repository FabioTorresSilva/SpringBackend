package io.reflectoring.Sprint3SpringBoot.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.StatisticsDto;
import io.reflectoring.Sprint3SpringBoot.Models.Statistics;
import io.reflectoring.Sprint3SpringBoot.Services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createStatistics() {
        try{
            //String s = "ssss";
            //return ResponseEntity.status(HttpStatus.OK).body(s);
            Statistics statistics = statisticsService.createStatistics(LocalDate.now());
            return ResponseEntity.status(HttpStatus.OK).body(statistics);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
