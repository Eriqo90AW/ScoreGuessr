package com.eriqoariefjsleeprj.frontend.request;

import com.eriqoariefjsleeprj.frontend.model.Fixture;
import com.eriqoariefjsleeprj.frontend.model.LoginResponse;
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
}
