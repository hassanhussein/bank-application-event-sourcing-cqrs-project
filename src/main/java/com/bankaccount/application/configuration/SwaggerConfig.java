package com.bankaccount.application.configuration;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket apiDocket(){
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(getApiInfo());
  }

  private ApiInfo getApiInfo(){
    return new ApiInfo(
        "Event Sourcing using Axon and Spring Boot",
        "Application to demonstrate Event Sourcing of banking app using Axon and Spring Boot",
        "1.0.0",
        "Terms of Service",
        "",
        "",
        "");
  }

}