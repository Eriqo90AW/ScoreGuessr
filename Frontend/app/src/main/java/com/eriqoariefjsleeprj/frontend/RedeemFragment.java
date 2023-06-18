package com.eriqoariefjsleeprj.frontend;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eriqoariefjsleeprj.frontend.databinding.FragmentHomeBinding;
import com.eriqoariefjsleeprj.frontend.databinding.FragmentRedeemBinding;
import com.eriqoariefjsleeprj.frontend.misc.RewardsListAdapter;
import com.eriqoariefjsleeprj.frontend.misc.UpcomingListAdapter;
import com.eriqoariefjsleeprj.frontend.model.Fixture;
import com.eriqoariefjsleeprj.frontend.model.Reward;
import com.eriqoariefjsleeprj.frontend.request.BaseApiService;
import com.eriqoariefjsleeprj.frontend.request.UtilsApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RedeemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RedeemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BaseApiService mApiService;
    Context mContext;
    private FragmentRedeemBinding binding;
    private Context context;

    public RedeemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RedeemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RedeemFragment newInstance(String param1, String param2) {
        RedeemFragment fragment = new RedeemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRedeemBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        mApiService = UtilsApi.getApiService();
        mContext = getContext();

        if (mContext == null){
            mContext = getActivity();
        }

        //update user's point balance
        binding.rewardsPointsBalance.setText(String.valueOf(LoginActivity.currentUser.getTotal_points()));
//        binding.rewardsPointsBalance.setText(String.valueOf(-1));

        requestRewards();

        return rootView;
    }

    protected ArrayList<Reward> requestRewards(){
        mApiService.getRewards().enqueue(new Callback<ArrayList<Reward>>() {
            @Override
            public void onResponse(Call<ArrayList<Reward>> call, Response<ArrayList<Reward>> response) {
                if (response.code() == 200) {
                    ArrayList<Reward> rewards = response.body();
                    if(rewards != null && !rewards.isEmpty()) {
                        RewardsListAdapter rewardsListAdapter = new RewardsListAdapter(mContext, rewards);
                        binding.listRewards.setAdapter(rewardsListAdapter);
                        binding.listRewards.setClickable(true);
                    }else{
                        Toast.makeText(mContext, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Reward>> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}