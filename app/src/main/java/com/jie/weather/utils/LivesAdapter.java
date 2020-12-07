package com.jie.weather.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jie.weather.R;
import com.jie.weather.weather.Lives;

import java.util.List;

/**
 * @author panilsy@icloud.com
 * @description 列表的adapter
 */
public class LivesAdapter extends ArrayAdapter {
    private int resourceId;
    public LivesAdapter(Context context, int resource, List<Lives> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Lives lives = (Lives) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView province = view.findViewById(R.id.home_list_province);
        TextView city = view.findViewById(R.id.home_list_city);
        TextView weather = view.findViewById(R.id.home_list_weather);
        TextView temperature = view.findViewById(R.id.home_list_temperature);
        TextView winddirection = view.findViewById(R.id.home_list_winddirection);
        TextView humidty = view.findViewById(R.id.home_list_humidity);
        TextView windpower = view.findViewById(R.id.home_list_windpower);
        TextView report = view.findViewById(R.id.home_list_reporttime);

        province.setText(lives.getProvince());
        city.setText(lives.getCity());
        weather.setText("天气：" + lives.getWeather());
        temperature.setText("温度：" + lives.getTemperature());
        winddirection.setText("风向：" + lives.getWinddirection());
        humidty.setText("湿度：" + lives.getHumidity());
        windpower.setText("风速：" + lives.getWindpower());
        report.setText("上次更新时间：" + lives.getReporttime());
        return view;
    }
}
