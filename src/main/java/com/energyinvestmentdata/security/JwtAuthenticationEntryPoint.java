package com.energyinvestmentdata.security;

import com.energyinvestmentdata.model.response.ApiResponse;
import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rabiu Ademoh
 */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        //httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        httpServletResponse.setContentType("application/json");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(e.getMessage());
        apiResponse.setStatus(401);
        apiResponse.setData("");
        apiResponse.setError(e.getLocalizedMessage());
        String jsonLoginResponse = new Gson().toJson(apiResponse);
        httpServletResponse.getWriter().print(jsonLoginResponse);



    }
}
