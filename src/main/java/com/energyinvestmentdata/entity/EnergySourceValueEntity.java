package com.energyinvestmentdata.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Rabiu Ademoh
 */
@Entity
@Table( name = "energy_source_value")
public class EnergySourceValueEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer percentage;

    @Column
    private Long energySourceId;

    @Column
    private Long energyProjectId;

//    @ManyToOne(cascade = CascadeType.ALL )
//    @JoinColumn(name = "energy_source_id" , referencedColumnName = "id")
//    RenewableEnergySourceEntity renewableEnergySourceEntity;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "energy_project_id" , referencedColumnName = "id")
//    RenewableEnergyProjectEntity renewableEnergyProjectEntity;

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

//    public RenewableEnergySourceEntity getRenewableEnergySourceEntity() {
//        return renewableEnergySourceEntity;
//    }
//
//    public void setRenewableEnergySourceEntity(RenewableEnergySourceEntity renewableEnergySourceEntity) {
//        this.renewableEnergySourceEntity = renewableEnergySourceEntity;
//    }
//
//    public RenewableEnergyProjectEntity getRenewableEnergyProjectEntity() {
//        return renewableEnergyProjectEntity;
//    }
//
//    public void setRenewableEnergyProjectEntity(RenewableEnergyProjectEntity renewableEnergyProjectEntity) {
//        this.renewableEnergyProjectEntity = renewableEnergyProjectEntity;
//    }

    public Long getEnergySourceId() {
        return energySourceId;
    }

    public void setEnergySourceId(Long energySourceId) {
        this.energySourceId = energySourceId;
    }

    public Long getEnergyProjectId() {
        return energyProjectId;
    }

    public void setEnergyProjectId(Long energyProjectId) {
        this.energyProjectId = energyProjectId;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnergySourceValueEntity that = (EnergySourceValueEntity) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;

    }

    @Override
    public int hashCode() {
        return (getId() != null ?  getId().hashCode() : 0);
    }*/
}
