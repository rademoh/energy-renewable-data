package com.energyinvestmentdata.service;

import com.energyinvestmentdata.shared.dto.PublicInstitutionDto;
import java.util.List;

/**
 * @author Rabiu Ademoh
 */
public interface PublicInstitutionService {

    PublicInstitutionDto getPublicInstitutionById(Long id);
    PublicInstitutionDto updatePublicInstitution(Long id, PublicInstitutionDto publicInstitutionDto);
    List<PublicInstitutionDto> findAll();
    void delete(Long id);
}
