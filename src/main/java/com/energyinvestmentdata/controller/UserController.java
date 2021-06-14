package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.UserEntity;
import com.energyinvestmentdata.exceptions.BadRequestException;
import com.energyinvestmentdata.model.request.LoginRequest;
import com.energyinvestmentdata.model.request.UserDetailsRequestModel;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.repository.RoleRepository;
import com.energyinvestmentdata.repository.UserRepository;
import com.energyinvestmentdata.security.JwtTokenProvider;
import com.energyinvestmentdata.security.UserPrincipal;
import com.energyinvestmentdata.service.UserService;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

import static com.energyinvestmentdata.security.SecurityConstants.TOKEN_PREFIX;


/**
 * @author Rabiu Ademoh
 */
//@CrossOrigin(origins = "http://localhost:8011")
@RestController
@RequestMapping("api/v1/users/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;



    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserDto>> registerUser(@Valid @RequestBody UserDetailsRequestModel userDetails){

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        userDto.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        UserDto createdUser = userService.createUser(userDto);

        ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.OK.value()).data(createdUser).error("").build();

        return ResponseEntity.ok().body(response);


    }

    @PostMapping( path = "admin",consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserDto>> registerUserAdmin(@Valid @RequestBody UserDetailsRequestModel userDetails){

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        userDto.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));

        UserDto createdUser = userService.createUser(userDto);

        ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
                .message(ResponseMessage.SUCCESSFULLY_CREATED)
                .status(HttpStatus.OK.value()).data(createdUser).error("").build();

        return ResponseEntity.ok().body(response);


    }

    @PostMapping( path = "/login" , consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UserDto>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        if( !userDetails.isEnabled()){
            throw new BadCredentialsException("You have been disabled.");
        }

        Optional<UserEntity> userEntity = userRepository.findByUserId(userDetails.getUserId());

        UserDto userDto = modelMapper.map(userEntity.get(), UserDto.class);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

        userDto.setToken(jwt);

        ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(userDto).error("").build();

        return ResponseEntity.ok().body(response);
    }




}
