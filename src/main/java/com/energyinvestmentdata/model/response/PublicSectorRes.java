package com.energyinvestmentdata.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rabiu Ademoh
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublicSectorRes {

    private Long id;

    private String name;
}
