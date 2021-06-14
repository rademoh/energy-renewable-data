package com.energyinvestmentdata.controller;

import com.energyinvestmentdata.entity.CompanyEntity;
import com.energyinvestmentdata.model.request.CreateCompanyRequest;
import com.energyinvestmentdata.model.response.ApiResponse;
import com.energyinvestmentdata.repository.CompanyRepository;
import com.energyinvestmentdata.service.CompanyService;
import com.energyinvestmentdata.shared.ResponseMessage;
import com.energyinvestmentdata.shared.dto.CompanyDto;
import com.energyinvestmentdata.shared.dto.DashboardDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rabiu Ademoh
 */

@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController {

    @Autowired
    ModelMapper modelMapper;


    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping( path = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<List<DashboardDao>>> getDashboardStats(){
        List<DashboardDao> dashboardDaoList = new ArrayList<>(
                Arrays.asList(DashboardDao.builder().title("Total Batteries").value("1,000").logoUrl("").build(),
                        DashboardDao.builder().title("Total Installed Capacity").value("1,500").logoUrl("").build(),
                        DashboardDao.builder().title("Total Co2 Avoided").value("3,500").logoUrl("").build(),
                        DashboardDao.builder().title("Total Debt Mix").value("N34,500,000").logoUrl("").build())
        );
        ApiResponse<List<DashboardDao>> response = ApiResponse.<List<DashboardDao>>builder()
                .message(ResponseMessage.SUCCESSFUL)
                .status(HttpStatus.OK.value()).data(dashboardDaoList).error("").build();
        return ResponseEntity.ok().body(response);
    }



}
