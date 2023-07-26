package com.eriqoariefjsleeprj.frontend.request;

import com.eriqoariefjsleeprj.frontend.model.Fixture;
import com.eriqoariefjsleeprj.frontend.model.LoginResponse;
import com.eriqoariefjsleeprj.frontend.model.Message;
import com.eriqoariefjsleeprj.frontend.model.MiniLeague;
import com.eriqoariefjsleeprj.frontend.model.MiniLeagueInfo;
import com.eriqoariefjsleeprj.frontend.model.MyReward;
import com.eriqoariefjsleeprj.frontend.model.RefreshResponse;
import com.eriqoariefjsleeprj.frontend.model.Reward;
import com.eriqoariefjsleeprj.frontend.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiService {
    @POST("/login")
    Call<LoginResponse> executeLogin(@Body HashMap<String, String> map);

    @POST("/register")
    Call<Void> executeRegister(@Body RegistrationData data);

    @GET("/fixtures/upcoming")
    Call<ArrayList<Fixture>> getFixtures();

    @GET("/rewards")
    Call<ArrayList<Reward>> getRewards();

    @POST("/refresh")
    Call<RefreshResponse> refreshToken(@Body RefreshData token);

    @GET("/getUser")
    Call<User> getUser(@Header("Authorization") String authHeader);

    @POST("/logout")
    Call<Void> executeLogout(@Body RefreshData token);

    @GET("/rewards/{id}")
    Call<ArrayList<MyReward>> getMyRewards(@Path("id") int userId);

    @PUT("/updateRewards/{id}")
    Call<Void> updateUserRewards(@Path("id") int userId, @Body HashMap<String, Integer> rewards_id);

    @PUT("/updatePoints/{id}")
    Call<Void> updateUserPoints(@Path("id") int userId, @Body HashMap<String, Float> total_points);

    @PUT("/updateMiniLeague/{id}")
    Call<Message> updateUserMiniLeague(@Path("id") int userId, @Body HashMap<String, String> mini_league_code);

    @GET("/mini-leagues/{code}")
    Call<ArrayList<MiniLeagueInfo>> getMiniLeagueUsers(@Path("code") String code);

    @GET("/mini-leagues/info/{code}")
    Call<MiniLeague> getMiniLeagueInfo(@Path("code") String code);

    @POST("/mini-leagues/addUser")
    Call<Message> adduserToMiniLeague(@Body HashMap<String, String> data);
}
