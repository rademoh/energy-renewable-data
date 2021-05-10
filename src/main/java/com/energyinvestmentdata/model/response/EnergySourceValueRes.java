package com.energyinvestmentdata.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rabiu Ademoh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnergySourceValueRes {

    private Integer percentage;

    private String name;



    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
