package com.example.ys.mycustomview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TopBar topbar;
    CustomPart part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topbar = (TopBar)this.findViewById(R.id.topbar);
        topbar.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this, "左边按钮", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,ToolBarActivity.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this, "右边按钮", Toast.LENGTH_SHORT).show();
            }
        });

        part = (CustomPart)this.findViewById(R.id.customPart);
        part.setSweepValue(90);
    }
}
