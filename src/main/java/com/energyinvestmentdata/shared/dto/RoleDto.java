package com.energyinvestmentdata.shared.dto;

import com.energyinvestmentdata.entity.PrivilegeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

/**
 * @author Rabiu Ademoh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @NotBlank(message = "Role name is required")
    private String name;
    private Collection<PrivilegeEntity> privileges;
}
