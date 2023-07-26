package com.eriqoariefjsleeprj.frontend.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eriqoariefjsleeprj.frontend.R;
import com.eriqoariefjsleeprj.frontend.model.MiniLeagueInfo;

import java.util.ArrayList;

public class StandingsListAdapter extends ArrayAdapter<MiniLeagueInfo> {
    public StandingsListAdapter(Context context, ArrayList<MiniLeagueInfo> miniLeagueInfoArrayList){
        super(context, R.layout.list_view_mini_league_info, miniLeagueInfoArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        MiniLeagueInfo miniLeagueInfo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_mini_league_info,parent,false);
        }

        TextView pos = convertView.findViewById(R.id.mini_users_position);
        TextView username = convertView.findViewById(R.id.mini_users_username);
        TextView match1 = convertView.findViewById(R.id.mini_users_m1);
        TextView match2 = convertView.findViewById(R.id.mini_users_m2);
        TextView match3 = convertView.findViewById(R.id.mini_users_m3);
        TextView points = convertView.findViewById(R.id.mini_users_pts);

        pos.setText(String.valueOf(position+1));
        username.setText(miniLeagueInfo.username);

        if (miniLeagueInfo.m1 == null){
            match1.setText(String.valueOf(0.0));
        }else{
            match1.setText(String.valueOf(miniLeagueInfo.m1));
        }
        if (miniLeagueInfo.m2 == null){
            match2.setText(String.valueOf(0.0));
        }else {
            match2.setText(String.valueOf(miniLeagueInfo.m2));
        }
        if (miniLeagueInfo.m3 == null){
            match3.setText(String.valueOf(0.0));
        }else {
            match3.setText(String.valueOf(miniLeagueInfo.m3));
        }
        points.setText(String.valueOf(miniLeagueInfo.pts));

        return convertView;
    }
}
