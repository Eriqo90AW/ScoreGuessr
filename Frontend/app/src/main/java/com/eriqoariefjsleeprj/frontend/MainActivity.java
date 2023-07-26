package com.eriqoariefjsleeprj.frontend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eriqoariefjsleeprj.frontend.databinding.ActivityMainBinding;
import com.eriqoariefjsleeprj.frontend.misc.AppPreferences;
import com.eriqoariefjsleeprj.frontend.model.User;
import com.eriqoariefjsleeprj.frontend.request.BaseApiService;
import com.eriqoariefjsleeprj.frontend.request.RefreshData;
import com.eriqoariefjsleeprj.frontend.request.UtilsApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BaseApiService mApiService;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    binding.mainMyRewardsButton.setVisibility(View.GONE);
                    binding.mainLogoutButton.setVisibility(View.VISIBLE);
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.mini_league:
                    binding.mainMyRewardsButton.setVisibility(View.GONE);
                    binding.mainLogoutButton.setVisibility(View.VISIBLE);
                    replaceFragment(new MiniLeagueFragment());
                    break;
                case R.id.redeem:
                    binding.mainMyRewardsButton.setVisibility(View.VISIBLE);
                    binding.mainLogoutButton.setVisibility(View.GONE);
                    replaceFragment(new RedeemFragment());
                    break;
            }
            return true;
        });

        binding.mainLogoutButton.setOnClickListener(item -> {
            AppPreferences preferences = new AppPreferences(mContext);
            String storedToken = preferences.getRefreshToken();
            RefreshData token = new RefreshData(storedToken);
            requestLogout(token);
        });

        binding.mainMyRewardsButton.setOnClickListener(item -> {
            replaceFragment(new MyRewardFragment());
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }

    protected Void requestLogout(RefreshData token){
        mApiService.executeLogout(token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200){
                    LoginActivity.currentUser = null;
                    AppPreferences preferences = new AppPreferences(mContext);
                    preferences.removeRefreshToken();
                    Intent move = new Intent(MainActivity.this, LandingActivity.class);
                    startActivity(move);
                    finish();
                }else if (response.code() == 400){
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_LONG).show();
            }
        });
        return null;
    }
}