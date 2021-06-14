package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.PrivilegeEntity;
import com.energyinvestmentdata.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rabiu Ademoh
 */
public interface PrivilegeRepository  extends JpaRepository<PrivilegeEntity, Long> {

    PrivilegeEntity findByName(String name);
}
