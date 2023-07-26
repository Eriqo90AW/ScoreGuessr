package com.eriqoariefjsleeprj.frontend;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eriqoariefjsleeprj.frontend.databinding.FragmentMiniLeagueBinding;
import com.eriqoariefjsleeprj.frontend.databinding.FragmentRedeemBinding;
import com.eriqoariefjsleeprj.frontend.misc.MyRewardsListAdapter;
import com.eriqoariefjsleeprj.frontend.misc.RewardsListAdapter;
import com.eriqoariefjsleeprj.frontend.misc.StandingsListAdapter;
import com.eriqoariefjsleeprj.frontend.model.Message;
import com.eriqoariefjsleeprj.frontend.model.MiniLeague;
import com.eriqoariefjsleeprj.frontend.model.MiniLeagueInfo;
import com.eriqoariefjsleeprj.frontend.model.MyReward;
import com.eriqoariefjsleeprj.frontend.model.Reward;
import com.eriqoariefjsleeprj.frontend.request.BaseApiService;
import com.eriqoariefjsleeprj.frontend.request.UtilsApi;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiniLeagueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiniLeagueFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    BaseApiService mApiService;
    Context mContext;
    private FragmentMiniLeagueBinding binding;

    public MiniLeagueFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MiniLeagueFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MiniLeagueFragment newInstance(String param1, String param2) {
        MiniLeagueFragment fragment = new MiniLeagueFragment();
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
        binding = FragmentMiniLeagueBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        mApiService = UtilsApi.getApiService();
        mContext = getContext();

        if (mContext == null){
            mContext = getActivity();
        }

        checkIsJoined();

        binding.miniLeagueJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = "V8JI0S";
                int id = LoginActivity.currentUser.getId();

                HashMap<String, String> data = new HashMap<>();
                data.put("mini_league_code", code);

                HashMap<String, String> map = new HashMap<>();
                map.put("mini_league_code", code);
                map.put("user_id", String.valueOf(id));

                requestAddUser(map);
            }
        });

        return rootView;
    }

    protected Message requestUpdateMiniLeague(int userId, HashMap<String , String> mini_league_code){
        mApiService.updateUserMiniLeague(userId, mini_league_code).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.code() == 200) {
                    Message result = response.body();
                    if(result.message.equals("Failed to update user mini league")) {
                        Toast.makeText(mContext, "Failed to update data", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "Mini League Joined", Toast.LENGTH_SHORT).show();
                        LoginActivity.currentUser.setMini_league_code(mini_league_code.get("mini_league_code"));
                        checkIsJoined();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });
        return  null;
    }

    protected ArrayList<MiniLeagueInfo> requestMiniLeagueInfo(String code){
        mApiService.getMiniLeagueUsers(code).enqueue(new Callback<ArrayList<MiniLeagueInfo>>() {
            @Override
            public void onResponse(Call<ArrayList<MiniLeagueInfo>> call, Response<ArrayList<MiniLeagueInfo>> response) {
                if (response.code() == 200) {
                    ArrayList<MiniLeagueInfo> result = response.body();
                    if(result != null && !result.isEmpty()) {
                        StandingsListAdapter standingsListAdapter = new StandingsListAdapter(mContext, result);
                        binding.listUsers.setAdapter(standingsListAdapter);
                        binding.listUsers.setClickable(true);
                    }else{
                        Toast.makeText(mContext, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MiniLeagueInfo>> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    protected MiniLeague requestMiniLeague(String code){
        mApiService.getMiniLeagueInfo(code).enqueue(new Callback<MiniLeague>() {
            @Override
            public void onResponse(Call<MiniLeague> call, Response<MiniLeague> response) {
                if (response.code() == 200) {
                    MiniLeague result = response.body();
                    if(result != null) {
                        binding.miniLeagueInfoName.setText(result.mini_league_name);
                        binding.miniLeagueInfoCode.setText(result.mini_league_code);
                        binding.miniLeagueInfoTotal.setText(result.total_users);
                    }else{
                        Toast.makeText(mContext, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MiniLeague> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    protected Message requestAddUser(HashMap<String, String> data){
        mApiService.adduserToMiniLeague(data).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.code() == 200) {
                    Message result = response.body();
                    if(result.message.equals("Mini league user added")) {
                        String code = "V8JI0S";
                        int id = LoginActivity.currentUser.getId();
                        HashMap<String, String> data = new HashMap<>();
                        data.put("mini_league_code", code);
                        requestUpdateMiniLeague(id, data);
                    }else{
                        Toast.makeText(mContext, "Failed to add user", Toast.LENGTH_SHORT).show();
                    }
                } else if (response.code() == 400) {
                    Toast.makeText(mContext, "Bad Request", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }

    private void toggleVisibility(boolean on){
        if(on){
            binding.miniLeagueInformation.setVisibility(View.GONE);
            binding.miniLeagueJoinButton.setVisibility(View.GONE);

            binding.miniLeagueTitle.setVisibility(View.VISIBLE);
            binding.miniLeagueInfo.setVisibility(View.VISIBLE);
            binding.miniLeagueInfoHeader.setVisibility(View.VISIBLE);
            binding.listUsers.setVisibility(View.VISIBLE);
        }else{
            binding.miniLeagueInformation.setVisibility(View.VISIBLE);
            binding.miniLeagueJoinButton.setVisibility(View.VISIBLE);

            binding.miniLeagueTitle.setVisibility(View.GONE);
            binding.miniLeagueInfo.setVisibility(View.GONE);
            binding.miniLeagueInfoHeader.setVisibility(View.GONE);
            binding.listUsers.setVisibility(View.GONE);
        }

    }

    private void checkIsJoined(){
        String code = LoginActivity.currentUser.getMini_league_code();
        if (code != null){
            toggleVisibility(true);
            requestMiniLeague(code);
            requestMiniLeagueInfo(code);
        }else {
            toggleVisibility(false);
        }
    }
}