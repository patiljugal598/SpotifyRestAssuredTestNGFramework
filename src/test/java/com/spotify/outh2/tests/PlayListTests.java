package com.spotify.outh2.tests;

import com.spotify.outh2.api.StatusCodes;
import com.spotify.outh2.api.applicationAPI.PlayListAPI;
import com.spotify.outh2.pojo.error.ErrorRoot;
import com.spotify.outh2.pojo.playlist.PlayList;


import static com.spotify.outh2.utils.FakerUtils.generateDescription;
import static com.spotify.outh2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.spotify.outh2.utils.DataLoader;
import com.spotify.outh2.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Epic("Spotify Outh 2.0")
@Feature("Playlist API")
public class PlayListTests extends BaseTest{

    @Story("Create a Playlist story")
    @Link("https://google.com")
    @TmsLink("18042")
    @Issue("202256")
    @Description("This is description")
    @Test(description = "Should Be Able To Create PlayList")
    public void shouldBeAbleToCreatePlayList(){
        PlayList playListRequest = new PlayList();
        playListRequest.setName(generateName());
        playListRequest.setDescription(generateDescription());
        playListRequest.set_public(false);

        Response response = PlayListAPI.post(playListRequest);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE201.code));
        assertThat(response.contentType(),equalTo("application/json; charset=utf-8"));

        PlayList playList = response.as(PlayList.class);

        assertThat(playList.getName(), equalTo(playListRequest.getName()));
        assertThat(playList.getDescription(), equalTo(playListRequest.getDescription()));
        assertThat(playList.get_public(), equalTo(playListRequest.get_public()));

    }

    @Story("Create a Playlist story")
    @Test
    public void shouldNotBeAbleToCreatePlayListWithoutName(){
        PlayList playListRequest = new PlayList();
        playListRequest.setName("");
        playListRequest.setDescription(generateDescription());
        playListRequest.set_public(false);

        Response response = PlayListAPI.post(playListRequest);
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE400.code));
        ErrorRoot errorRoot = response.as(ErrorRoot.class);
        assertThat(errorRoot.getError().getStatus(),equalTo(StatusCodes.CODE400.code));
        assertThat(errorRoot.getError().getMessage(),equalTo(StatusCodes.CODE400.msg));
        assertThat(response.contentType(),equalTo("application/json; charset=utf-8"));
    }

    @Story("Create a Playlist story")
    @Test
    public void shouldNotBeAbleToCreatePlayListWithInvalidToken(){
        PlayList playListRequest = new PlayList();
        playListRequest.setName(generateName());
        playListRequest.setDescription(generateDescription());
        playListRequest.set_public(false);

        Response response = PlayListAPI.post(playListRequest,"Invalid_12345");
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE401.code));
        ErrorRoot errorRoot = response.as(ErrorRoot.class);
        assertThat(errorRoot.getError().getStatus(),equalTo(StatusCodes.CODE401.code));
        assertThat(errorRoot.getError().getMessage(),equalTo(StatusCodes.CODE401.msg));
        assertThat(response.contentType(),equalTo("application/json"));
    }

    @Test
    public void shouldBeAbleToGetPlayList(){
        Response response = PlayListAPI.get(DataLoader.getInstance().getPlayListId());
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE200.code));

        PlayList playList = response.as(PlayList.class);

        assertThat(playList.getName(),equalTo("New Playlist"));
        assertThat(playList.getDescription(),equalTo("New playlist description1"));
        assertThat(playList.get_public(),equalTo(true));
    }

    @Test
    public void shouldBeAbleToUpdatePlayList(){
        PlayList playListRequest = new PlayList();
        playListRequest.setName(generateName());
        playListRequest.setDescription(generateDescription());
        playListRequest.set_public(false);

        Response response = PlayListAPI.put(playListRequest, DataLoader.getInstance().getUpdatePlayListId());
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE200.code));

    }
}
