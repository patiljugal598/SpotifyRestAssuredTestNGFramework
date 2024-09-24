package com.spotify.outh2.api;

import com.spotify.outh2.pojo.playlist.PlayList;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.outh2.api.Route.API;
import static com.spotify.outh2.api.Route.TOKEN;
import static com.spotify.outh2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {


    public static Response post(String token, PlayList playListRequest,String path){
        return given(getRequestSpecification())
                .auth().oauth2(token)
                .body(playListRequest).when().post(path)
                .then().spec(getResponseSpecification())
                .extract().response();
    }


    public static Response get(String path, String token){
        return given(getRequestSpecification())
                .auth().oauth2(token)
                .when().get(path)
                .then().spec(getResponseSpecification()).extract()
                .response();
    }

    public static Response put(PlayList playListRequest, String path, String token){
        return given(getRequestSpecification()).auth().oauth2(token)
                .body(playListRequest).when().put(path)
                .then().spec(getResponseSpecification()).extract().response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return RestAssured.given(getAccountRequestSpecification())
                .formParams(formParams).when().post(API+TOKEN).then().extract().response();
    }
}
