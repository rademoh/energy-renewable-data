package com.energyinvestmentdata.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Rabiu Ademoh
 */

@Entity( name = "public_institution")
public class PublicInstitutionsEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    private RenewableEnergyProjectEntity renewableEnergyProjectEntity;

    @Column
    private Date createdAt;

    @Column
    private Date modifiedAt;
}
