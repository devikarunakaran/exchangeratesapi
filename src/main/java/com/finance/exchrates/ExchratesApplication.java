package com.finance.exchrates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.jdbc.DataSourcePoolMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DataSourcePoolMetricsAutoConfiguration.class})
@Configuration
public class ExchratesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExchratesApplication.class, args);
    }

}
