package com.energyinvestmentdata.security;


import com.energyinvestmentdata.SpringApplicationContext;

/**
 * @author Rabiu Ademoh
 */
public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/v1/users/**";
    public static final String H2_URL = "/api/v1/users/login/";
    public static final String SECRET = getTokenSecret();
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3600*1000; //5 MINUTES


    public static String getTokenSecret(){
       // AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return "rab@#14@3cjf9i4gu83nfl0o6yGEHHgde9ddrbL744"; //appProperties.getTokenSecret();
    }




}
