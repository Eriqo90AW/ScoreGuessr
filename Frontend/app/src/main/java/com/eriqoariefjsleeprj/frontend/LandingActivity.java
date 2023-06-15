package com.eriqoariefjsleeprj.frontend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eriqoariefjsleeprj.frontend.model.User;
import com.eriqoariefjsleeprj.frontend.request.BaseApiService;
import com.eriqoariefjsleeprj.frontend.request.RegistrationData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private BaseApiService retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:8416";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(BaseApiService.class);

        findViewById(R.id.landing_loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        findViewById(R.id.landing_registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegister();
            }
        });
    }

    private void handleLogin() {
        View view = getLayoutInflater().inflate(R.layout.activity_login, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view).show();

        EditText userEdit = view.findViewById(R.id.login_userInput);
        EditText passEdit = view.findViewById(R.id.login_passInput);
        Button loginBtn = view.findViewById(R.id.login_loginButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> data = new HashMap<>();

                data.put("username", userEdit.getText().toString());
                data.put("password", passEdit.getText().toString());

                Call<User> call = retrofitInterface.executeLogin(data);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.code() == 200){
                            User result = response.body();

                            AlertDialog.Builder builder = new AlertDialog.Builder(LandingActivity.this);

                            builder.setTitle(result.getUsername());
                            builder.setMessage(result.getEmail());
                            builder.show();
                        }else if (response.code() == 400){
                            Toast.makeText(LandingActivity.this, "Wrong username/password", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LandingActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    private void handleRegister() {
        View view = getLayoutInflater().inflate(R.layout.activity_register, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view).show();

        EditText userEdit = view.findViewById(R.id.register_userInput);
        EditText emailEdit = view.findViewById(R.id.register_emailInput);
        EditText passEdit = view.findViewById(R.id.register_passInput);
        Button registerBtn = view.findViewById(R.id.register_registerButton);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationData data = new RegistrationData(
                        userEdit.getText().toString(),
                        emailEdit.getText().toString(),
                        passEdit.getText().toString()
                );

                Call<Void> call = retrofitInterface.executeRegister(data);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            Toast.makeText(LandingActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                        }else if(response.code() == 400){
                            Toast.makeText(LandingActivity.this, "Failed to register", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(LandingActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}