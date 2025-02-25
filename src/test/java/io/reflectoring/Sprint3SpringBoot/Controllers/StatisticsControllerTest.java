package io.reflectoring.Sprint3SpringBoot.Controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import io.reflectoring.Sprint3SpringBoot.Models.Statistics;
import io.reflectoring.Sprint3SpringBoot.Services.CustomUserDetailsService;
import io.reflectoring.Sprint3SpringBoot.Services.StatisticsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

//@WebMvcTest(StatisticsController.class)
@SpringBootTest
@AutoConfigureMockMvc

public class StatisticsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void testCreateStatistics() throws Exception {
        LocalDate date = LocalDate.now();
        Statistics statistics = new Statistics(10.0, 20.0, 5.0, 100, date);

        when(statisticsService.createStatistics(date)).thenReturn(statistics);

        mockMvc.perform(post("/api/statistics"))
//                .with(csrf()) // Adiciona o token CSRF à requisição
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageRadonLevel").value(10.0))
                .andExpect(jsonPath("$.maxRadonLevel").value(20.0))
                .andExpect(jsonPath("$.minRadonLevel").value(5.0))
                .andExpect(jsonPath("$.totalAnalysis").value(100));
    }
}