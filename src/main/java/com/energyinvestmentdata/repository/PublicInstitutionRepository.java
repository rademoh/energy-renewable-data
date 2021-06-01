package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.PublicInstitutionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Rabiu Ademoh
 */
public interface PublicInstitutionRepository  extends JpaRepository<PublicInstitutionsEntity,Long> {
}
