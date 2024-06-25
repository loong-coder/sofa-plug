package com.pimcenter.base;

import com.pimcenter.base.facade.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = {"com.pimcenter.base.repository"})
@SpringBootApplication(exclude = {
        ElasticsearchRepositoriesAutoConfiguration.class,
        ElasticsearchDataAutoConfiguration.class
})
public class BaseApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BaseApplication.class, args);
        context.getBean(SampleService.class);
        LOGGER.info("BaseApplication start!");
        LOGGER.info("Spring Boot Version: {}", SpringApplication.class.getPackage().getImplementationVersion());
        LOGGER.info("BaseApplication classLoader:{} ", BaseApplication.class.getClassLoader());
    }
}
