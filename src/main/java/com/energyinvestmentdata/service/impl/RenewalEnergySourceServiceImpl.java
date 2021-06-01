package com.energyinvestmentdata.service.impl;

import com.energyinvestmentdata.entity.RenewableEnergySourceEntity;
import com.energyinvestmentdata.exceptions.NotFoundException;
import com.energyinvestmentdata.repository.RenewableEnergySourceRepository;
import com.energyinvestmentdata.service.RenewableEnergySourceService;
import com.energyinvestmentdata.shared.dto.RenewalEnergySourceDto;
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
public class RenewalEnergySourceServiceImpl implements RenewableEnergySourceService {

   @Autowired
   RenewableEnergySourceRepository renewableEnergySourceRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public RenewalEnergySourceDto getEnergySourceById(Long id) {
        Optional<RenewableEnergySourceEntity> renewableEnergySourceEntity = renewableEnergySourceRepository.findById(id);
        renewableEnergySourceEntity.orElseThrow(() -> new NotFoundException("Energy source does not exist"));
        return modelMapper.map(renewableEnergySourceEntity.get(), RenewalEnergySourceDto.class);
    }

    @Override
    public RenewalEnergySourceDto updateEnergySource(Long id, RenewalEnergySourceDto renewalEnergySourceDto) {
        Optional<RenewableEnergySourceEntity> renewableEnergySourceEntity = renewableEnergySourceRepository.findById(id);
        renewableEnergySourceEntity.orElseThrow(() -> new NotFoundException("Energy source does not exist"));

        RenewableEnergySourceEntity fetchedEnergySource = renewableEnergySourceEntity.get();
        fetchedEnergySource.setName(renewalEnergySourceDto.getName());

        RenewableEnergySourceEntity updatedEnergySource = renewableEnergySourceRepository.save(fetchedEnergySource);
        return modelMapper.map(updatedEnergySource, RenewalEnergySourceDto.class);
    }

    @Override
    public List<RenewalEnergySourceDto> findAll() {
        List<RenewableEnergySourceEntity> renewableEnergySourceEntityList = renewableEnergySourceRepository.findAll();
        return modelMapper.map(renewableEnergySourceEntityList, new TypeToken<List<RenewalEnergySourceDto>>() {}.getType());
    }

    @Override
    public void delete(Long id) {
        Optional<RenewableEnergySourceEntity> renewableEnergySourceEntity = renewableEnergySourceRepository.findById(id);
        renewableEnergySourceEntity.orElseThrow(() -> new NotFoundException("Public Sector does not exist"));
        renewableEnergySourceRepository.delete(renewableEnergySourceEntity.get());
    }
}
