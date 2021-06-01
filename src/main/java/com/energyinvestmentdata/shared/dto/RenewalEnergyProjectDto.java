package com.energyinvestmentdata.shared.dto;

import com.energyinvestmentdata.entity.PublicInstitutionsConnectedEntity;
import com.energyinvestmentdata.model.request.PublicInstitutionConnectedRes;
import com.energyinvestmentdata.model.response.EnergySourceValueRes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Rabiu Ademoh
 */
public class RenewalEnergyProjectDto implements Serializable {



    private String projectName;

    private Long companyId;

    private List<EnergySourceValueRes> energySource = new ArrayList<>();

    private Double Longitude;

    private Double latitude;

    private Double installedCapacity;

    private int connections_financial_close;

    private int co2_avoided;

    private int batteriesInstalled;

    private List<Integer> publicSector;

    private Double debtMix;

    private List<PublicInstitutionConnectedRes> publicInstitutionConnected;


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

    public List<Integer> getPublicSector() {
        return publicSector;
    }

    public void setPublicSector(List<Integer> publicSector) {
        this.publicSector = publicSector;
    }

    public Double getDebtMix() {
        return debtMix;
    }

    public void setDebtMix(Double debtMix) {
        this.debtMix = debtMix;
    }

    public List<PublicInstitutionConnectedRes> getPublicInstitutionConnected() {
        return publicInstitutionConnected;
    }

    public void setPublicInstitutionConnected(List<PublicInstitutionConnectedRes> publicInstitutionConnected) {
        this.publicInstitutionConnected = publicInstitutionConnected;
    }
}
