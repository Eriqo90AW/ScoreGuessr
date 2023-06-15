package com.eriqoariefjsleeprj.frontend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eriqoariefjsleeprj.frontend.request.BaseApiService;
import com.eriqoariefjsleeprj.frontend.request.RegistrationData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private BaseApiService retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        retrofit = new Retrofit.Builder()
                .baseUrl(LandingActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(BaseApiService.class);

        findViewById(R.id.register_backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        findViewById(R.id.register_registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRegister();
            }
        });
    }

    private void handleRegister() {
        EditText userEdit = findViewById(R.id.register_userInput);
        EditText emailEdit = findViewById(R.id.register_emailInput);
        EditText passEdit = findViewById(R.id.register_passInput);
        Button registerBtn = findViewById(R.id.register_registerButton);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userEdit.getText().toString().equals("") ||
                        emailEdit.getText().toString().equals("") ||
                        passEdit.getText().toString().equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill out the form", Toast.LENGTH_LONG).show();
                    return;
                }
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
                            Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                        }else if(response.code() == 400){
                            Toast.makeText(RegisterActivity.this, "Failed to register", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}