package com.spotify.outh2.utils;

import java.util.Properties;

public class ConfigLoader {

    static Properties properties;
    private static ConfigLoader configLoader;


    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader==null){
            configLoader=new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientId(){
        String value = properties.getProperty("client_id");
        if(value !=null)
            return value;
        else throw new RuntimeException("property client_id is not specified in ");
    }

    public String getClientSecret(){
        String value = properties.getProperty("client_secret");
        if(value !=null)
            return value;
        else throw new RuntimeException("property client_secret is not specified in ");
    }

    public String getGrantType(){
        String value = properties.getProperty("grant_type");
        if(value !=null)
            return value;
        else throw new RuntimeException("property grant_type is not specified in ");
    }

    public String getRefreshToken(){
        String value = properties.getProperty("refresh_token");
        if(value !=null)
            return value;
        else throw new RuntimeException("property refresh_token is not specified in ");
    }

    public String getUserId(){
        String value = properties.getProperty("user_id");
        if(value !=null)
            return value;
        else throw new RuntimeException("property user_id is not specified in ");
    }
}
