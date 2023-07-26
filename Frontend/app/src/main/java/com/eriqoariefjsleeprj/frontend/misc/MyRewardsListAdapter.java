package com.eriqoariefjsleeprj.frontend.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eriqoariefjsleeprj.frontend.R;
import com.eriqoariefjsleeprj.frontend.model.MyReward;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRewardsListAdapter extends ArrayAdapter<MyReward> {
    public MyRewardsListAdapter(Context context, ArrayList<MyReward> myRewardArrayList){
        super(context, R.layout.list_view_my_reward, myRewardArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        MyReward reward = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_my_reward,parent,false);
        }
        ImageView image = convertView.findViewById(R.id.my_reward_image);
        TextView name = convertView.findViewById(R.id.my_reward_name);
        TextView type = convertView.findViewById(R.id.my_reward_type);
        TextView code = convertView.findViewById(R.id.my_reward_code);

        Picasso.get().load(reward.image).into(image);

        name.setText(reward.name);
        type.setText(reward.type);
        code.setText(reward.code);

        return convertView;

    }
}
