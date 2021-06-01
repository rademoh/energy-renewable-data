package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.RenewableEnergyProjectEntity;
import com.energyinvestmentdata.model.request.CreateRenewalEnergyProjectRequest;
import com.energyinvestmentdata.model.request.EnergyProjectFilterRequest;
import com.energyinvestmentdata.model.response.*;
import com.energyinvestmentdata.repository.RenewalEnergyProjectRepository;
import com.energyinvestmentdata.service.RenewalEnergyProjectService;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.RenewalEnergyProjectDto;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Rabiu Ademoh
 */

@Slf4j
@RestController
@RequestMapping("api/v1/project")
public class RenewalEnergyProjectController {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RenewalEnergyProjectService renewalEnergyProjectService;

    @Autowired
    RenewalEnergyProjectRepository renewalEnergyProjectRepository;


    //SELECT rep.id, rep.project_id , rep.project_name , esv.percentage, res.name , cm.name FROM renewable_energy_project AS rep INNER JOIN energy_source_value AS esv ON rep.id = esv.energy_project_id
    //INNER JOIN renewable_energy_source AS res ON res.id = esv.energy_source_id INNER JOIN company AS cm ON rep.company_id = cm.id WHERE rep.id = 83


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RenewalEnergyProjectDto>> createProject(@Valid @RequestBody CreateRenewalEnergyProjectRequest projectRequest){

        RenewalEnergyProjectDto renewalEnergyProjectDto = modelMapper.map(projectRequest, RenewalEnergyProjectDto.class);

        RenewalEnergyProjectDto createdProject = renewalEnergyProjectService.createEnergyProject(renewalEnergyProjectDto);

        ApiResponse<RenewalEnergyProjectDto> response = ApiResponse.<RenewalEnergyProjectDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.CREATED.value()).data(createdProject).error("").build();

        return ResponseEntity.ok().body(response);

    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<EnergyProjectRes>> getProjectById(@PathVariable Long id){

        EnergyProjectRes projectDto =  renewalEnergyProjectService.getProjectByProjectId(id);

        ApiResponse<EnergyProjectRes> response = ApiResponse.<EnergyProjectRes>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(projectDto).error("").build();

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping()
    public ResponseEntity<ApiResponse<Pagination<EnergyProjectRes>>> fetchEnergyProjects(@RequestBody EnergyProjectFilterRequest request) {
        log.info(" controller request - {}", new Gson().toJson(request));
        Pagination<EnergyProjectRes> newCorp = renewalEnergyProjectService.fetchEnergyProjects(request);
        ApiResponse<Pagination<EnergyProjectRes>> response = ApiResponse.<Pagination<EnergyProjectRes>>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(newCorp).error("").build();
        return ResponseEntity.ok().body(response);
    }


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping( path = "/all")
    public List<EnergyProjectRes> fen(){
        List<EnergyProjectRes> list = new ArrayList<>();
        Pageable paging = PageRequest.of(0,10);
        List<RenewableEnergyProjectEntity> result = renewalEnergyProjectRepository.findAll(paging).getContent();

        list = result.stream().map( item ->
                {
            List<EnergySourceValueRes> energySourceValueResList = item.getEnergySourceValueEntitySet().stream().map(esv -> new EnergySourceValueRes(esv.getPercentage(),esv.getRenewableEnergySourceEntity().getName())).collect( Collectors.toList());
            List<PublicInstitutionsConnectedRes> publicInstitutionsConnectedResList = item.getPublicInstitutionsConnected().stream().map(pic -> new PublicInstitutionsConnectedRes(pic.getInstitutionsConnected(),pic.getPublicInstitutionsEntity().getName())).collect(Collectors.toList());
            List<PublicSectorRes> publicSectorResList   =  item.getPublicSectorEntityList().stream().map( ps -> new PublicSectorRes(ps.getId(),ps.getName())).collect(Collectors.toList());
                    return new EnergyProjectRes(item.getId(), item.getProjectName(), item.getCompanyEntity().getName(), energySourceValueResList, item.getLongitude(), item.getLatitude(), item.getInstalledCapacity(), item.getConnections_financial_close()
                            , item.getCo2_avoided(), item.getBatteriesInstalled(), publicSectorResList, item.getDebtMix(), publicInstitutionsConnectedResList);
                }
        ).collect(Collectors.toList());
        return  modelMapper.map(list, new TypeToken<List<EnergyProjectRes>>() {
        }.getType());
    }


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PutMapping(path = "/{id}" ,  consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RenewalEnergyProjectDto>> updatePublicInstitution(@PathVariable Long id, @Valid @RequestBody CreateRenewalEnergyProjectRequest request){
        RenewalEnergyProjectDto renewalEnergyProjectDto = modelMapper.map(request, RenewalEnergyProjectDto.class);
        RenewalEnergyProjectDto updatedProject = renewalEnergyProjectService.updateEnergyProject(id,renewalEnergyProjectDto);

        ApiResponse<RenewalEnergyProjectDto> response = ApiResponse.<RenewalEnergyProjectDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(updatedProject).error("").build();
        return ResponseEntity.ok().body(response);
    }



    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @DeleteMapping( path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deleteEnergyProjectById(@PathVariable Long id){
        renewalEnergyProjectService.delete(id);
        ApiResponse<?> response = ApiResponse.builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data("").error("").build();
        return ResponseEntity.ok().body(response);
    }




}
