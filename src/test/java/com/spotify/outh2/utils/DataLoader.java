package com.spotify.outh2.utils;

import java.util.Properties;

public class DataLoader {

    static Properties properties;
    private static DataLoader configLoader;
    private DataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/data.properties");
    }
    public static DataLoader getInstance(){
        if(configLoader==null){
            configLoader=new DataLoader();
        }
        return configLoader;
    }

    public String getPlayListId(){
        String value = properties.getProperty("get_playlist_id");
        if(value !=null)
            return value;
        else throw new RuntimeException("property get_playlist_id is not specified in ");
    }

    public String getUpdatePlayListId(){
        String value = properties.getProperty("update_playlist_id");
        if(value !=null)
            return value;
        else throw new RuntimeException("property update_playlist_id is not specified in ");
    }
}
