package com.energyinvestmentdata.model.response;


/**
 * @author Rabiu Ademoh
 */
public class EnergySourceValueRes {

    private Integer percentage;

    private String name;

    public EnergySourceValueRes(Integer percentage, String name) {
        this.percentage = percentage;
        this.name = name;
    }

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
