package com.jie.weather;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jie.weather.R;

import com.jie.weather.Location.District;
import com.jie.weather.Location.DistrictFather;
import com.jie.weather.utils.CallBack;
import com.jie.weather.utils.DistrictHelper;
import com.jie.weather.utils.JsonToDistrict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondDistrictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_district);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = getIntent();
        this.initView(intent.getStringExtra("cityName"));
    }

    /**
     * @author panilsy@icloud.com
     * @description 初始化视图
     * @param cityName
     */
    private void initView(final String cityName) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                DistrictHelper districtHelper = new DistrictHelper(new CallBack() {
                    @Override
                    public void run (String s) {
                        getNewDistrict(s);
                    }
                });
                districtHelper.getSecondDistrict(cityName);
            }
        }).start();
    }

    /**
     * @author panilsy@icloud.com
     * @description 获取省份
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondDistrictActivity.this, android.R.layout.simple_list_item_1,data);
                ListView lv = (ListView) findViewById(R.id.list_view2);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(SecondDistrictActivity.this, MainActivity.class);
                        intent.putExtra("cityCode", districts.getDistricts().get(i).getAdcode());
                        intent.putExtra("cityName", districts.getDistricts().get(i).getName());
                        startActivity(intent);
                        finish();
                    }
                });

            }
        });

    }

}
