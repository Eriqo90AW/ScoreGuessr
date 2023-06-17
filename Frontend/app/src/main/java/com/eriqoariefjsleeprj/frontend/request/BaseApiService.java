package com.eriqoariefjsleeprj.frontend.request;

import com.eriqoariefjsleeprj.frontend.model.Fixture;
import com.eriqoariefjsleeprj.frontend.model.Reward;
import com.eriqoariefjsleeprj.frontend.model.User;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {
    @POST("/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

    @POST("/register")
    Call<Void> executeRegister(@Body RegistrationData data);

    @GET("/fixtures/upcoming")
    Call<ArrayList<Fixture>> getFixtures();

    @GET("/rewards")
    Call<ArrayList<Reward>> getRewards();
}
