package com.energyinvestmentdata.shared.dto;

import com.energyinvestmentdata.entity.RenewableEnergyProjectEntity;
import com.energyinvestmentdata.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rabiu Ademoh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;

    private String name;

    private Set<RenewableEnergyProjectEntity> renewableEnergyProjectEntities  = new HashSet<>();

    private Set<UserEntity> userEntity  = new HashSet<>();
}
