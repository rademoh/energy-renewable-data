package com.energyinvestmentdata.service.impl;

import com.energyinvestmentdata.entity.PublicInstitutionsEntity;
import com.energyinvestmentdata.exceptions.NotFoundException;
import com.energyinvestmentdata.repository.PublicInstitutionRepository;
import com.energyinvestmentdata.service.PublicInstitutionService;
import com.energyinvestmentdata.shared.dto.PublicInstitutionDto;
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
public class PublicInstitutionServiceImpl implements PublicInstitutionService {

    @Autowired
    PublicInstitutionRepository publicInstitutionRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PublicInstitutionDto getPublicInstitutionById(Long id) {
        Optional<PublicInstitutionsEntity> publicInstitutionsEntity = publicInstitutionRepository.findById(id);
        publicInstitutionsEntity.orElseThrow(() ->  new NotFoundException("Public Institution does not exist"));
        return modelMapper.map(publicInstitutionsEntity.get(), PublicInstitutionDto.class);
    }

    @Override
    public PublicInstitutionDto updatePublicInstitution(Long id, PublicInstitutionDto publicInstitutionDto) {
        Optional<PublicInstitutionsEntity> publicInstitutionsEntity = publicInstitutionRepository.findById(id);
        publicInstitutionsEntity.orElseThrow(() ->  new NotFoundException("Public Institution does not exist"));

        PublicInstitutionsEntity fetchedPublicInstitution = publicInstitutionsEntity.get();
        fetchedPublicInstitution.setName(publicInstitutionDto.getName());

        PublicInstitutionsEntity updatedPublicInstitution = publicInstitutionRepository.save(fetchedPublicInstitution);
        return modelMapper.map(updatedPublicInstitution, PublicInstitutionDto.class);
    }

    @Override
    public List<PublicInstitutionDto> findAll() {
        List<PublicInstitutionsEntity> publicInstitutionsEntityList = publicInstitutionRepository.findAll();
        return modelMapper.map(publicInstitutionsEntityList, new TypeToken<List<PublicInstitutionDto>>() {}.getType());
    }

    @Override
    public void delete(Long id) {
        Optional<PublicInstitutionsEntity> publicInstitutionsEntity = publicInstitutionRepository.findById(id);
        publicInstitutionsEntity.orElseThrow(() -> new NotFoundException("Public Sector does not exist"));
        publicInstitutionRepository.delete(publicInstitutionsEntity.get());
    }
}
