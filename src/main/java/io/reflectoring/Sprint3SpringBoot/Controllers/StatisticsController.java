package io.reflectoring.Sprint3SpringBoot.Controllers;

import io.reflectoring.Sprint3SpringBoot.Dto.StatisticsDto;
import io.reflectoring.Sprint3SpringBoot.Mapper.StatisticsMapper;
import io.reflectoring.Sprint3SpringBoot.Models.Statistics;
import io.reflectoring.Sprint3SpringBoot.Services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsMapper statisticsMapper;

    @Autowired
    public StatisticsController(StatisticsService statisticsService, StatisticsMapper statisticsMapper) {
        this.statisticsService = statisticsService;
        this.statisticsMapper = statisticsMapper;
    }

    @PostMapping
    public ResponseEntity<?> createStatistics() {
        try{
            //String s = "ssss";
            //return ResponseEntity.status(HttpStatus.OK).body(s);
            Statistics statistics = statisticsService.createStatistics(LocalDate.now());
            return ResponseEntity.status(HttpStatus.OK).body(statisticsMapper.StatisticstoDto(statistics));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/bymonth/{month}")
    public ResponseEntity<?> getMonthStatistics(@PathVariable int month)
    {
        try{
            List<Statistics> statistics = statisticsService.getMonthStatistics(month);

            return ResponseEntity.ok(statistics);
        } catch (IllegalArgumentException  e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/byyear/{year}")
    public ResponseEntity<?> getYearStatistics(@PathVariable int year)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getYearStatistics(year));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatisticsById(@PathVariable int id)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(statisticsService.getStatisticsById(id));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
