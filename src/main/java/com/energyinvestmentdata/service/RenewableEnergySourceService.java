package com.energyinvestmentdata.service;

import com.energyinvestmentdata.shared.dto.RenewalEnergySourceDto;

import java.util.List;

/**
 * @author Rabiu Ademoh
 */
public interface RenewableEnergySourceService {


    RenewalEnergySourceDto getEnergySourceById(Long id);
    RenewalEnergySourceDto updateEnergySource(Long id, RenewalEnergySourceDto renewalEnergySourceDto);
    List<RenewalEnergySourceDto> findAll();
    void delete(Long id);
}
