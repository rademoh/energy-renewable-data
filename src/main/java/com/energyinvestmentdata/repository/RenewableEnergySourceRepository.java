package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.RenewableEnergySourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rabiu Ademoh
 */
public interface RenewableEnergySourceRepository extends JpaRepository<RenewableEnergySourceEntity,Long> {


}
