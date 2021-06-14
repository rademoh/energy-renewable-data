package com.energyinvestmentdata.model.response;


/**
 * @author Rabiu Ademoh
 */
public class EnergySourceValueRes {

    private Long id;

    private Integer percentage;

    private String name;

    public EnergySourceValueRes(Long id, Integer percentage, String name) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
