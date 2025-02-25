package io.reflectoring.Sprint3SpringBoot.Repositories;

import static org.assertj.core.api.Assertions.assertThat;

import io.reflectoring.Sprint3SpringBoot.Models.Statistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

//@DataJpaTest
@SpringBootTest
@AutoConfigureMockMvc
public class StatisticsRepositoryTest {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Test
    public void testSaveAndFindById() {
        LocalDate date = LocalDate.now();
        Statistics statistics = new Statistics(10.0, 20.0, 5.0, 100, date);
        statisticsRepository.save(statistics);

        Optional<Statistics> foundStatistics = statisticsRepository.findById(statistics.getId());

        assertThat(foundStatistics).isPresent();
        assertThat(foundStatistics.get().getAverageRadonLevel()).isEqualTo(10.0);
    }
}