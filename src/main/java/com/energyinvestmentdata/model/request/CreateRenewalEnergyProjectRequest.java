package com.energyinvestmentdata.model.request;

import com.energyinvestmentdata.entity.PublicInstitutionsConnectedEntity;
import com.energyinvestmentdata.model.response.EnergySourceValueRes;
import com.energyinvestmentdata.shared.dto.EnergySourceValueDto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Rabiu Ademoh
 */
public class CreateRenewalEnergyProjectRequest {


    @NotBlank(message = "Project name is required")
    private String projectName;

    private Long companyId;

    private List<EnergySourceValueRes> energySource  = new ArrayList<>();

   //@NotNull(message = "Longitude name is required")
    private Double longitude;

   //@NotNull(message = "Project name is required")
    private Double latitude;

    //@NotNull(message = "Installed Capacity name is required")
    private Double installedCapacity;

   // @NotNull(message = "Connection Financial Close is required")
    private int connections_financial_close;

  //  @NotNull(message = "C02 avoided is required")
    private int co2_avoided;

   // @NotNull(message = "Batteries Installed is required")
    private int batteriesInstalled;

    private Set<Integer> publicSector;

    private Double debtMix;

    private Set<PublicInstitutionConnectedRes> publicInstitutionConnected;


    public Set<Integer> getPublicSector() {
        return publicSector;
    }

    public void setPublicSector(Set<Integer> publicSector) {
        this.publicSector = publicSector;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<EnergySourceValueRes> getEnergySource() {
        return energySource;
    }

    public void setEnergySource(List<EnergySourceValueRes> energySource) {
        this.energySource = energySource;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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


    public Double getDebtMix() {
        return debtMix;
    }

    public void setDebtMix(Double debtMix) {
        this.debtMix = debtMix;
    }

    public Set<PublicInstitutionConnectedRes> getPublicInstitutionConnected() {
        return publicInstitutionConnected;
    }

    public void setPublicInstitutionConnected(Set<PublicInstitutionConnectedRes> publicInstitutionConnected) {
        this.publicInstitutionConnected = publicInstitutionConnected;
    }

 // 1. Entry point custome exception
    // 2. roles and privileges exception customs



    ///load test
    //functional test
}
