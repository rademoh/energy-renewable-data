package com.energyinvestmentdata.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Rabiu Ademoh
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEnergySourceRequest {

    @NotBlank(message = "name is required")
    private String name;
}
