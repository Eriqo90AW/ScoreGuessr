package com.eriqoariefjsleeprj.frontend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eriqoariefjsleeprj.frontend.misc.AppPreferences;
import com.eriqoariefjsleeprj.frontend.model.LoginResponse;
import com.eriqoariefjsleeprj.frontend.model.RefreshResponse;
import com.eriqoariefjsleeprj.frontend.model.User;
import com.eriqoariefjsleeprj.frontend.request.BaseApiService;
import com.eriqoariefjsleeprj.frontend.request.RefreshData;
import com.eriqoariefjsleeprj.frontend.request.UtilsApi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private BaseApiService retrofitInterface;
    protected static User currentUser = null;
    BaseApiService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mApiService = UtilsApi.getApiService();
        mContext = this;

        retrofit = new Retrofit.Builder()
                .baseUrl(LandingActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(BaseApiService.class);

        findViewById(R.id.login_backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent move = new Intent(LoginActivity.this, LandingActivity.class);
                startActivity(move);
            }
        });

        findViewById(R.id.login_loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

    }

    private void handleLogin() {
        EditText userEdit = findViewById(R.id.login_userInput);
        EditText passEdit = findViewById(R.id.login_passInput);
        Button loginBtn = findViewById(R.id.login_loginButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> data = new HashMap<>();

                data.put("username", userEdit.getText().toString());
                data.put("password", passEdit.getText().toString());

                requestLogin(data);

            }
        });

    }

    protected LoginResponse requestLogin(HashMap<String, String> data){
        mApiService.executeLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.code() == 200){
                    LoginResponse result = response.body();
                    if(result.getRefreshToken() != null){
                        AppPreferences preferences = new AppPreferences(mContext);
                        preferences.setRefreshToken(result.getRefreshToken());
                        RefreshData token = new RefreshData(result.getRefreshToken());
                        requestRefresh(token);
                    }else{
                        Toast.makeText(mContext, "No user found", Toast.LENGTH_LONG).show();
                    }
                }else if (response.code() == 400){
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }

    protected User requestUser(String token){
        mApiService.getUser("Bearer " + token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){
                    User result = response.body();
                    if(result.getUsername() != null){
                        currentUser = result;
                        Intent move = new Intent(LoginActivity.this, MainActivity.class);
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