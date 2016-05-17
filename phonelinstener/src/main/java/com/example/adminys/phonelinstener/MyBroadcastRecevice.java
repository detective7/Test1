package com.example.adminys.phonelinstener;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Administrator on 2015/12/21 0021.
 * 虚拟机无效，真机测试可以
 */
public class MyBroadcastRecevice extends BroadcastReceiver {
    private static final String CALLACTION = Intent.ACTION_NEW_OUTGOING_CALL;
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(CALLACTION)){
            //调出拨号界面
            String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Log.i("broadcast11","有人在打电话"+phoneNumber);
        }else{
            //电话呼入
            Log.i("broadcast11","有电话呼入");
            TelephonyManager telephonyManager=(TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    PhoneStateListener phoneStateListener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch(state){
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.i("broadcast11","挂断");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.i("broadcast11","接听");
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.i("broadcast11","有电话呼入，来电号码："+incomingNumber);
                    break;
            }
        }
    };
}
