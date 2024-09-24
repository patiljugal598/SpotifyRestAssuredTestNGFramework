package com.spotify.outh2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.outh2.api.Route.BASE_PATH;

public class SpecBuilder {

    public static RequestSpecification getRequestSpecification(){
        String BASE_URI = System.getProperty("BASE_URI","https://api.spotify.com");
        return new RequestSpecBuilder().setBaseUri(BASE_URI)
                .setBasePath(BASE_PATH).setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL).build();
    }

    public static RequestSpecification getAccountRequestSpecification(){
        String ACCOUNT_BASE_URI = System.getProperty("ACCOUNT_BASE_URI","https://accounts.spotify.com");

        return new RequestSpecBuilder().setBaseUri(ACCOUNT_BASE_URI)
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL).build();
    }

    public static ResponseSpecification getResponseSpecification(){
        return new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }
}
