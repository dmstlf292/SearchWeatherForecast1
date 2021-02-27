package com.info.searchweather.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.info.searchweather.R;
import com.info.searchweather.model.CurrentWeather;

public class CurrentWeatherFragment extends Fragment {

    private TextView mSunrise, mSunset, mWindSpeed, mWindDeg, mWeather, mTemp, mPressure, mHumidity, mVisibility;
    private LinearLayout mWeatherContainer;
    private TextView mNotifyTextView;
    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        mSunrise = (TextView) view.findViewById(R.id.sunrise_tv);
        mSunset = (TextView) view.findViewById(R.id.sunset_tv);
        mWindSpeed = (TextView) view.findViewById(R.id.windspeed_tv);
        mWindDeg = (TextView) view.findViewById(R.id.winddeg_tv);
        mWeather = (TextView) view.findViewById(R.id.description_tv);
        mTemp = (TextView) view.findViewById(R.id.temp_tv);
        mPressure = (TextView) view.findViewById(R.id.pressure_tv);
        mHumidity = (TextView) view.findViewById(R.id.humidity_tv);
        mVisibility = (TextView) view.findViewById(R.id.visibility_tv);
        mWeatherContainer = (LinearLayout) view.findViewById(R.id.weather_container);
        mNotifyTextView = (TextView) view.findViewById(R.id.no_data_tv);
    }

    public void receivedData(final CurrentWeather weather) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mNotifyTextView.setVisibility(View.GONE);
                mWeatherContainer.setVisibility(View.VISIBLE);
                mSunrise.setText(weather.getSys().getSunrise());
                mSunset.setText(weather.getSys().getSunset());
                mWindSpeed.setText(weather.getWind().getSpeed());
                mWindDeg.setText(weather.getWind().getDeg());
                mWeather.setText(weather.getWeather()[0].getMain());
                mTemp.setText(weather.getMain().getTemp());
                mPressure.setText(weather.getMain().getPressure());
                mHumidity.setText(weather.getMain().getHumidity());
                mVisibility.setText(weather.getVisibility());
            }
        });

    }
}
