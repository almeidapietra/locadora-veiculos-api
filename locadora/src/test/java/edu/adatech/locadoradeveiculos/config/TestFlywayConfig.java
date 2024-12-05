package edu.adatech.locadoradeveiculos.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestFlywayConfig {

    private final Flyway flyway;

    public TestFlywayConfig(Flyway flyway) {
        this.flyway = flyway;
    }

    @Bean
    public void initializeFlyway() {
        flyway.clean();
        flyway.migrate();
    }
}
