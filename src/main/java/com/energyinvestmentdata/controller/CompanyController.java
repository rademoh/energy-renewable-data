package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.CompanyEntity;
import com.energyinvestmentdata.model.request.CreateCompanyRequest;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.repository.CompanyRepository;
import com.energyinvestmentdata.service.CompanyService;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.CompanyDto;
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
@RequestMapping("api/v1/company")
public class CompanyController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CompanyDto>> createCompany(@Valid @RequestBody CreateCompanyRequest companyRequest){

        CompanyEntity companyEntity = modelMapper.map(companyRequest, CompanyEntity.class);
        CompanyEntity createdCompany = companyRepository.save(companyEntity);
        CompanyDto companyDto = modelMapper.map(createdCompany, CompanyDto.class);

        ApiResponse<CompanyDto> response = ApiResponse.<CompanyDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.CREATED.value()).data(companyDto).error("").build();

        return ResponseEntity.ok().body(response);

    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping( path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CompanyDto>> getCompany(@PathVariable Long id){
          CompanyDto companyDto =     companyService.getCompanyByCompanyId(id);

        ApiResponse<CompanyDto> response = ApiResponse.<CompanyDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(companyDto).error("").build();

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PutMapping(path = "/{id}" ,  consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<CompanyDto>> updateCompany(@PathVariable Long id, @RequestBody CreateCompanyRequest request){
        CompanyDto companyDto = modelMapper.map(request, CompanyDto.class);
        CompanyDto updatedCompany = companyService.updateCompany(id,companyDto);

        ApiResponse<CompanyDto> response = ApiResponse.<CompanyDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(updatedCompany).error("").build();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<CompanyDto>>> getAllCompany(){

        List<CompanyDto> companyDtoList = companyService.findAll();
        ApiResponse<List<CompanyDto>> response = ApiResponse.<List<CompanyDto>>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(companyDtoList).error("").build();
        return ResponseEntity.ok().body(response);
    }



}
