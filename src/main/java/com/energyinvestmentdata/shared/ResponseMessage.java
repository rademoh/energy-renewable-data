package com.energyinvestmentdata.shared;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class ResponseMessage {


    public static final String SUCCESSFUL = "Successful";
    public static final String SUCCESSFULLY_CREATED = "Successfully created";
    public static final String PENDING = "Pending approval";
    public static final String FAILED = "Failed request";
    public static final String GET = "Successfully fetched records";


}
