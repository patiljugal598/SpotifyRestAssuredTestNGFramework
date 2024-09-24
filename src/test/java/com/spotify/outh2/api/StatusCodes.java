package com.spotify.outh2.api;

public enum StatusCodes {
    CODE200(200,""),
    CODE201(201,""),
    CODE400(400,"Missing required field: name"),
    CODE401(401,"Invalid access token");

    public int code;
    public String msg;


    StatusCodes(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }
}
