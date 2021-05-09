package com.energyinvestmentdata.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Rabiu Ademoh
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsRequestModel {

    @NotBlank(message = "Username field is required")
    private String username;

    @NotBlank(message = "Password field is required")
    private String password;

    @NotBlank(message = "EmailAddress field is required")
    private String emailAddress;



}
