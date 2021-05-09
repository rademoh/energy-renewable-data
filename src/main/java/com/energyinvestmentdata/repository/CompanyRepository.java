package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * @author Rabiu Ademoh
 */
public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {


}
