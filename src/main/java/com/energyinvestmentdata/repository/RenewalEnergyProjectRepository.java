package com.energyinvestmentdata.repository;


import com.energyinvestmentdata.entity.RenewableEnergyProjectEntity;
import com.energyinvestmentdata.model.request.PaginationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

/**
 * @author Rabiu Ademoh
 */
public interface RenewalEnergyProjectRepository extends JpaRepository<RenewableEnergyProjectEntity, Long> {

    RenewableEnergyProjectEntity findByProjectName(String name);
   // Page<RenewableEnergyProjectEntity>



}
