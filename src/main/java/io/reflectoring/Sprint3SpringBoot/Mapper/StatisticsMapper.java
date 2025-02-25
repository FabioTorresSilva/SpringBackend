package io.reflectoring.Sprint3SpringBoot.Mapper;


import io.reflectoring.Sprint3SpringBoot.Dto.StatisticsDto;
import io.reflectoring.Sprint3SpringBoot.Models.Statistics;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticsMapper {

    public StatisticsMapper() {}

    public StatisticsDto StatisticstoDto(Statistics statistics) {
        return new StatisticsDto(statistics.averageRadonLevel, statistics.maxRadonLevel, statistics.minRadonLevel, statistics.totalAnalysis, statistics.date);
    }

    public List<StatisticsDto> ListtoDto(List<Statistics> statistics) {

        List<StatisticsDto> statisticsDto = new ArrayList<>();

        for (Statistics statistic: statistics) {
            statisticsDto.add(StatisticstoDto(statistic));
        }

        return statisticsDto;
    }
}
