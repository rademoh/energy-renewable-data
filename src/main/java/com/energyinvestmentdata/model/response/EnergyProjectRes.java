package com.energyinvestmentdata.model.response;

import com.energyinvestmentdata.entity.PublicInstitutionsConnectedEntity;
import com.energyinvestmentdata.entity.PublicSectorEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Rabiu Ademoh
 */
public class EnergyProjectRes implements Serializable {


    private Long id;

    private String projectName;

    private String companyName;

    private List<EnergySourceValueRes> energySource = new ArrayList<>();

    private Double Longitude;

    private Double latitude;

    private Double installedCapacity;

    private int connections_financial_close;

    private int co2_avoided;

    private int batteriesInstalled;

    private List<PublicSectorRes> publicSector;

    private Double debtMix;

    private List<PublicInstitutionsConnectedRes> publicInstitutionsConnected;

    public EnergyProjectRes(Long id, String projectName, String companyName, List<EnergySourceValueRes> energySource, Double longitude, Double latitude, Double installedCapacity, int connections_financial_close, int co2_avoided, int batteriesInstalled, List<PublicSectorRes> publicSector, Double debtMix, List<PublicInstitutionsConnectedRes> publicInstitutionsConnected) {
        this.id = id;
        this.projectName = projectName;
        this.companyName = companyName;
        this.energySource = energySource;
        Longitude = longitude;
        this.latitude = latitude;
        this.installedCapacity = installedCapacity;
        this.connections_financial_close = connections_financial_close;
        this.co2_avoided = co2_avoided;
        this.batteriesInstalled = batteriesInstalled;
        this.publicSector = publicSector;
        this.debtMix = debtMix;
        this.publicInstitutionsConnected = publicInstitutionsConnected;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public List<PublicSectorRes> getPublicSector() {
        return publicSector;
    }

    public void setPublicSector(List<PublicSectorRes> publicSector) {
        this.publicSector = publicSector;
    }

    public Double getDebtMix() {
        return debtMix;
    }

    public void setDebtMix(Double debtMix) {
        this.debtMix = debtMix;
    }

    public List<PublicInstitutionsConnectedRes> getPublicInstitutionsConnected() {
        return publicInstitutionsConnected;
    }

    public void setPublicInstitutionsConnected(List<PublicInstitutionsConnectedRes> publicInstitutionsConnected) {
        this.publicInstitutionsConnected = publicInstitutionsConnected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
