package com.spotify.outh2.api.applicationAPI;

import com.spotify.outh2.api.RestResource;
import com.spotify.outh2.pojo.playlist.PlayList;
import com.spotify.outh2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.outh2.api.Route.PLAYLIST;
import static com.spotify.outh2.api.Route.USER;
import static com.spotify.outh2.api.TokenManager.getToken;


public class PlayListAPI {

    @Step("Post call with PlayListName - {playListRequest.name}")
    public static Response post(PlayList playListRequest){
        return RestResource.post(getToken(),playListRequest, USER+"/"+ ConfigLoader.getInstance().getUserId() +PLAYLIST);
    }

    @Step("Post call with PlayListName - {playListRequest.name}")
    public static Response post(PlayList playListRequest, String accessToken){
        return RestResource.post(accessToken,playListRequest,USER+"/"+ ConfigLoader.getInstance().getUserId()+PLAYLIST);
    }

    @Step("Post call with PlayListId - {playListId}")
    public static Response get(String playListId){
        return RestResource.get(PLAYLIST+"/"+playListId,getToken());
    }

    @Step("Post call with PlayListName - {playListRequest.name} and PlayListId - {playListId}")
    public static Response put(PlayList playListRequest, String playListId){
        return RestResource.put(playListRequest,PLAYLIST+"/"+playListId,getToken());
    }
}
