package com.info.searchweather.activity;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.tabs.TabLayout;
import com.info.searchweather.R;
import com.info.searchweather.adapters.PagerAdapter;
import com.info.searchweather.fragments.CurrentWeatherFragment;
import com.info.searchweather.fragments.ForecastFragment;
import com.info.searchweather.fragments.SearchFragment;
import com.info.searchweather.listener.SearchFragmentToActivityListener;
import com.info.searchweather.model.CurrentWeather;
import com.info.searchweather.model.Forecast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements SearchFragmentToActivityListener {

    private final String CURRENT_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=Busan&appid=c4cd4dc6954bff34ad45980400632fb2";
    private final String WEATHER_FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q=Busan&mode=json&appid=c4cd4dc6954bff34ad45980400632fb2";

    private SearchFragment mSearchFragment;
    private CurrentWeatherFragment mCurrentWeatherFragment;
    private ForecastFragment mForecastFragment;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private PagerAdapter mAdapter;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProgressDialog();
        initView();
    }

    private void initProgressDialog() {
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("날씨정보");
        mProgressDialog.setMessage("데이터를 불러오고 있습니다....");
    }

    private void initView() {
        mSearchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.search_fragment);
        mSearchFragment.setListener(this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);

        ArrayList<Fragment> list = new ArrayList<>();
        mCurrentWeatherFragment = new CurrentWeatherFragment();
        mForecastFragment = new ForecastFragment();
        list.add(mCurrentWeatherFragment);
        list.add(mForecastFragment);

        mAdapter = new PagerAdapter(getSupportFragmentManager(), list);

        mViewPager.setAdapter(mAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText("현재 날씨"));
        mTabLayout.addTab(mTabLayout.newTab().setText("날씨 예보"));

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }

    private String getStringFromUrl(String urlString) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlString)
                .build();


        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private CurrentWeather parsing(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, CurrentWeather.class);
    }

    private Forecast parsing2(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Forecast.class);
    }

    private void showProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void receivedDataFromSearchFragment(String keyword) {
        showProgressDialog();

        final String currentWeatherInfoUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + keyword + "&appid=c4cd4dc6954bff34ad45980400632fb2&units=metric";
        final String forecastUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + keyword + "&mode=json&appid=c4cd4dc6954bff34ad45980400632fb2&units=metric";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonString = getStringFromUrl(currentWeatherInfoUrl);
                    CurrentWeather current = parsing(jsonString);
                    mCurrentWeatherFragment.receivedData(current);

                    String jsonString2 = getStringFromUrl(forecastUrl);
                    Forecast forecast = parsing2(jsonString2);
                    mForecastFragment.receivedDatas(forecast.getGroupData(), forecast.getChildData());

                    runOnUiThread(new Runnable() {//여기서 UI 변경 코드 넣기@@@
                        @Override
                        public void run() {
                            dismissProgressDialog();

                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}