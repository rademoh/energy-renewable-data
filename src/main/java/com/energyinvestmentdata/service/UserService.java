package com.energyinvestmentdata.service;

import com.energyinvestmentdata.security.UserPrincipal;
import com.energyinvestmentdata.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Rabiu Ademoh
 */
public interface UserService extends UserDetailsService {

       UserDto createUser(UserDto user);
       UserPrincipal loadUserByUserId(String userId);

}
