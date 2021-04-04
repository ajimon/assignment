package com.ul.api;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Ajimon.Sanku
 */
@SpringBootApplication
@EnableSwagger2
public class BackendApp {
    /**
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(BackendApp.class, args);
    }

    /**
     * @return {@link Docket}
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    /**
     * @return {@link ApiInfo}
     */
    private ApiInfo apiInfo() {
        return new ApiInfo("REST API", "API.", "API ", "Terms of service",
                new springfox.documentation.service.Contact("Demo",
                        "www.demo.com", "myeaddress@demo.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
