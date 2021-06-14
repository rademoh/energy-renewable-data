package com.energyinvestmentdata.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Rabiu Ademoh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeDto {

    private Long id;
    private String name;


}
