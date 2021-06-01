package com.energyinvestmentdata.service.impl;

import com.energyinvestmentdata.entity.*;
import com.energyinvestmentdata.exceptions.NotFoundException;
import com.energyinvestmentdata.exceptions.ProjectNameAlreadyExistsException;
import com.energyinvestmentdata.model.request.EnergyProjectFilterRequest;
import com.energyinvestmentdata.model.request.FilterTestRequest;
import com.energyinvestmentdata.model.request.PaginationRequest;
import com.energyinvestmentdata.model.response.*;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Rabiu Ademoh
 */
@Slf4j
@Service
public class RenewalEnergyProjectServiceImpl implements RenewalEnergyProjectService {

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
    PublicSectorRepository publicSectorRepository;

    @Autowired
    PublicInstitutionRepository publicInstitutionRepository;

    @Autowired
    PublicInstitutionsConnectedRepository institutionsConnectedRepository;

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
           renewableEnergyProjectEntity.setCompanyEntity(companyEntity.get());

           //saving list of energy source value
           Set<EnergySourceValueEntity> energySourceValueEntities = new HashSet<>();
           for ( int i = 0; i < renewalEnergyProjectDto.getEnergySource().size(); i++){

               Optional<RenewableEnergySourceEntity> energySourceEntity = renewableEnergySourceRepository.findById(renewalEnergyProjectDto.getEnergySource().get(i).getId());

               energySourceEntity.orElseThrow(() -> new NotFoundException("Energy Source type does not exist"));

               EnergySourceValueEntity energySourceValueEntity = new EnergySourceValueEntity();
               energySourceValueEntity.setRenewableEnergySourceEntity(energySourceEntity.get());
               energySourceValueEntity.setPercentage(renewalEnergyProjectDto.getEnergySource().get(i).getPercentage());
               energySourceValueEntity.setRenewableEnergyProjectEntity(renewableEnergyProjectEntity);

               energySourceValueEntities.add(energySourceValueEntity);
           }
          // renewableEnergyProjectEntity.getEnergySourceValueEntitySet().addAll(energySourceValueEntities);
           renewableEnergyProjectEntity.setEnergySourceValueEntitySet(energySourceValueEntities);

           //saving publicInstitutionConnected value
           Set<PublicInstitutionsConnectedEntity> publicInstitutionsConnectedEntities = new HashSet<>();
          for (int i = 0 ; i < renewalEnergyProjectDto.getPublicInstitutionConnected().size() ; i++){
              Optional<PublicInstitutionsEntity> publicInstitutionsEntity = publicInstitutionRepository.findById(renewalEnergyProjectDto.getPublicInstitutionConnected().get(i).getId());
              publicInstitutionsEntity.orElseThrow(() -> new NotFoundException("Public Institution does not exist"));

              PublicInstitutionsConnectedEntity publicInstitutionsConnectedEntity = new PublicInstitutionsConnectedEntity();
              publicInstitutionsConnectedEntity.setInstitutionsConnected(renewalEnergyProjectDto.getPublicInstitutionConnected().get(i).getNumber());
              publicInstitutionsConnectedEntity.setPublicInstitutionsEntity(publicInstitutionsEntity.get());
              publicInstitutionsConnectedEntity.setRenewableEnergyProjectEntity(renewableEnergyProjectEntity);

              publicInstitutionsConnectedEntities.add(publicInstitutionsConnectedEntity);
          }
          renewableEnergyProjectEntity.setPublicInstitutionsConnected(publicInstitutionsConnectedEntities);

          //Saving list of public sector;
          Set<PublicSectorEntity> publicSectorEntities = new HashSet<>();
          for ( int i = 0 ; i <  renewalEnergyProjectDto.getPublicSector().size(); i++ ){
           Optional<PublicSectorEntity> publicSectorEntity = publicSectorRepository.findById(renewalEnergyProjectDto.getPublicSector().get(i).longValue());
           publicSectorEntity.orElseThrow(() -> new NotFoundException("Public Sector does not exist"));
           publicSectorEntities.add(publicSectorEntity.get());
          }

          renewableEnergyProjectEntity.setPublicSectorEntityList(publicSectorEntities);

          RenewableEnergyProjectEntity createdProject = renewalEnergyProjectRepository.save(renewableEnergyProjectEntity);

          RenewalEnergyProjectDto energyProjectDto = modelMapper.map(createdProject, RenewalEnergyProjectDto.class);
          List<EnergySourceValueRes> energySourceValueRes = modelMapper.map(createdProject.getEnergySourceValueEntitySet(),new TypeToken<List<EnergySourceValueRes>>() {}.getType());
          energyProjectDto.setEnergySource(energySourceValueRes);

