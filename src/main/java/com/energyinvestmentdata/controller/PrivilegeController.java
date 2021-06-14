package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.PrivilegeEntity;
import com.energyinvestmentdata.entity.RoleEntity;
import com.energyinvestmentdata.exceptions.NotFoundException;
import com.energyinvestmentdata.exceptions.UsernameAlreadyExistsException;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.repository.PrivilegeRepository;
import com.energyinvestmentdata.repository.RoleRepository;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.PrivilegeDto;
import com.energyinvestmentdata.shared.dto.RoleDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Rabiu Ademoh
 */

@RestController
@RequestMapping("api/v1/privilege")
public class PrivilegeController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PrivilegeRepository privilegeRepository;



    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PrivilegeDto>> createPrivilege(@Valid @RequestBody PrivilegeDto request){
        PrivilegeEntity privilegeEntity = privilegeRepository.findByName(request.getName());
        if (privilegeEntity == null) {
            privilegeEntity = new PrivilegeEntity();
            privilegeEntity.setName(request.getName());
            privilegeRepository.save(privilegeEntity);
        }else {
            throw new UsernameAlreadyExistsException("Privilege name already exist");
        }
        PrivilegeDto privilegeDto = modelMapper.map(privilegeEntity, PrivilegeDto.class);

        ApiResponse<PrivilegeDto> response = ApiResponse.<PrivilegeDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.CREATED.value()).data(privilegeDto).error("").build();

        return ResponseEntity.ok().body(response);

    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping( path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<PrivilegeDto>> getPrivilegeId(@PathVariable Long id){

        Optional<PrivilegeEntity> privilegeEntity =  privilegeRepository.findById(id);
        privilegeEntity.orElseThrow(() ->  new NotFoundException("Privilege doesn't exist"));
        PrivilegeDto privilegeDto = modelMapper.map(privilegeEntity, PrivilegeDto.class);
        ApiResponse<PrivilegeDto> response = ApiResponse.<PrivilegeDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(privilegeDto).error("").build();

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<PrivilegeDto>>> getAllPrivileges(){
        List<PrivilegeEntity> privilegeEntityList = privilegeRepository.findAll();
        List<PrivilegeDto> privilegeDtos = modelMapper.map(privilegeEntityList,new TypeToken<List<PrivilegeDto>>() {}.getType());
        ApiResponse<List<PrivilegeDto>> response = ApiResponse.<List<PrivilegeDto>>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(privilegeDtos).error("").build();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @DeleteMapping( path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deletePrivilege(@PathVariable Long id){
             privilegeRepository.deleteById(id);
        ApiResponse<?> response = ApiResponse.builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data("").error("").build();
        return ResponseEntity.ok().body(response);
    }



}
