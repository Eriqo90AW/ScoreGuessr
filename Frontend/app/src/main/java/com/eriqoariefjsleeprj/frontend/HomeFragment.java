package com.eriqoariefjsleeprj.frontend;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eriqoariefjsleeprj.frontend.databinding.FragmentHomeBinding;
import com.eriqoariefjsleeprj.frontend.misc.UpcomingListAdapter;
import com.eriqoariefjsleeprj.frontend.model.Fixture;
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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Retrofit retrofit;
    private BaseApiService retrofitInterface;
    private FragmentHomeBinding binding;
    private Context context;
    BaseApiService mApiService;
    Context mContext;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
//        context = getContext();
        mApiService = UtilsApi.getApiService();
        mContext = getContext();

        if (mContext == null){
            mContext = getActivity();
        }

        requestFixtures();

        return rootView;
    }

    protected ArrayList<Fixture> requestFixtures(){
        mApiService.getFixtures().enqueue(new Callback<ArrayList<Fixture>>() {
            @Override
            public void onResponse(Call<ArrayList<Fixture>> call, Response<ArrayList<Fixture>> response) {
                if(response.code() == 200){
                    ArrayList<Fixture> fixtures = response.body();
                    if(fixtures != null && !fixtures.isEmpty()){
                        UpcomingListAdapter upcomingListAdapter = new UpcomingListAdapter(mContext, fixtures);
                        binding.listUpcoming.setAdapter(upcomingListAdapter);
                        binding.listUpcoming.setClickable(true);
//                        binding.listUpcoming.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                                if (context != null) {
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                                    builder.setTitle(fixtures.get(position).home_id);
//                                    builder.setMessage(fixtures.get(position).away_id);
//                                    builder.show();
//                                } else {
//                                    Toast.makeText(context, "Failed to create dialog", Toast.LENGTH_LONG).show();
//                                }
//
//                            }
//                        });
                    }else{
                        Toast.makeText(mContext, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                }else if (response.code() == 400){
                    Toast.makeText(mContext, "Failed to reach server", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Fixture>> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}