package com.energyinvestmentdata.service;

import com.energyinvestmentdata.shared.dto.PublicSectorDto;

import java.util.List;

/**
 * @author Rabiu Ademoh
 */
public interface PublicSectorService {

    PublicSectorDto getPublicSectorById(Long id);
    PublicSectorDto updatePublicSector(Long id, PublicSectorDto publicSectorDto);
    List<PublicSectorDto> findAll();
    void delete(Long id);
}
