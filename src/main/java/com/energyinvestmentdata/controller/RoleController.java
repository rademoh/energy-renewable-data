package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.RenewableEnergySourceEntity;
import com.energyinvestmentdata.entity.RoleEntity;
import com.energyinvestmentdata.exceptions.NotFoundException;
import com.energyinvestmentdata.exceptions.UsernameAlreadyExistsException;
import com.energyinvestmentdata.model.request.CreateEnergySourceRequest;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.repository.RenewableEnergySourceRepository;
import com.energyinvestmentdata.repository.RoleRepository;
import com.energyinvestmentdata.service.RenewableEnergySourceService;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.RenewalEnergySourceDto;
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
@RequestMapping("api/v1/roles")
public class RoleController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleRepository roleRepository;

    //SELECT p.* FROM roles_privileges rp left join privilege p on p.id = rp.privilege_id where rp.role_id = 181


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RoleDto>> createRole(@Valid @RequestBody RoleDto request){
        RoleEntity role = roleRepository.findByName(request.getName());
        if (role == null) {
            role = new RoleEntity();
            role.setName(request.getName());
            role.setPrivileges(request.getPrivileges());
            roleRepository.save(role);
        }else {
            throw new UsernameAlreadyExistsException("Role name already exist");
        }

        RoleDto roleDto = modelMapper.map(role, RoleDto.class);

        ApiResponse<RoleDto> response = ApiResponse.<RoleDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.CREATED.value()).data(roleDto).error("").build();

        return ResponseEntity.ok().body(response);

    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping( path = "/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<RoleDto>> getRoleId(@PathVariable Long id){

        Optional<RoleEntity> roleEntity =  roleRepository.findById(id);
        roleEntity.orElseThrow(() ->  new NotFoundException("Role doesn't exist"));
        RoleDto roleDto = modelMapper.map(roleEntity, RoleDto.class);
        ApiResponse<RoleDto> response = ApiResponse.<RoleDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(roleDto).error("").build();

        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<RoleDto>>> getAllRoles(){
        List<RoleEntity> roleDtoList = roleRepository.findAll();
        List<RoleDto> roleDtoList1 = modelMapper.map(roleDtoList,new TypeToken<List<RoleDto>>() {}.getType());
        ApiResponse<List<RoleDto>> response = ApiResponse.<List<RoleDto>>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(roleDtoList1).error("").build();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @DeleteMapping( path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deleteRole(@PathVariable Long id){
             roleRepository.deleteById(id);
        ApiResponse<?> response = ApiResponse.builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data("").error("").build();
        return ResponseEntity.ok().body(response);
    }



}
