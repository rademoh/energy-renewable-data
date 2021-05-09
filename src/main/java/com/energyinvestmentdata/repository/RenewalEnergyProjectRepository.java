package com.energyinvestmentdata.repository;


import com.energyinvestmentdata.entity.RenewableEnergyProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rabiu Ademoh
 */
public interface RenewalEnergyProjectRepository extends JpaRepository<RenewableEnergyProjectEntity, Long> {

    RenewableEnergyProjectEntity findByProjectName(String name);
}
