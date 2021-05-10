package com.energyinvestmentdata.service.impl;

import com.energyinvestmentdata.entity.CompanyEntity;
import com.energyinvestmentdata.entity.EnergySourceValueEntity;
import com.energyinvestmentdata.entity.RenewableEnergyProjectEntity;
import com.energyinvestmentdata.entity.RenewableEnergySourceEntity;
import com.energyinvestmentdata.exceptions.NotFoundException;
import com.energyinvestmentdata.exceptions.ProjectNameAlreadyExistsException;
import com.energyinvestmentdata.model.response.EnergyProjectRes;
import com.energyinvestmentdata.model.response.EnergySourceValueRes;
import com.energyinvestmentdata.repository.*;
import com.energyinvestmentdata.service.RenewalEnergyProjectService;
import com.energyinvestmentdata.shared.Utils;
import com.energyinvestmentdata.shared.dto.EnergySourceValueDto;
import com.energyinvestmentdata.shared.dto.RenewalEnergyProjectDto;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Rabiu Ademoh
 */
@Slf4j
@Service
public class RenewalEnergyProjectServiceImpl implements RenewalEnergyProjectService {

    @Autowired
    CustomRepository customRepository;
    
    @Autowired
    BaseRepository repository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RenewalEnergyProjectRepository renewalEnergyProjectRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EnergySourceValueRepository energySourceValueRepository;

    @Autowired
    RenewableEnergySourceRepository renewableEnergySourceRepository;

    @Autowired
    Utils utils;

    @Transactional
    @Override
    public RenewalEnergyProjectDto createEnergyProject(RenewalEnergyProjectDto renewalEnergyProjectDto) {

        log.info(" create request - {}", new Gson().toJson(renewalEnergyProjectDto));

        if( renewalEnergyProjectRepository.findByProjectName(renewalEnergyProjectDto.getProjectName()) != null) throw new ProjectNameAlreadyExistsException(renewalEnergyProjectDto.getProjectName() + " Already Exists");

           Optional<CompanyEntity> companyEntity = companyRepository.findById(renewalEnergyProjectDto.getCompanyId());

           companyEntity.orElseThrow(() -> new NotFoundException("Company does not exist"));
           String projectId = utils.generateProjectId(15);

           RenewableEnergyProjectEntity renewableEnergyProjectEntity = modelMapper.map(renewalEnergyProjectDto, RenewableEnergyProjectEntity.class);
           renewableEnergyProjectEntity.setProjectId(projectId);
           //renewableEnergyProjectEntity.setCompanyEntity(companyEntity.get());


          Set<EnergySourceValueEntity> energySourceValueEntities = new HashSet<>();
           for ( int i = 0; i < renewalEnergyProjectDto.getEnergySourceValueDto().size(); i++){
               Optional<RenewableEnergySourceEntity> energySourceEntity = renewableEnergySourceRepository.findById(renewalEnergyProjectDto.getEnergySourceValueDto().get(i).getEnergySourceId());

               energySourceEntity.orElseThrow(() -> new NotFoundException("Energy Source type does not exist"));

               EnergySourceValueEntity energySourceValueEntity = new EnergySourceValueEntity();

               //energySourceValueEntity.setRenewableEnergySourceEntity(energySourceEntity.get());
               energySourceValueEntity.setPercentage(renewalEnergyProjectDto.getEnergySourceValueDto().get(i).getPercentage());
              // energySourceValueEntity.setRenewableEnergyProjectEntity(renewableEnergyProjectEntity);

               energySourceValueEntities.add(energySourceValueEntity);
           }
          // renewableEnergyProjectEntity.getEnergySourceValueEntitySet().addAll(energySourceValueEntities);
           //renewableEnergyProjectEntity.setEnergySourceValueEntitySet(energySourceValueEntities);



          RenewableEnergyProjectEntity createdProject = renewalEnergyProjectRepository.save(renewableEnergyProjectEntity);

          RenewalEnergyProjectDto energyProjectDto = modelMapper.map(createdProject, RenewalEnergyProjectDto.class);

          //List<EnergySourceValueDto> energySourceValueDto = modelMapper.map(createdProject.getEnergySourceValueEntitySet(),new TypeToken<List<EnergySourceValueDto>>() {}.getType());

          //log.info(" energySourceValueDto - {}", new Gson().toJson(energySourceValueDto));

          //energyProjectDto.setEnergySourceValueDto(energySourceValueDto);

        return energyProjectDto;
    }

    @Override
//    public EnergyProjectRes getProjectByProjectId(Long id) {
//
//          Optional<RenewableEnergyProjectEntity> projectEntity = renewalEnergyProjectRepository.findById(id);
//
//          Set<EnergySourceValueEntity> energySourceValueEntities =   projectEntity.get().getEnergySourceValueEntitySet();
//          List<EnergySourceValueRes> res = energySourceValueEntities.stream().map( i -> new EnergySourceValueRes(i.getPercentage(),i.getRenewableEnergySourceEntity().getName())).collect( Collectors.toList());
//
//          EnergyProjectRes renewalEnergyProjectDto = modelMapper.map(projectEntity.get(), EnergyProjectRes.class);
//          // List<EnergySourceValueDto> energySourceValueDto = modelMapper.map(projectEntity.get().getEnergySourceValueEntitySet(),new TypeToken<List<EnergySourceValueDto>>() {}.getType());
//        renewalEnergyProjectDto.setEnergySourceValueDto(res);
//        renewalEnergyProjectDto.setCompanyName(projectEntity.get().getCompanyEntity().getName());
//
//        return renewalEnergyProjectDto;
//    }

    public EnergyProjectRes getProjectByProjectId(Long id) {
        RenewableEnergyProjectEntity energyProject = repository.findOneOptional(RenewableEnergyProjectEntity.class, id).orElseThrow(
                () -> new NotFoundException("Records not found")
        );
        List<EnergySourceValueEntity> energySource = repository.findAllBy(EnergySourceValueEntity.class, "energyProjectId",energyProject.getId());
        CompanyEntity company = repository.findOne(CompanyEntity.class, energyProject.getCompanyId());
        EnergyProjectRes projectRes = modelMapper.map(energyProject, EnergyProjectRes.class);
        List<EnergySourceValueRes> collect = energySource.stream().map(i ->
                EnergySourceValueRes.builder().percentage(i.getPercentage()).build())
                .collect(Collectors.toList());
        projectRes.setEnergySourceValueDto(collect);
        projectRes.setCompanyName(company.getName());
        return  projectRes;
    }


}
