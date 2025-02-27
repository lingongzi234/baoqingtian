package com.tjpu22t01.respondent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean(value = "dockerBean")
    public Docket dockerBean(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("被申请人")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tjpu22t01.respondent"))
                .paths(PathSelectors.any())
                .build();
    }
}