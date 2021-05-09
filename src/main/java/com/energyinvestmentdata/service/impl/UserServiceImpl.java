package com.energyinvestmentdata.service.impl;

import com.energyinvestmentdata.entity.UserEntity;
import com.energyinvestmentdata.exceptions.UsernameAlreadyExistsException;
import com.energyinvestmentdata.repository.UserRepository;
import com.energyinvestmentdata.security.UserPrincipal;
import com.energyinvestmentdata.service.UserService;
import com.energyinvestmentdata.shared.Utils;
import com.energyinvestmentdata.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Rabiu Ademoh
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    Utils utils;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        if( userRepository.findByUsername(userDto.getUsername()) != null) throw new UsernameAlreadyExistsException("There is an account with that username: " + userDto.getUsername());

        String publicId = utils.generateUserId(10);
        UserEntity userEntity = modelMapper.map(userDto,UserEntity.class);
        userEntity.setUserId(publicId);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setRoles(userDto.getRoles());
        userEntity.setEnabled(true);

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

        return returnValue;
    }

    @Override
    public UserPrincipal loadUserByUserId(String userId) {
        UserEntity userEntity  = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException(userId));
        return new UserPrincipal(userEntity);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity  = userRepository.findByUsername(username);

        if( userEntity == null) throw new UsernameNotFoundException(username);

        return new UserPrincipal(userEntity);
    }



}
