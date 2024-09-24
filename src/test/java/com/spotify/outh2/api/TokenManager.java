package com.spotify.outh2.api;

import com.spotify.outh2.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){
        try{

            if(access_token == null || Instant.now().isAfter(expiry_time)){
                access_token = renewToken().path("access_token");
                int expiryDurationInSeconds = renewToken().path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds-300);
            }else{
                System.out.println("Token is good to use");
            }

        }catch (Exception e){
            throw new RuntimeException("ABORT!!! Failed to renew token.");

        }
            return access_token;
    }

    public static Response renewToken(){

        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("grant_type",ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret",ConfigLoader.getInstance().getClientSecret());

       Response response = RestResource.postAccount(formParams);
        if(response.statusCode()!=200){
            throw new RuntimeException("ABORT!!! Renew token failed.");
        }

        return response;
    }
}
