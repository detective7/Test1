package com.example.adminys.phonelinstener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvBattery  = (TextView)findViewById(R.id.toBattery);
        tvBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBattery = new Intent(MainActivity.this,TestDynamicBroadcastRecevice.class);
                startActivity(intentBattery);
            }
        });
    }
}
