package com.energyinvestmentdata.service;

import com.energyinvestmentdata.shared.dto.CompanyDto;

import java.util.List;

/**
 * @author Rabiu Ademoh
 */
public interface CompanyService {

    CompanyDto getCompanyByCompanyId(Long id);
    CompanyDto updateCompany(Long id, CompanyDto companyDto);
    List<CompanyDto> findAll();
}
