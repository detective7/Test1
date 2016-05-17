package com.example.adminys.phonelinstener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestDynamicBroadcastRecevice extends Activity {

    private TextView tvBetteryInfo;

    private static final String BETTERY_ACTION = Intent.ACTION_BATTERY_CHANGED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dynamic_broadcast_recevice);
        tvBetteryInfo = (TextView)findViewById(R.id.battery);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BETTERY_ACTION);
        registerReceiver(mBatteryBR,intentFilter);
    }
 
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBatteryBR);
    }

    private BroadcastReceiver mBatteryBR = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BETTERY_ACTION)) {
                //第二个参数是获取不到时，的默认值
                int status = intent.getIntExtra("status",0);//电池状态
                int health = intent.getIntExtra("health",1);//健康状态
                boolean present = intent.getBooleanExtra("present",false);//是否使用电池
                int level = intent.getIntExtra("level",0);//电池剩余容量
                int scale = intent.getIntExtra("scale",0);//电池最大值
                int plugged = intent.getIntExtra("plugged",0);//充电方式
                int voltage = intent.getIntExtra("vpltage",0);//电池的电压
                int temperature = intent.getIntExtra("temperature",0);//电池的温度，单位：0.1度
                String technology = intent.getStringExtra("technology");//电池类型

                String statusString = "未知状态";
                switch (status){
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
                        statusString = "电池状态未知";
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
                        statusString = "充电状态";
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
                        statusString= "放电状态";
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                        statusString = "未充电";
                        break;
                    case BatteryManager.BATTERY_STATUS_FULL:
                        statusString="电量已满";
                        break;
                }

                String healthString = "未知状态";
                switch (health){
                    case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                        healthString = "电池健康状态未知";
                        break;
                    case BatteryManager.BATTERY_HEALTH_GOOD:
                        healthString = "状态好";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                        healthString = "电池过热";
                        break;
                    case BatteryManager.BATTERY_HEALTH_DEAD:
                        healthString = "电池没电";
                        break;
                    case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                        healthString = "电池电压过高";
                        break;
                    case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                        healthString = "未知错误";
                        break;
                }

                String acString = "未知状态";
                switch (plugged){
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        acString = "直流充电";
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        acString = "USB充电";
                        break;
                }
                tvBetteryInfo.setText("电池状态如下：\n"+
                        "是否使用电池："+String.valueOf(present)+
                        "\n电池状态："+statusString+
                        "\n电池电量："+String.valueOf(level)+
                        "\n电池健康状况："+healthString+
                        "\n最大值："+String.valueOf(scale)+
                        "\n充电方式："+acString+
                        "\n电池电压："+String.valueOf(voltage)+
                        "\n电池温度："+String.valueOf(temperature)+
                        "\n电池类型："+technology
                );

            }
        }
    };
}
