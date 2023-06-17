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
import com.eriqoariefjsleeprj.frontend.model.Reward;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RewardsListAdapter extends ArrayAdapter<Reward>  {
    public RewardsListAdapter(Context context, ArrayList<Reward> rewardArrayList){
        super(context, R.layout.list_view_rewards, rewardArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Reward reward = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_rewards,parent,false);
        }
        ImageView image = convertView.findViewById(R.id.rewards_image);
        TextView name = convertView.findViewById(R.id.rewards_name);
        TextView type = convertView.findViewById(R.id.rewards_type);
        TextView cost = convertView.findViewById(R.id.rewards_cost);

        Picasso.get().load(reward.image).into(image);

        name.setText(reward.name);
        type.setText(reward.type);
        cost.setText(String.valueOf(reward.cost));

        return convertView;

    }

}

