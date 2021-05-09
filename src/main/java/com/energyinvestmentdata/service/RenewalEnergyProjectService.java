package com.energyinvestmentdata.service;

import com.energyinvestmentdata.model.response.EnergyProjectRes;
import com.energyinvestmentdata.shared.dto.RenewalEnergyProjectDto;

/**
 * @author Rabiu Ademoh
 */
public interface RenewalEnergyProjectService {

    RenewalEnergyProjectDto createEnergyProject(RenewalEnergyProjectDto renewalEnergyProjectDto);

    EnergyProjectRes getProjectByProjectId(Long id);
}
