package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.model.request.CreateRenewalEnergyProjectRequest;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.model.response.EnergyProjectRes;
import com.energyinvestmentdata.service.impl.RenewalEnergyProjectServiceImpl;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.RenewalEnergyProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author Rabiu Ademoh
 */

@RestController
@RequestMapping("api/v1/project/")
public class RenewalEnergyProjectController {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RenewalEnergyProjectServiceImpl renewalEnergyProjectService;


    //SELECT rep.id, rep.project_id , rep.project_name , esv.percentage, res.name FROM renewable_energy_project AS rep INNER JOIN energy_source_value AS esv
    //ON rep.id = esv.energy_project_id INNER JOIN renewable_energy_source AS res ON res.id = esv.energy_source_id WHERE rep.id = 83

    //SELECT rep.id, rep.project_id , rep.project_name , esv.percentage, res.name , cm.name FROM renewable_energy_project AS rep INNER JOIN energy_source_value AS esv ON rep.id = esv.energy_project_id
    //INNER JOIN renewable_energy_source AS res ON res.id = esv.energy_source_id INNER JOIN company AS cm ON rep.company_id = cm.id WHERE rep.id = 83


    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RenewalEnergyProjectDto>> createProject(@Valid @RequestBody CreateRenewalEnergyProjectRequest projectRequest){

        modelMapper = new ModelMapper();
        RenewalEnergyProjectDto renewalEnergyProjectDto = modelMapper.map(projectRequest, RenewalEnergyProjectDto.class);

        RenewalEnergyProjectDto createdProject = renewalEnergyProjectService.createEnergyProject(renewalEnergyProjectDto);

        ApiResponse<RenewalEnergyProjectDto> response = ApiResponse.<RenewalEnergyProjectDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.CREATED.value()).data(createdProject).error("").build();

        return ResponseEntity.ok().body(response);

    }

    @GetMapping(path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<EnergyProjectRes>> getProjectById(@PathVariable Long id){

        EnergyProjectRes projectDto =  renewalEnergyProjectService.getProjectByProjectId(id);

        ApiResponse<EnergyProjectRes> response = ApiResponse.<EnergyProjectRes>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(projectDto).error("").build();

        return ResponseEntity.ok().body(response);
    }



   /* @GetMapping(path = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRest getUser(@PathVariable String id){
        UserRest returnValue = new UserRest();

        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }
*/
}
