package io.reflectoring.Sprint3SpringBoot.Services;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import io.reflectoring.Sprint3SpringBoot.Models.Statistics;
import io.reflectoring.Sprint3SpringBoot.Repositories.StatisticsRepository;
import io.reflectoring.Sprint3SpringBoot.Retrofit.Service.WaterAnalysisService;
import io.reflectoring.Sprint3SpringBoot.Dto.WaterAnalysisDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

    @Mock
    private StatisticsRepository statisticsRepository;

    @Mock
    private WaterAnalysisService waterAnalysisService; // Mockando o WaterAnalysisService

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    public void testCreateStatistics() {
        LocalDate date = LocalDate.now();
        Statistics statistics = new Statistics(15.0, 20.0, 5.0, 100, date);

        WaterAnalysisDto waterAnalysis1 = new WaterAnalysisDto();
        waterAnalysis1.setRadonConcentration(15.0);
        waterAnalysis1.setDate(date);

        WaterAnalysisDto waterAnalysis2 = new WaterAnalysisDto();
        waterAnalysis2.setRadonConcentration(15.0);
        waterAnalysis2.setDate(date);

        // Retornando uma lista com um elemento WaterAnalysis
        when(waterAnalysisService.getAllWaterAnalyses()).thenReturn(List.of(waterAnalysis1, waterAnalysis2));

        // Mockando o comportamento do StatisticsRepository
        when(statisticsRepository.save(any(Statistics.class))).thenReturn(statistics);

        // Chamando o método a ser testado
        Statistics createdStatistics = statisticsService.createStatistics(date);

        // Verificações
        assertThat(createdStatistics).isNotNull();
        assertThat(createdStatistics.getAverageRadonLevel()).isEqualTo(15.0);
        assertThat(createdStatistics.getMaxRadonLevel()).isEqualTo(15.0);
        assertThat(createdStatistics.getMinRadonLevel()).isEqualTo(15.0);
        assertThat(createdStatistics.getTotalAnalysis()).isEqualTo(2);

        // Verificando se o método save() foi chamado apenas uma vez
        verify(statisticsRepository, times(1)).save(any(Statistics.class));
        // Verificando se o método getAllWaterAnalyses() foi chamado uma vez
        verify(waterAnalysisService, times(1)).getAllWaterAnalyses();
    }
}