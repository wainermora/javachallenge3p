package com.challlenge.demo.configuration.datasource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.challlenge.demo.repositories"}
)
@EntityScan("com.challlenge.demo.entities")
public class PersistenceJPAConfig {
}
