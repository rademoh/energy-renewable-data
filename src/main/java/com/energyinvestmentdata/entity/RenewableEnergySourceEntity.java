package com.energyinvestmentdata.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rabiu Ademoh
 */

@Entity( name = "renewable_energy_source")
public class RenewableEnergySourceEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy="renewableEnergySourceEntity", cascade = CascadeType.ALL)
    private Set<EnergySourceValueEntity> energySourceValueEntitySet = new HashSet<>();

    @Column
    private Date createdAt;

    @Column
    private Date modifiedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.modifiedAt = new Date();
    }


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

    public Set<EnergySourceValueEntity> getEnergySourceValueEntitySet() {
        return energySourceValueEntitySet;
    }

    public void setEnergySourceValueEntitySet(Set<EnergySourceValueEntity> energySourceValueEntitySet) {
        this.energySourceValueEntitySet = energySourceValueEntitySet;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
