package com.energyinvestmentdata.shared.dto;

import com.energyinvestmentdata.entity.EnergySourceValueEntity;

import java.util.Set;

/**
 * @author Rabiu Ademoh
 */
public class RenewalEnergySourceDto {

    private Long id;

    private String name;

   // private Set<EnergySourceValueEntity> energySourceValueEntitySet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Set<EnergySourceValueEntity> getEnergySourceValueEntitySet() {
        return energySourceValueEntitySet;
    }

    public void setEnergySourceValueEntitySet(Set<EnergySourceValueEntity> energySourceValueEntitySet) {
        this.energySourceValueEntitySet = energySourceValueEntitySet;
    }*/
}
