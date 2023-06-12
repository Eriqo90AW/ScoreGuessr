package com.eriqoariefjsleeprj.frontend.request;

import com.eriqoariefjsleeprj.frontend.model.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BaseApiService {
    @POST("/login")
    Call<User> executeLogin(@Body HashMap<String, String> map);

    @POST("/register")
    Call<Void> executeRegister(@Body RegistrationData data);
}
