package com.jie.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jie.weather.R;

import com.jie.weather.utils.CallBack;
import com.jie.weather.utils.JsonToLives;
import com.jie.weather.utils.LivesAdapter;
import com.jie.weather.utils.WeatherHelper;
import com.jie.weather.weather.Lives;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private String outSideTest = "";
    private LivesAdapter livesAdapter;
    private ListView listView;
    private List<Lives> livesList = new ArrayList<Lives>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) this.findViewById(R.id.home_list);

        this.initView();
        Intent intent = getIntent();
        String cityCode = intent.getStringExtra("cityCode");
        String cityName = intent.getStringExtra("cityName");
        this.addWatchCity(cityCode, cityName);



    }

    /**
     * @author panilsy@icloud.com
     * @description 初始化视图
     */
    private void initView() {


        SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);
        String list = pref.getString("userWatched", "");


        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DistrictActivity.class);
                startActivity(intent);
            }
        });

        Button manageButton = findViewById(R.id.button_manage);
        manageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManageCityActivity.class);
                startActivity(intent);
            }
        });

        Button refreshButton = findViewById(R.id.button_refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleRefresh();
            }
        });



        if (!list.equals("")) {
            String[] cityCodes = list.split(",");

            for (String code : cityCodes) {
                this.initWeather(code);
            }

        }
    }

    /**
     * @author panilsy@icloud.com
     * @description 处理刷新事件
     */

    private void handleRefresh() {
        livesList.clear();
        SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);
        String list = pref.getString("userWatched", "");

        if (!list.equals("")) {
            String[] cityCodes = list.split(",");

            for (String code : cityCodes) {
                refreshWeather(code);
            }

        }
    }

    private void addWatchCity(String cityCode, String cityName) {
        if (cityCode!=null && cityName!=null){
            SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);
            String oldList = pref.getString("userWatched","");


            // 先解构，判断是否存在这个城市了
            if (!oldList.equals("")) {
                String[] temp = oldList.split(",");

                for(String k : temp) {
                    if (k.equals(cityCode)) {
                        // 存在了
                        Toast.makeText(MainActivity.this,"您已经添加过这个城市了！", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

            }

            if (oldList.equals("")) {
                oldList += cityCode;
            } else {
                oldList = cityCode + "," + oldList;
            }

            SharedPreferences.Editor editor = pref.edit();
            editor.putString("userWatched", oldList);
            editor.putString(cityCode, cityName);
            editor.commit();

            this.handleRefresh();

            Log.d(TAG, pref.getString("userWatched", ""));

        }

    }

    /**
     * @author panilsy@icloud.com
     * @description 初始化天气预报
     * @param cityCode
     */
    private void initWeather(final String cityCode) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                WeatherHelper weatherHelper = new WeatherHelper(cityCode, new CallBack() {
                    @Override
                    public void run (String s) {
                        getNewWeather(s);
                    }
                });
                weatherHelper.setExtensions("base");
                weatherHelper.getWeatherWithCache(getPreferences(Context.MODE_PRIVATE));

            }
        }).start();
    }

    /**
     * @author panilsy@icloud.com
     * @description 刷新天气的线程函数
     * @param cityCode
     */

    private void refreshWeather(final String cityCode) {
        this.livesList.clear();
        new Thread(new Runnable(){
            @Override
            public void run() {
                WeatherHelper weatherHelper = new WeatherHelper(cityCode, new CallBack() {
                    @Override
                    public void run (String s) {
                        getNewWeather(s);
                    }
                });
                weatherHelper.setExtensions("base");
                weatherHelper.getWeatherNotCache(getPreferences(Context.MODE_PRIVATE));

            }
        }).start();
        Toast.makeText(MainActivity.this,"刷新成功！", Toast.LENGTH_LONG).show();

    }

    /**
     * @author panilsy@icloud.com
     * @description 获取初始化天气的函数
     * @param json
     */

    private void getNewWeather(final String json) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JsonToLives jtl = new JsonToLives(json);
                Lives live = jtl.getLive();
                handleAdd(live);
                livesAdapter = new LivesAdapter(MainActivity.this, R.layout.item, livesList);
                listView.setAdapter(livesAdapter);
            }
        });

//        JsonToCast jtc = new JsonToCast(json);
//        Forecasts forecasts = jtc.getCast();
    }

    private void handleAdd(Lives lives) {
        boolean flag = true;

        for (Lives item: this.livesList) {
            if (item.getAdcode().equals(lives.getAdcode())) {
                flag = false;
            }
        }

        if (flag) {
            this.livesList.add(lives);
        }
    }
}
