package com.energyinvestmentdata.shared.dto;



/**
 * @author Rabiu Ademoh
 */
public class EnergySourceValueDto {

    private Long id;

    private Integer percentage;

    private RenewalEnergyProjectDto renewalEnergyProjectDto;

    private RenewalEnergySourceDto renewalEnergySourceDto;

    public RenewalEnergySourceDto getRenewalEnergySourceDto() {
        return renewalEnergySourceDto;
    }

    public void setRenewalEnergySourceDto(RenewalEnergySourceDto renewalEnergySourceDto) {
        this.renewalEnergySourceDto = renewalEnergySourceDto;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public RenewalEnergyProjectDto getRenewalEnergyProjectDto() {
        return renewalEnergyProjectDto;
    }

    public void setRenewalEnergyProjectDto(RenewalEnergyProjectDto renewalEnergyProjectDto) {
        this.renewalEnergyProjectDto = renewalEnergyProjectDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
