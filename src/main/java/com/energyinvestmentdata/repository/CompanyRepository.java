package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Rabiu Ademoh
 */
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {


}
