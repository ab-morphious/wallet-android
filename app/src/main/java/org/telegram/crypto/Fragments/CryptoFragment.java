package org.telegram.crypto.Fragments;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.telegram.crypto.Adapters.CryptoRecyclerAdapter;
import org.telegram.crypto.Models.Crypto;
import org.telegram.crypto.Models.Data;
import org.telegram.crypto.Models.ResponseStatus;
import org.telegram.crypto.Retrofit.APIClient;
import org.telegram.crypto.Retrofit.APIInterface;
import org.telegram.crypto.Utils.APIUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoFragment extends Fragment {

    APIInterface apiInterface;
    CryptoRecyclerAdapter cryptoRecyclerAdapter;
    RecyclerView recyclerView;
    List<Data> data = new ArrayList<>();

    public CryptoFragment() {
        // Required empty public constructor
    }

    public static CryptoFragment newInstance() {
        CryptoFragment fragment = new CryptoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        fetchCryptoCurrencies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Create params for views
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 0, 0, 0);

        //Scrollable container
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getActivity());

        //Recyclerview for crypto list
        recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutParams(params);

        //Adapter for recyclerview
        cryptoRecyclerAdapter = new CryptoRecyclerAdapter(data, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cryptoRecyclerAdapter);
        horizontalScrollView.addView(recyclerView);

        return horizontalScrollView;
    }

    private void fetchCryptoCurrencies() {
        Call<Crypto> cryptoListCall = apiInterface.getCryptoList(1, 10, "USD", APIUtil.apiKey);
        cryptoListCall.enqueue(new Callback<Crypto>() {
            @Override
            public void onResponse(Call<Crypto> call, Response<Crypto> response) {

                if(response.isSuccessful())
                {
                    Crypto crypto = response.body();
                    ResponseStatus status = crypto.status;
                    if(status.errorCode == 0) {
                        data = crypto.data;
                        cryptoRecyclerAdapter = new CryptoRecyclerAdapter(data, getActivity());
                        recyclerView.setAdapter(cryptoRecyclerAdapter);
                        cryptoRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Crypto> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}