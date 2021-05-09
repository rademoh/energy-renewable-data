package com.energyinvestmentdata.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rabiu Ademoh
 */

@Entity( name = "renewable_energy_project")
public class RenewableEnergyProjectEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column( nullable = false)
    private String projectId;

    @Column ( unique = true)
    private String projectName;

    @ManyToOne()
    @JoinColumn(name = "company_id", referencedColumnName = "id", updatable = false , nullable = false)
    private CompanyEntity companyEntity;

    @OneToMany(mappedBy="renewableEnergyProjectEntity" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<EnergySourceValueEntity> energySourceValueEntitySet = new HashSet<>();

    private Double Longitude;

    private Double latitude;

    private Double installedCapacity;

    private int connections_financial_close;

    private int co2_avoided;

    private int batteriesInstalled;

    @OneToMany(cascade = { CascadeType.MERGE} , fetch = FetchType.EAGER)
    @JoinTable(
            name="energy_project_public_sector",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "public_sector_id", referencedColumnName = "id")
    )
    private Set<PublicSectorEntity> publicSectorEntityList;

    private Double debtMix;

    @OneToMany( mappedBy = "renewableEnergyProjectEntity")
    private Set<PublicInstitutionsConnected> publicInstitutionsConnected;

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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public CompanyEntity getCompanyEntity() {
        return companyEntity;
    }

    public void setCompanyEntity(CompanyEntity companyEntity) {
        this.companyEntity = companyEntity;
    }

    public Set<EnergySourceValueEntity> getEnergySourceValueEntitySet() {
        return energySourceValueEntitySet;
    }

    public void setEnergySourceValueEntitySet(Set<EnergySourceValueEntity> energySourceValueEntitySet) {
        this.energySourceValueEntitySet = energySourceValueEntitySet;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getInstalledCapacity() {
        return installedCapacity;
    }

    public void setInstalledCapacity(Double installedCapacity) {
        this.installedCapacity = installedCapacity;
    }

    public int getConnections_financial_close() {
        return connections_financial_close;
    }

    public void setConnections_financial_close(int connections_financial_close) {
        this.connections_financial_close = connections_financial_close;
    }

    public int getCo2_avoided() {
        return co2_avoided;
    }

    public void setCo2_avoided(int co2_avoided) {
        this.co2_avoided = co2_avoided;
    }

    public int getBatteriesInstalled() {
        return batteriesInstalled;
    }

    public void setBatteriesInstalled(int batteriesInstalled) {
        this.batteriesInstalled = batteriesInstalled;
    }

    public Set<PublicSectorEntity> getPublicSectorEntityList() {
        return publicSectorEntityList;
    }

    public void setPublicSectorEntityList(Set<PublicSectorEntity> publicSectorEntityList) {
        this.publicSectorEntityList = publicSectorEntityList;
    }

    public Double getDebtMix() {
        return debtMix;
    }

    public void setDebtMix(Double debtMix) {
        this.debtMix = debtMix;
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

    public Set<PublicInstitutionsConnected> getPublicInstitutionsConnected() {
        return publicInstitutionsConnected;
    }

    public void setPublicInstitutionsConnected(Set<PublicInstitutionsConnected> publicInstitutionsConnected) {
        this.publicInstitutionsConnected = publicInstitutionsConnected;
    }
}
