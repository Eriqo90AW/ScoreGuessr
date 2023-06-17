package com.eriqoariefjsleeprj.frontend.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eriqoariefjsleeprj.frontend.R;
import com.eriqoariefjsleeprj.frontend.model.Fixture;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class UpcomingListAdapter extends ArrayAdapter<Fixture> {
    public UpcomingListAdapter(Context context, ArrayList<Fixture> fixtureArrayList){
        super(context, R.layout.list_view_upcoming, fixtureArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Fixture fixture = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_upcoming,parent,false);
        }
        ImageView homeBadge = convertView.findViewById(R.id.upcoming_homeClubBadge);
        TextView homeName = convertView.findViewById(R.id.upcoming_homeClubName);
        TextView homeOdds = convertView.findViewById(R.id.upcoming_homeOdds);

        ImageView awayBadge = convertView.findViewById(R.id.upcoming_awayClubBadge);
        TextView awayName = convertView.findViewById(R.id.upcoming_awayClubName);
        TextView awayOdds = convertView.findViewById(R.id.upcoming_awayOdds);

        TextView fixtureDate = convertView.findViewById(R.id.upcoming_fixtureDate);

        Picasso.get().load(fixture.home_crest).into(homeBadge);
        Picasso.get().load(fixture.away_crest).into(awayBadge);

        homeName.setText(fixture.home_team);
        homeOdds.setText(String.valueOf(fixture.home_odds));

        awayName.setText(fixture.away_team);
        awayOdds.setText(String.valueOf(fixture.away_odds));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(fixture.date);
        fixtureDate.setText(formattedDate);


        return convertView;

    }

}
