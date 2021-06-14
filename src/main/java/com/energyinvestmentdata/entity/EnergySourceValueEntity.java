package com.energyinvestmentdata.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Rabiu Ademoh
 */

@Entity( name = "energy_source_value")
public class EnergySourceValueEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer percentage;


    //@ManyToOne(cascade = CascadeType.ALL )
    @ManyToOne()
    @JoinColumn(name = "energy_source_id" , referencedColumnName = "id")
    RenewableEnergySourceEntity renewableEnergySourceEntity;

   // @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne()
    @JoinColumn(name = "energy_project_id" , referencedColumnName = "id")
    RenewableEnergyProjectEntity renewableEnergyProjectEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public RenewableEnergySourceEntity getRenewableEnergySourceEntity() {
        return renewableEnergySourceEntity;
    }

    public void setRenewableEnergySourceEntity(RenewableEnergySourceEntity renewableEnergySourceEntity) {
        this.renewableEnergySourceEntity = renewableEnergySourceEntity;
    }

    public RenewableEnergyProjectEntity getRenewableEnergyProjectEntity() {
        return renewableEnergyProjectEntity;
    }

    public void setRenewableEnergyProjectEntity(RenewableEnergyProjectEntity renewableEnergyProjectEntity) {
        this.renewableEnergyProjectEntity = renewableEnergyProjectEntity;
    }


}
