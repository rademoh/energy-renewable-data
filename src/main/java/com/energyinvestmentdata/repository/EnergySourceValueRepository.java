package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.EnergySourceValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rabiu Ademoh
 */
public interface EnergySourceValueRepository  extends JpaRepository<EnergySourceValueEntity,Long> {
}
