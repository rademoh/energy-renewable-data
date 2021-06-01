package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.EnergySourceValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Rabiu Ademoh
 */
public interface EnergySourceValueRepository  extends JpaRepository<EnergySourceValueEntity,Long> {

    @Modifying
    @Query( value = "DELETE FROM energy_source_value esv WHERE esv.renewableEnergyProjectEntity.id = :projectId")
     Integer deleteByProjectId(@Param("projectId") Long projectId);

}
