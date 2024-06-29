package com.pimcenter.schedule;

import com.pimcenter.schedule.facade.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"com.pimcenter.schedule.repository"})
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.pimcenter.base.service"})
public class ScheduleApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ScheduleApplication.class, args);
        context.getBean(SampleService.class);
        LOGGER.info("BaseApplication start!");
        LOGGER.info("Spring Boot Version: {}", SpringApplication.class.getPackage().getImplementationVersion());
        LOGGER.info("BaseApplication classLoader:{} ", ScheduleApplication.class.getClassLoader());
    }
}
