package com.energyinvestmentdata.service.impl;

import com.energyinvestmentdata.entity.CompanyEntity;
import com.energyinvestmentdata.entity.PublicSectorEntity;
import com.energyinvestmentdata.exceptions.NotFoundException;
import com.energyinvestmentdata.repository.PublicSectorRepository;
import com.energyinvestmentdata.service.PublicSectorService;
import com.energyinvestmentdata.shared.dto.PublicSectorDto;
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
public class PublicSectorServiceImpl implements PublicSectorService {

    @Autowired
    PublicSectorRepository publicSectorRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public PublicSectorDto getPublicSectorById(Long id) {
        Optional<PublicSectorEntity> publicSectorEntity = publicSectorRepository.findById(id);
        publicSectorEntity.orElseThrow(() -> new NotFoundException("Public Sector does not exist"));
        return modelMapper.map(publicSectorEntity.get(), PublicSectorDto.class);
    }

    @Override
    public PublicSectorDto updatePublicSector(Long id, PublicSectorDto publicSectorDto) {
        Optional<PublicSectorEntity> publicSectorEntity = publicSectorRepository.findById(id);
        publicSectorEntity.orElseThrow(() -> new NotFoundException("Public Sector does not exist"));

        PublicSectorEntity fetchedPublicSector = publicSectorEntity.get();
        fetchedPublicSector.setName(publicSectorDto.getName());

        PublicSectorEntity savedPublicSector = publicSectorRepository.save(fetchedPublicSector);
        return modelMapper.map(savedPublicSector, PublicSectorDto.class);
    }

    @Override
    public List<PublicSectorDto> findAll() {
        List<PublicSectorEntity> publicSectorEntityList = publicSectorRepository.findAll();
        return modelMapper.map(publicSectorEntityList,new TypeToken<List<PublicSectorDto>>() {}.getType());
    }

    @Override
    public void delete(Long id) {
        Optional<PublicSectorEntity> publicSectorEntity = publicSectorRepository.findById(id);
        publicSectorEntity.orElseThrow(() -> new NotFoundException("Public Sector does not exist"));
        publicSectorRepository.delete(publicSectorEntity.get());
    }
}
