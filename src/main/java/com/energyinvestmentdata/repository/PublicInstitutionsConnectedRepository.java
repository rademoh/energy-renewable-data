package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.PublicInstitutionsConnectedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Rabiu Ademoh
 */
public interface PublicInstitutionsConnectedRepository  extends JpaRepository<PublicInstitutionsConnectedEntity,Long> {

    @Modifying
    @Query( value = "DELETE FROM public_institutions_connected pic WHERE pic.renewableEnergyProjectEntity.id = :projectId")
    Integer deleteByProjectId(@Param("projectId") Long projectId);
}
