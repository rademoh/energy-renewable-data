package com.energyinvestmentdata.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rabiu Ademoh
 */

@RestController
@RequestMapping("api/v1/test/")
public class TestSecurityController {

    @GetMapping("/access_user")
    @PreAuthorize("hasRole('USER')")
    public String accessUser() {
        return "User";
    }

    @GetMapping("/access_admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin() {
        return "Admin.";
    }

    @GetMapping("/view_privilege")
    @PreAuthorize("hasAuthority('VIEW_PRIVILEGE')")
    public String viewPrivilege() {
        return "Admin.";
    }

    @GetMapping("/update_privilege")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public String updatePrivilege() {
        return "Admin.";
    }

}
