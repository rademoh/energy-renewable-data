package com.energyinvestmentdata.security;

import com.energyinvestmentdata.entity.PrivilegeEntity;
import com.energyinvestmentdata.entity.RoleEntity;
import com.energyinvestmentdata.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author Rabiu Ademoh
 */
public class UserPrincipal implements UserDetails {

    private UserEntity userEntity;
    private String userId;

    public UserPrincipal(UserEntity userEntity) {
        this.userEntity = userEntity;
        this.userId = userEntity.getUserId();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Collection<PrivilegeEntity> privilegeEntities = new HashSet<>();

        //get user roles
        Collection<RoleEntity> roles = userEntity.getRoles();

        if ( roles == null) return authorities;

        roles.forEach( role -> {
            authorities.add( new SimpleGrantedAuthority(role.getName()));
            privilegeEntities.addAll( role.getPrivileges());
        });

        privilegeEntities.forEach( privilegeEntity -> {
            authorities.add( new SimpleGrantedAuthority(privilegeEntity.getName()));
        });

       return authorities;
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.userEntity.isEnabled();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