        return energyProjectDto;
    }

    @Override
    public EnergyProjectRes getProjectByProjectId(Long id) {

          Optional<RenewableEnergyProjectEntity> projectEntity = renewalEnergyProjectRepository.findById(id);
          RenewableEnergyProjectEntity fetchedProject = projectEntity.orElseThrow(() -> new NotFoundException("Project does not exist"));

          List<EnergySourceValueRes> energySourceValueResList = fetchedProject.getEnergySourceValueEntitySet().stream().map( esv -> new EnergySourceValueRes(esv.getPercentage(),esv.getRenewableEnergySourceEntity().getName())).collect( Collectors.toList());

          List<PublicInstitutionsConnectedRes> publicInstitutionsConnectedResList = fetchedProject.getPublicInstitutionsConnected().stream().map( pic -> new PublicInstitutionsConnectedRes(pic.getInstitutionsConnected(),pic.getPublicInstitutionsEntity().getName())).collect(Collectors.toList());

          List<PublicSectorRes> publicSectorResList   =  fetchedProject.getPublicSectorEntityList().stream().map( ps -> new PublicSectorRes(ps.getId(),ps.getName())).collect(Collectors.toList());

          EnergyProjectRes renewalEnergyProjectRes = modelMapper.map(fetchedProject, EnergyProjectRes.class);

          renewalEnergyProjectRes.setEnergySource(energySourceValueResList);
          renewalEnergyProjectRes.setCompanyName(fetchedProject.getCompanyEntity().getName());
          renewalEnergyProjectRes.setPublicSector(publicSectorResList);
          renewalEnergyProjectRes.setPublicInstitutionsConnected(publicInstitutionsConnectedResList);

        return renewalEnergyProjectRes;
    }

    @Override
    public Pagination<EnergyProjectRes> fetchEnergyProjects(EnergyProjectFilterRequest request) {
        Pageable paging = PageRequest.of(request.getPage(),request.getSize());
        Page<RenewableEnergyProjectEntity> response = renewalEnergyProjectRepository.findAll(paging);
        List<EnergyProjectRes> list =   response.getContent().stream().map(
                item ->
                {
                    List<EnergySourceValueRes> energySourceValueResList = item.getEnergySourceValueEntitySet().stream().map(esv -> new EnergySourceValueRes(esv.getPercentage(),esv.getRenewableEnergySourceEntity().getName())).collect( Collectors.toList());
                    List<PublicInstitutionsConnectedRes> publicInstitutionsConnectedResList = item.getPublicInstitutionsConnected().stream().map(pic -> new PublicInstitutionsConnectedRes(pic.getInstitutionsConnected(),pic.getPublicInstitutionsEntity().getName())).collect(Collectors.toList());
                    List<PublicSectorRes> publicSectorResList   =  item.getPublicSectorEntityList().stream().map( ps -> new PublicSectorRes(ps.getId(),ps.getName())).collect(Collectors.toList());
                    return new EnergyProjectRes(item.getId(), item.getProjectName(), item.getCompanyEntity().getName(), energySourceValueResList, item.getLongitude(), item.getLatitude(), item.getInstalledCapacity(), item.getConnections_financial_close()
                            , item.getCo2_avoided(), item.getBatteriesInstalled(), publicSectorResList, item.getDebtMix(), publicInstitutionsConnectedResList);
                }
        ).collect(Collectors.toList());
        return Pagination.<EnergyProjectRes>builder().content(list).number(response.getNumber()).size(response.getSize()).totalElements(response.getNumberOfElements()).totalPages(response.getTotalPages()).last(response.isLast()).first(response.isFirst()).build();
    }

    @Override
    public void delete(Long id) {
        Optional<RenewableEnergyProjectEntity> renewableEnergyProjectEntity = renewalEnergyProjectRepository.findById(id);
        renewableEnergyProjectEntity.orElseThrow(() -> new NotFoundException("Project does not exist"));
        renewalEnergyProjectRepository.delete(renewableEnergyProjectEntity.get());
    }

    @Override
    @Transactional
    public RenewalEnergyProjectDto updateEnergyProject(Long id, RenewalEnergyProjectDto renewalEnergyProjectDto) {
        Optional<RenewableEnergyProjectEntity> renewableEnergyProjectEntity = renewalEnergyProjectRepository.findById(id);
        renewableEnergyProjectEntity.orElseThrow(() -> new NotFoundException("Project does not exist"));

        RenewableEnergyProjectEntity fetchedProject = renewableEnergyProjectEntity.get();
        fetchedProject.setProjectName(renewalEnergyProjectDto.getProjectName());

        Optional<CompanyEntity> companyEntity = companyRepository.findById(renewalEnergyProjectDto.getCompanyId());
        companyEntity.orElseThrow(() -> new NotFoundException("Company does not exist"));
        fetchedProject.setCompanyEntity(companyEntity.get());

        fetchedProject.setLatitude(renewalEnergyProjectDto.getLatitude());
        fetchedProject.setLongitude(renewalEnergyProjectDto.getLongitude());
        fetchedProject.setInstalledCapacity(renewalEnergyProjectDto.getInstalledCapacity());
        fetchedProject.setConnections_financial_close(renewalEnergyProjectDto.getConnections_financial_close());
        fetchedProject.setCo2_avoided(renewalEnergyProjectDto.getCo2_avoided());
        fetchedProject.setBatteriesInstalled(renewalEnergyProjectDto.getBatteriesInstalled());
        fetchedProject.setDebtMix(renewalEnergyProjectDto.getDebtMix());


        energySourceValueRepository.deleteByProjectId(id);

        //updating list of energy source value
        Set<EnergySourceValueEntity> energySourceValueEntities = new HashSet<>();
        for ( int i = 0; i < renewalEnergyProjectDto.getEnergySource().size(); i++){
            Optional<RenewableEnergySourceEntity> energySourceEntity = renewableEnergySourceRepository.findById(renewalEnergyProjectDto.getEnergySource().get(i).getId());

            energySourceEntity.orElseThrow(() -> new NotFoundException("Energy Source type does not exist"));

            EnergySourceValueEntity energySourceValueEntity = new EnergySourceValueEntity();
            energySourceValueEntity.setRenewableEnergySourceEntity(energySourceEntity.get());
            energySourceValueEntity.setPercentage(renewalEnergyProjectDto.getEnergySource().get(i).getPercentage());
            energySourceValueEntity.setRenewableEnergyProjectEntity(fetchedProject);

            energySourceValueEntities.add(energySourceValueEntity);
        }

        fetchedProject.setEnergySourceValueEntitySet(energySourceValueEntities);

         institutionsConnectedRepository.deleteByProjectId(id);

        //saving publicInstitutionConnected value
        Set<PublicInstitutionsConnectedEntity> publicInstitutionsConnectedEntities = new HashSet<>();
        for (int i = 0 ; i < renewalEnergyProjectDto.getPublicInstitutionConnected().size() ; i++){
            Optional<PublicInstitutionsEntity> publicInstitutionsEntity = publicInstitutionRepository.findById(renewalEnergyProjectDto.getPublicInstitutionConnected().get(i).getId());
            publicInstitutionsEntity.orElseThrow(() -> new NotFoundException("Public Institution does not exist"));

            PublicInstitutionsConnectedEntity publicInstitutionsConnectedEntity = new PublicInstitutionsConnectedEntity();
            publicInstitutionsConnectedEntity.setInstitutionsConnected(renewalEnergyProjectDto.getPublicInstitutionConnected().get(i).getNumber());
            publicInstitutionsConnectedEntity.setPublicInstitutionsEntity(publicInstitutionsEntity.get());
            publicInstitutionsConnectedEntity.setRenewableEnergyProjectEntity(fetchedProject);

            publicInstitutionsConnectedEntities.add(publicInstitutionsConnectedEntity);
        }
        fetchedProject.setPublicInstitutionsConnected(publicInstitutionsConnectedEntities);

        fetchedProject.getPublicSectorEntityList().parallelStream().forEach(pc ->
                    fetchedProject.getPublicSectorEntityList().remove(pc)
                );

        //Saving list of public sector;
        Set<PublicSectorEntity> publicSectorEntities = new HashSet<>();
        for ( int i = 0 ; i <  renewalEnergyProjectDto.getPublicSector().size(); i++ ){
            Optional<PublicSectorEntity> publicSectorEntity = publicSectorRepository.findById(renewalEnergyProjectDto.getPublicSector().get(i).longValue());
            publicSectorEntity.orElseThrow(() -> new NotFoundException("Public Sector does not exist"));
            publicSectorEntities.add(publicSectorEntity.get());
        }

        fetchedProject.setPublicSectorEntityList(publicSectorEntities);

        RenewableEnergyProjectEntity updatedProject = renewalEnergyProjectRepository.save(fetchedProject);

        RenewalEnergyProjectDto energyProjectDto = modelMapper.map(updatedProject, RenewalEnergyProjectDto.class);
        List<EnergySourceValueRes> energySourceValueRes = modelMapper.map(updatedProject.getEnergySourceValueEntitySet(),new TypeToken<List<EnergySourceValueRes>>() {}.getType());
        energyProjectDto.setEnergySource(energySourceValueRes);


        return energyProjectDto;
    }


    /*Map<String, Object> filter = new HashMap<>();
        if (request.getId() > 0L) {
        filter.put("id", request.getId());
    }
        if (null != request.getName()) {
        filter.put("id", request.getId());
    }
    PaginationRequest page = PaginationRequest.builder().page(request.getPage()).size(request.getSize()).build();

    Page<Department> response = repo.findAllBy(Department.class, filter, page);
        return mapper.map(response, new com.google.gson.reflect.TypeToken<Pagination<DepartmentResponse>>() {
    }.getType());
   }*/


}
