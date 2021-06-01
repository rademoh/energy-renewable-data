package com.energyinvestmentdata.service.impl;

import com.energyinvestmentdata.entity.CompanyEntity;
import com.energyinvestmentdata.exceptions.NotFoundException;
import com.energyinvestmentdata.repository.CompanyRepository;
import com.energyinvestmentdata.service.CompanyService;
import com.energyinvestmentdata.shared.dto.CompanyDto;
import com.energyinvestmentdata.shared.dto.EnergySourceValueDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Rabiu Ademoh
 */
@Service
public class CompanyServiceImpl implements CompanyService {

   @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public CompanyDto getCompanyByCompanyId(Long id) {
        Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
        companyEntity.orElseThrow(() -> new NotFoundException("Company does not exist"));
        return modelMapper.map(companyEntity.get(), CompanyDto.class);
    }

    @Override
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Optional<CompanyEntity> companyEntity = companyRepository.findById(id);
        companyEntity.orElseThrow(() -> new NotFoundException("Company does not exist"));

        CompanyEntity fetchedCompany = companyEntity.get();
        fetchedCompany.setName(companyDto.getName());

        CompanyEntity updatedCompany =  companyRepository.save(fetchedCompany);
        return modelMapper.map(updatedCompany, CompanyDto.class);
    }

    @Override
    public List<CompanyDto> findAll() {
       List<CompanyEntity> companyEntityList =  companyRepository.findAll();
       return modelMapper.map(companyEntityList,new TypeToken<List<CompanyDto>>() {}.getType());
    }
}
