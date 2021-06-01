package com.energyinvestmentdata.service;

import com.energyinvestmentdata.model.request.EnergyProjectFilterRequest;
import com.energyinvestmentdata.model.request.FilterTestRequest;
import com.energyinvestmentdata.model.response.EnergyProjectRes;
import com.energyinvestmentdata.model.response.Pagination;
import com.energyinvestmentdata.shared.dto.RenewalEnergyProjectDto;

/**
 * @author Rabiu Ademoh
 */
public interface RenewalEnergyProjectService {

    RenewalEnergyProjectDto createEnergyProject(RenewalEnergyProjectDto renewalEnergyProjectDto);

    EnergyProjectRes getProjectByProjectId(Long id);

    Pagination<EnergyProjectRes> fetchEnergyProjects(EnergyProjectFilterRequest request);

    void delete(Long id);

    RenewalEnergyProjectDto updateEnergyProject(Long id, RenewalEnergyProjectDto renewalEnergyProjectDto);
}
