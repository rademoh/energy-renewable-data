package com.energyinvestmentdata;

import com.energyinvestmentdata.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

@EnableSwagger2
@SpringBootApplication
public class EnergyInvestmentDataApplication {



    public static void main(String[] args) {
        SpringApplication.run(EnergyInvestmentDataApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/v1/**"))
                .apis(RequestHandlerSelectors.basePackage("com.energyinvestmentdata"))
                .build()
                .apiInfo(apiDetails())
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Energy Saving Data API",
                "",
                "1.0",
                "",
                new Contact("Rabiu Ademoh","www.notready.com","rademoh@yahoo.com"),
                "",
                "",
                Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }



    @Bean(name = "AppProperties")
    public AppProperties getAppProperties(){
        return  new AppProperties();
    }

}
