package com.energyinvestmentdata.repository;

import com.energyinvestmentdata.entity.PublicSectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Rabiu Ademoh
 */
public interface PublicSectorRepository  extends JpaRepository<PublicSectorEntity,Long> {


}
