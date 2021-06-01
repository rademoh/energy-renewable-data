package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.PublicSectorEntity;
import com.energyinvestmentdata.entity.RenewableEnergySourceEntity;
import com.energyinvestmentdata.model.request.CreateEnergySourceRequest;
import com.energyinvestmentdata.model.request.CreatePublicSectorRequest;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.repository.PublicSectorRepository;
import com.energyinvestmentdata.repository.RenewableEnergySourceRepository;
import com.energyinvestmentdata.service.PublicSectorService;
import com.energyinvestmentdata.service.RenewableEnergySourceService;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.PublicSectorDto;
import com.energyinvestmentdata.shared.dto.RenewalEnergySourceDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Rabiu Ademoh
 */

@RestController
@RequestMapping("api/v1/energysource")
public class EnergySourceController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RenewableEnergySourceRepository energySourceRepository;

    @Autowired
    RenewableEnergySourceService energySourceService;

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RenewalEnergySourceDto>> createEnergySourceType(@Valid @RequestBody CreateEnergySourceRequest request){

        RenewableEnergySourceEntity energySourceEntity = modelMapper.map(request, RenewableEnergySourceEntity.class);
        RenewableEnergySourceEntity createdEnergySource = energySourceRepository.save(energySourceEntity);
        RenewalEnergySourceDto renewalEnergySourceDto = modelMapper.map(createdEnergySource, RenewalEnergySourceDto.class);

        ApiResponse<RenewalEnergySourceDto> response = ApiResponse.<RenewalEnergySourceDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.CREATED.value()).data(renewalEnergySourceDto).error("").build();

        return ResponseEntity.ok().body(response);

    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping( path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RenewalEnergySourceDto>> getEnergySourceTypeById(@PathVariable Long id){
        RenewalEnergySourceDto renewalEnergySourceDto =  energySourceService.getEnergySourceById(id);
        ApiResponse<RenewalEnergySourceDto> response = ApiResponse.<RenewalEnergySourceDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(renewalEnergySourceDto).error("").build();

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PutMapping(path = "/{id}" ,  consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RenewalEnergySourceDto>> updateEnergySourceType(@PathVariable Long id,@Valid @RequestBody CreateEnergySourceRequest request){
        RenewalEnergySourceDto energySourceDto = modelMapper.map(request, RenewalEnergySourceDto.class);
        RenewalEnergySourceDto updatedEnergySource = energySourceService.updateEnergySource(id,energySourceDto);

        ApiResponse<RenewalEnergySourceDto> response = ApiResponse.<RenewalEnergySourceDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(updatedEnergySource).error("").build();
        return ResponseEntity.ok().body(response);
    }


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<RenewalEnergySourceDto>>> getAllEnergySourceTypes(){

        List<RenewalEnergySourceDto> energySourceDtoList = energySourceService.findAll();
        ApiResponse<List<RenewalEnergySourceDto>> response = ApiResponse.<List<RenewalEnergySourceDto>>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(energySourceDtoList).error("").build();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @DeleteMapping( path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deleteEnergySourceTypeById(@PathVariable Long id){
             energySourceService.delete(id);
        ApiResponse<?> response = ApiResponse.builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data("").error("").build();
        return ResponseEntity.ok().body(response);
    }



}
