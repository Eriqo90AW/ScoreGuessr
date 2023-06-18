package com.eriqoariefjsleeprj.frontend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eriqoariefjsleeprj.frontend.misc.AppPreferences;
import com.eriqoariefjsleeprj.frontend.model.RefreshResponse;
import com.eriqoariefjsleeprj.frontend.model.User;
import com.eriqoariefjsleeprj.frontend.request.BaseApiService;
import com.eriqoariefjsleeprj.frontend.request.RefreshData;
import com.eriqoariefjsleeprj.frontend.request.RegistrationData;
import com.eriqoariefjsleeprj.frontend.request.UtilsApi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingActivity extends AppCompatActivity {
    protected static final String BASE_URL = "http://10.0.2.2:8416";
    protected static User userData = null;
    BaseApiService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mApiService = UtilsApi.getApiService();
        mContext = this;


        AppPreferences preferences = new AppPreferences(mContext);
        String storedToken = preferences.getRefreshToken();
        if(storedToken != ""){
            RefreshData token = new RefreshData(storedToken);
            requestRefresh(token);
        }
    }

    protected void onStart(){
        super.onStart();
        findViewById(R.id.landing_loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent= new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        findViewById(R.id.landing_registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent= new Intent(LandingActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    protected User requestUser(String token){
        mApiService.getUser("Bearer " + token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){
                    User result = response.body();
                    if(result.getUsername() != null){
                        LoginActivity.currentUser = result;
                        Intent move = new Intent(LandingActivity.this, MainActivity.class);
                        startActivity(move);
                        finish();
                    }else{
                        Toast.makeText(mContext, "No user found", Toast.LENGTH_LONG).show();
                    }
                }else if (response.code() == 400){
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }

    protected RefreshResponse requestRefresh(RefreshData token){
        mApiService.refreshToken(token).enqueue(new Callback<RefreshResponse>() {
            @Override
            public void onResponse(Call<RefreshResponse> call, Response<RefreshResponse> response) {
                if(response.code() == 200){
                    RefreshResponse result = response.body();
                    if(result.getAccessToken() != null){
                        requestUser(result.getAccessToken());
                    }else{
                        Toast.makeText(mContext, "No token found", Toast.LENGTH_LONG).show();
                    }
                }else if (response.code() == 400){
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RefreshResponse> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }
}