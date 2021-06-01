package com.energyinvestmentdata.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnergyProjectFilterRequest {

   long id;
    String name;
    long facultyId;
    @Builder.Default
    private Integer page = 0;
    @Builder.Default
    private Integer size = 20;
/*
    long id;
    String name;
    long facultyId;
    private Integer page;
    private Integer size;*/
}
