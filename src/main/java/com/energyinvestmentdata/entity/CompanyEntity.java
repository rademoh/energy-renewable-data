package com.energyinvestmentdata.entity;

import javax.persistence.*;
import java.util.*;

/**
 * @author Rabiu Ademoh
 */

@Entity( name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToMany( mappedBy = "companyEntity")
    private Set<RenewableEnergyProjectEntity> renewableEnergyProjectEntities  = new HashSet<>();

    @OneToMany( mappedBy = "companyEntity")
    private Set<UserEntity> userEntity  = new HashSet<>();

    @Column
    private Date createdAt;

    @Column
    private Date modifiedAt;

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

    public Set<RenewableEnergyProjectEntity> getRenewableEnergyProjectEntities() {
        return renewableEnergyProjectEntities;
    }

    public void setRenewableEnergyProjectEntities(Set<RenewableEnergyProjectEntity> renewableEnergyProjectEntities) {
        this.renewableEnergyProjectEntities = renewableEnergyProjectEntities;
    }

    public Set<UserEntity> getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(Set<UserEntity> userEntity) {
        this.userEntity = userEntity;
    }
}
