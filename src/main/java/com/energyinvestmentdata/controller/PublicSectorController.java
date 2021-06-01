package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.CompanyEntity;
import com.energyinvestmentdata.entity.PublicSectorEntity;
import com.energyinvestmentdata.model.request.CreateCompanyRequest;
import com.energyinvestmentdata.model.request.CreatePublicSectorRequest;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.repository.CompanyRepository;
import com.energyinvestmentdata.repository.PublicSectorRepository;
import com.energyinvestmentdata.service.CompanyService;
import com.energyinvestmentdata.service.PublicSectorService;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.CompanyDto;
import com.energyinvestmentdata.shared.dto.PublicSectorDto;
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
@RequestMapping("api/v1/publicsector")
public class PublicSectorController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PublicSectorRepository publicSectorRepository;

    @Autowired
    PublicSectorService publicSectorService;


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PublicSectorDto>> createPublicSector(@Valid @RequestBody CreatePublicSectorRequest publicSectorRequest){

        PublicSectorEntity publicSectorEntity = modelMapper.map(publicSectorRequest, PublicSectorEntity.class);
        PublicSectorEntity createdPublicSector = publicSectorRepository.save(publicSectorEntity);
        PublicSectorDto publicSectorDto = modelMapper.map(createdPublicSector, PublicSectorDto.class);

        ApiResponse<PublicSectorDto> response = ApiResponse.<PublicSectorDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.CREATED.value()).data(publicSectorDto).error("").build();

        return ResponseEntity.ok().body(response);

    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping( path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PublicSectorDto>> getPublicSector(@PathVariable Long id){
        PublicSectorDto publicSectorDto =  publicSectorService.getPublicSectorById(id);

        ApiResponse<PublicSectorDto> response = ApiResponse.<PublicSectorDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(publicSectorDto).error("").build();

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PutMapping(path = "/{id}" ,  consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PublicSectorDto>> updatePublicSector(@PathVariable Long id,@Valid @RequestBody CreatePublicSectorRequest request){
        PublicSectorDto publicSectorDto = modelMapper.map(request, PublicSectorDto.class);
        PublicSectorDto updatedPublicSector = publicSectorService.updatePublicSector(id,publicSectorDto);

        ApiResponse<PublicSectorDto> response = ApiResponse.<PublicSectorDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(updatedPublicSector).error("").build();
        return ResponseEntity.ok().body(response);
    }


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<PublicSectorDto>>> getAllPublicSector(){

        List<PublicSectorDto> publicSectorDtoList = publicSectorService.findAll();
        ApiResponse<List<PublicSectorDto>> response = ApiResponse.<List<PublicSectorDto>>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(publicSectorDtoList).error("").build();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @DeleteMapping( path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deletePublicSectorById(@PathVariable Long id){
          publicSectorService.delete(id);
        ApiResponse<?> response = ApiResponse.builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data("").error("").build();
        return ResponseEntity.ok().body(response);
    }



}
