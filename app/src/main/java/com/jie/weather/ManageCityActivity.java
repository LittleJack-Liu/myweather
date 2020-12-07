package com.jie.weather;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jie.weather.R;

public class ManageCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_city);

        this.initView();
    }

    /**
     * @author panilsy@icloud.com
     * @description 初始化视图
     */
    private void initView() {
        SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);
        String list = pref.getString("userWatched","");

        if (!list.equals("")){
            String[] cityCodes = list.split(",");
            String[] cityNames = new String[cityCodes.length];
            int i = 0;
            for (String item : cityCodes) {
                cityNames[i] = pref.getString(item, "") + " / " + item;
                i++;
            }


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManageCityActivity.this, android.R.layout.simple_list_item_1,cityNames);
            ListView lv = (ListView) findViewById(R.id.list_view3);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int mk, long l) {
                    makeDailog(mk);
                }
            });
        }


    }

    /**
     * @author panilsy@icloud.com
     * @description 弹出dailog的东西
     * @param mk
     */
    private void makeDailog(final int mk) {
        final SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);
        String list = pref.getString("userWatched","");
        final String[] cityCodes = list.split(",");
        final String[] cityNames = new String[cityCodes.length];
        AlertDialog.Builder dialog = new AlertDialog.Builder(ManageCityActivity.this);
        dialog.setTitle("删除");
        dialog.setMessage("请注意，删除城市后不可恢复，你确定吗？");
        dialog.setCancelable(false);
        dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String cityCodesString = "";
                for (int j=0;j<cityCodes.length;j++){
                    if (j != mk) {
                        if (cityCodesString.equals("")) {
                            cityCodesString = cityCodes[j];
                        } else {
                            cityCodesString += ","  + cityCodes[j];
                        }
                    }
                }
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("userWatched", cityCodesString);
                editor.commit();
                reloadCity();
                Toast.makeText(ManageCityActivity.this,"删除城市成功", Toast.LENGTH_LONG).show();

            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ManageCityActivity.this,"取消删除", Toast.LENGTH_LONG).show();

            }
        });
        dialog.show();
    }

    /**
     * @author panilsy@icloud.com
     * @description 负责删完了刷新的函数
     */
    private void reloadCity() {
        final SharedPreferences pref = getSharedPreferences("data", Context.MODE_PRIVATE);
        String list = pref.getString("userWatched","");
        final String[] cityCodes = list.split(",");
        String[] cityNames = new String[cityCodes.length];
        int i = 0;
        for (String item : cityCodes) {
            cityNames[i] = pref.getString(item, "") + " / " + item;
            i++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ManageCityActivity.this, android.R.layout.simple_list_item_1,cityNames);
        ListView lv = (ListView) findViewById(R.id.list_view3);
        lv.setAdapter(adapter);
    }
}
