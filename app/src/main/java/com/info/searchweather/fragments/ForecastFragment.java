package com.info.searchweather.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.info.searchweather.R;
import com.info.searchweather.adapters.ExpAdapter;
import com.info.searchweather.model.Forecast;

import java.util.ArrayList;

public class ForecastFragment extends Fragment {

    private ExpandableListView mListView;
    private ExpAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        mListView = (ExpandableListView) view.findViewById(R.id.expand_list);

        return view;
    }

    public void receivedDatas(ArrayList<String> groupData, ArrayList<ArrayList<Forecast.List>> childData) {

        if(mAdapter == null) {
            mAdapter = new ExpAdapter(groupData, childData, getContext());
            mListView.post(new Runnable() {
                @Override
                public void run() {
                    mListView.setAdapter(mAdapter);
                }
            });
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


}