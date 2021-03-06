package com.energyinvestmentdata.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Rabiu Ademoh
 */

@Entity(  name = "public_institutions_connected")
public class PublicInstitutionsConnectedEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column( name = "institutions_connected")
    private Integer institutionsConnected;

    //@ManyToOne(cascade = CascadeType.ALL )
    @ManyToOne()
    @JoinColumn(name = "energy_project_id" , referencedColumnName = "id")
    RenewableEnergyProjectEntity renewableEnergyProjectEntity;

    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne()
    @JoinColumn(name = "public_institution_id" , referencedColumnName = "id")
    PublicInstitutionsEntity publicInstitutionsEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInstitutionsConnected() {
        return institutionsConnected;
    }

    public void setInstitutionsConnected(Integer institutionsConnected) {
        this.institutionsConnected = institutionsConnected;
    }

    public RenewableEnergyProjectEntity getRenewableEnergyProjectEntity() {
        return renewableEnergyProjectEntity;
    }

    public void setRenewableEnergyProjectEntity(RenewableEnergyProjectEntity renewableEnergyProjectEntity) {
        this.renewableEnergyProjectEntity = renewableEnergyProjectEntity;
    }

    public PublicInstitutionsEntity getPublicInstitutionsEntity() {
        return publicInstitutionsEntity;
    }

    public void setPublicInstitutionsEntity(PublicInstitutionsEntity publicInstitutionsEntity) {
        this.publicInstitutionsEntity = publicInstitutionsEntity;
    }
}
