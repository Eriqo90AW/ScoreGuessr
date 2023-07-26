package com.eriqoariefjsleeprj.frontend.misc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.eriqoariefjsleeprj.frontend.LoginActivity;
import com.eriqoariefjsleeprj.frontend.R;
import com.eriqoariefjsleeprj.frontend.RedeemFragment;
import com.eriqoariefjsleeprj.frontend.databinding.FragmentRedeemBinding;
import com.eriqoariefjsleeprj.frontend.model.Reward;
import com.eriqoariefjsleeprj.frontend.request.BaseApiService;
import com.eriqoariefjsleeprj.frontend.request.UtilsApi;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardsListAdapter extends ArrayAdapter<Reward>  {
    BaseApiService mApiService;
    Context mContext;

    public RewardsListAdapter(Context context, ArrayList<Reward> rewardArrayList){
        super(context, R.layout.list_view_rewards, rewardArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        mApiService = UtilsApi.getApiService();
        mContext = getContext();
        Reward reward = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_rewards,parent,false);
        }
        ImageView image = convertView.findViewById(R.id.rewards_image);
        TextView name = convertView.findViewById(R.id.rewards_name);
        TextView type = convertView.findViewById(R.id.rewards_type);
        TextView cost = convertView.findViewById(R.id.rewards_cost);
        Button redeem = convertView.findViewById(R.id.rewards_redeem_button);

        Picasso.get().load(reward.image).into(image);

        name.setText(reward.name);
        type.setText(reward.type);
        cost.setText(String.valueOf(reward.cost));

        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_redeem_reward,parent,false);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialog);
                AlertDialog alertDialog = builder.setView(dialogView).create();

                dialogView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.dialog_bg));

                ImageView dialogImage = dialogView.findViewById(R.id.dialog_redeem_image);
                TextView dialogName = dialogView.findViewById(R.id.dialog_redeem_name);
                TextView dialogType = dialogView.findViewById(R.id.dialog_redeem_type);
                TextView dialogPoints = dialogView.findViewById(R.id.dialog_redeem_points);
                TextView dialogCost = dialogView.findViewById(R.id.dialog_redeem_cost);
                TextView dialogUserPoints = dialogView.findViewById(R.id.dialog_redeem_user_points);
                Button dialogRedeem = dialogView.findViewById(R.id.dialog_redeem_redeem_button);
                Button dialogCancel = dialogView.findViewById(R.id.dialog_redeem_cancel_button);

                Picasso.get().load(reward.image).into(dialogImage);

                dialogName.setText(reward.name);
                dialogType.setText(reward.type);
                dialogPoints.setText(String.valueOf(reward.cost));
                dialogCost.setText(String.valueOf(reward.cost));
                dialogUserPoints.setText(String.valueOf(LoginActivity.currentUser.getTotal_points()));

                alertDialog.show();
                dialogRedeem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (LoginActivity.currentUser != null){
                            HashMap<String, Float> data = new HashMap<>();
                            Float newPoints = LoginActivity.currentUser.getTotal_points() - reward.cost;
                            data.put("total_points", newPoints);
                            if(newPoints > 0){
                                requestUpdatePoints(LoginActivity.currentUser.getId(), data, reward.id, parent, newPoints);
                            }else{
                                Toast.makeText(mContext, "Not enough points", Toast.LENGTH_SHORT).show();
                            }
                            alertDialog.dismiss();
                        }
                    }
                });

                dialogCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

            }
        });

        return convertView;

    }

    protected Void requestUpdateRewards(int userId, HashMap<String, Integer> rewards_id, @NonNull ViewGroup parent, Float newPoints){
        mApiService.updateUserRewards(userId, rewards_id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    Toast.makeText(mContext, "Reward Successfully Redeemed", Toast.LENGTH_SHORT).show();
                    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                    FragmentRedeemBinding binding = FragmentRedeemBinding.inflate(inflater, parent, false);
                    binding.rewardsPointsBalance.setText(String.valueOf(newPoints));
                } else if (response.code() == 400) {
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    protected Void requestUpdatePoints(int userId, HashMap<String, Float> total_points, Integer rewards_id, @NonNull ViewGroup parent, Float newPoints){
        mApiService.updateUserPoints(userId, total_points).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200) {
                    LoginActivity.currentUser.setTotal_points(newPoints);
                    HashMap<String, Integer> data = new HashMap<>();
                    data.put("rewards_id", rewards_id);
                    requestUpdateRewards(userId, data, parent, newPoints);
                } else if (response.code() == 400) {
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

}

