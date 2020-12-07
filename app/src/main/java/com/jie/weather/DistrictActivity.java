package com.jie.weather;

import android.content.Intent;
import android.os.Bundle;

import com.jie.weather.R;

import com.jie.weather.Location.District;
import com.jie.weather.Location.DistrictFather;
import com.jie.weather.utils.CallBack;
import com.jie.weather.utils.DistrictHelper;
import com.jie.weather.utils.JsonToDistrict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DistrictActivity extends AppCompatActivity {
    private static final String TAG = "DistrictActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        this.initView();
    }

    /**
     * @author panilsy@icloud.com
     * @description 初始化视图
     */
    private void initView() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                DistrictHelper districtHelper = new DistrictHelper(new CallBack() {
                    @Override
                    public void run (String s) {
                        getNewDistrict(s);
                    }
                });
                districtHelper.getFirstDistrict();
            }
        }).start();
    }

    /**
     * @author panilsy@icloud.com
     * @description 获取区
     * @param s
     */
    private void getNewDistrict(final String s) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                JsonToDistrict jtd = new JsonToDistrict(s);
                final DistrictFather districts = jtd.getDistricts();

                String[] data = new String[districts.getLength()];
                int i = 0;
                for (District item : districts.getDistricts()) {
                    data[i] = item.getName();
                    i++;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DistrictActivity.this, android.R.layout.simple_list_item_1,data);
                ListView lv = (ListView) findViewById(R.id.list_view1);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        District d = districts.getDistricts().get(i);
                        Log.d(TAG, d.getName());
                        handleClickDistrictFirst(d.getName());
                    }
                });

            }
        });


    }

    /**
     * @author panilsy@icloud.com
     * @description 处理点击事件
     * @param cityName
     */
    private void handleClickDistrictFirst(String cityName) {
        Intent intent = new Intent(DistrictActivity.this, SecondDistrictActivity.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
        finish();
    }


}
