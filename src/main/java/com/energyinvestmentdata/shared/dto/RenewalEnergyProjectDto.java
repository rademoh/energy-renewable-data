package com.energyinvestmentdata.shared.dto;

import com.energyinvestmentdata.entity.CompanyEntity;
import com.energyinvestmentdata.entity.EnergySourceValueEntity;
import com.energyinvestmentdata.entity.PublicInstitutionsConnected;
import com.energyinvestmentdata.entity.PublicSectorEntity;
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

    private List<EnergySourceValueDto> energySourceValueDto = new ArrayList<>();

    private Double Longitude;

    private Double latitude;

    private Double installedCapacity;

    private int connections_financial_close;

    private int co2_avoided;

    private int batteriesInstalled;

    private Set<PublicSectorEntity> publicSectorEntityList;

    private Double debtMix;

    private Set<PublicInstitutionsConnected> publicInstitutionsConnected;


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

    public List<EnergySourceValueDto> getEnergySourceValueDto() {
        return energySourceValueDto;
    }

    public void setEnergySourceValueDto(List<EnergySourceValueDto> energySourceValueDto) {
        this.energySourceValueDto = energySourceValueDto;
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

    public Set<PublicInstitutionsConnected> getPublicInstitutionsConnected() {
        return publicInstitutionsConnected;
    }

    public void setPublicInstitutionsConnected(Set<PublicInstitutionsConnected> publicInstitutionsConnected) {
        this.publicInstitutionsConnected = publicInstitutionsConnected;
    }


}
