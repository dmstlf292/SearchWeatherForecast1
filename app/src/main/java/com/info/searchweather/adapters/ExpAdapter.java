package com.info.searchweather.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.info.searchweather.R;
import com.info.searchweather.model.Forecast;

import java.util.ArrayList;

public class ExpAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> mGroupData;
    private ArrayList<ArrayList<Forecast.List>> mChildData;
    private LayoutInflater mInflater;

    public ExpAdapter(ArrayList<String> mGroupData, ArrayList<ArrayList<Forecast.List>> mChildData, Context context) {
        this.mGroupData = mGroupData;
        this.mChildData = mChildData;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mGroupData.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupData.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_group, parent, false);
            viewHolder.groupTv = (TextView) convertView.findViewById(R.id.group_tv);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String data = (String) getGroup(groupPosition);
        viewHolder.groupTv.setText(data);


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildData.get(groupPosition).size();

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChildData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_child, parent, false);
            viewHolder.childHumidityTv = (TextView) convertView.findViewById(R.id.child_humidity_tv);
            viewHolder.childPressureTv = (TextView) convertView.findViewById(R.id.child_pressure_tv);
            viewHolder.childTempTv = (TextView) convertView.findViewById(R.id.child_temp_tv);
            viewHolder.childWeatherTv = (TextView) convertView.findViewById(R.id.child_weather_tv);
            viewHolder.childWindDegTv = (TextView) convertView.findViewById(R.id.child_winddeg_tv);
            viewHolder.childWindSpeedTv = (TextView) convertView.findViewById(R.id.child_windspeed_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Forecast.List data = (Forecast.List) getChild(groupPosition, childPosition);

        viewHolder.childHumidityTv.setText(data.getMain().getHumidity());
        viewHolder.childPressureTv.setText(data.getMain().getPressure());
        viewHolder.childTempTv.setText(data.getMain().getTemp());
        viewHolder.childWeatherTv.setText(data.getWeather()[0].getMain());
        viewHolder.childWindDegTv.setText(data.getWind().getDeg());
        viewHolder.childWindSpeedTv.setText(data.getWind().getSpeed());



        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolder {
        TextView groupTv;
        TextView childWeatherTv;
        TextView childTempTv;
        TextView childWindSpeedTv;
        TextView childWindDegTv;
        TextView childPressureTv;
        TextView childHumidityTv;
    }
}
