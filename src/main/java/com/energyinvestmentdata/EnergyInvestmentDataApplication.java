package com.energyinvestmentdata;

import com.energyinvestmentdata.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EnergyInvestmentDataApplication {



    public static void main(String[] args) {
        SpringApplication.run(EnergyInvestmentDataApplication.class, args);
    }


    @Bean(name = "AppProperties")
    public AppProperties getAppProperties(){
        return  new AppProperties();
    }

}
