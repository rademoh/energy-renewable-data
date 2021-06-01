package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.PublicInstitutionsEntity;
import com.energyinvestmentdata.model.request.CreatePublicInstitutionRequest;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.repository.PublicInstitutionRepository;
import com.energyinvestmentdata.service.PublicInstitutionService;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.PublicInstitutionDto;
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
@RequestMapping("api/v1/publicinstitution")
public class PublicInstitutionController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PublicInstitutionRepository publicInstitutionRepository;

    @Autowired
    PublicInstitutionService publicInstitutionService;


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PublicInstitutionDto>> createPublicInstitution(@Valid @RequestBody CreatePublicInstitutionRequest request){

        PublicInstitutionsEntity publicInstitutionEntity = modelMapper.map(request, PublicInstitutionsEntity.class);
        PublicInstitutionsEntity createdInstitution = publicInstitutionRepository.save(publicInstitutionEntity);
        PublicInstitutionDto publicInstitutionDto = modelMapper.map(createdInstitution, PublicInstitutionDto.class);

        ApiResponse<PublicInstitutionDto> response = ApiResponse.<PublicInstitutionDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.CREATED.value()).data(publicInstitutionDto).error("").build();

        return ResponseEntity.ok().body(response);

    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping( path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PublicInstitutionDto>> getPublicInstitution(@PathVariable Long id){
        PublicInstitutionDto publicInstitutionDto =  publicInstitutionService.getPublicInstitutionById(id);

        ApiResponse<PublicInstitutionDto> response = ApiResponse.<PublicInstitutionDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(publicInstitutionDto).error("").build();

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PutMapping(path = "/{id}" ,  consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PublicInstitutionDto>> updatePublicInstitution(@PathVariable Long id,@Valid @RequestBody CreatePublicInstitutionRequest request){
        PublicInstitutionDto publicInstitutionDto = modelMapper.map(request, PublicInstitutionDto.class);
        PublicInstitutionDto updatedPublicInstitution = publicInstitutionService.updatePublicInstitution(id,publicInstitutionDto);

        ApiResponse<PublicInstitutionDto> response = ApiResponse.<PublicInstitutionDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(updatedPublicInstitution).error("").build();
        return ResponseEntity.ok().body(response);
    }


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<PublicInstitutionDto>>> getAllPublicInstitution(){

        List<PublicInstitutionDto> publicInstitutionDtoList = publicInstitutionService.findAll();
        ApiResponse<List<PublicInstitutionDto>> response = ApiResponse.<List<PublicInstitutionDto>>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(publicInstitutionDtoList).error("").build();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @DeleteMapping( path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deletePublicInstitutionById(@PathVariable Long id){
          publicInstitutionService.delete(id);
        ApiResponse<?> response = ApiResponse.builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data("").error("").build();
        return ResponseEntity.ok().body(response);
    }



}
