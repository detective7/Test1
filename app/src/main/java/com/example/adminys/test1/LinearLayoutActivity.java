package com.example.adminys.test1;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;

public class LinearLayoutActivity extends AppCompatActivity {

    private Button btnl1, btnl2, jietu;
    private LinearLayout ll;
    private RelativeLayout rl;
    private TextView rltext, changeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_layout);

        ll = (LinearLayout) findViewById(R.id.abc);
        rl = (RelativeLayout) findViewById(R.id.efg);

        btnl1 = (Button) findViewById(R.id.btnl1);
        btnl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] location = new int[2];
                btnl2.getLocationOnScreen(location);
                Log.d("abc", "x:" + location[0] + ",y:" + location[1]);
            }
        });
        btnl2 = (Button) findViewById(R.id.btnl2);
        btnl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ll.measure(0, 0);
                int width = ll.getMeasuredWidth();
                int Height = ll.getMeasuredHeight();
                Log.d("abc", "width:" + width + ",Height:" + Height);
            }
        });

        rltext = (TextView) findViewById(R.id.rltest);
        rltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button nBtn = new Button(LinearLayoutActivity.this);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.addRule(RelativeLayout.RIGHT_OF, R.id.rltest);
                layoutParams.addRule(RelativeLayout.BELOW, R.id.rltest);
                nBtn.setLayoutParams(layoutParams);
                nBtn.setText("成功！");
                rl.addView(nBtn);
            }
        });

        jietu = (Button) findViewById(R.id.jietu);
        jietu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.linear_layout, null);
                //打开图像缓存
                view.setDrawingCacheEnabled(true);
                //必须要调用measure和layout方法才能成功保存可视组件的截图到png图像文件
                //测量view的大小
                view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                //发送位置和尺寸到view和其所有的子view
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                try {
                    //获取可视组件的截图
                    Bitmap bitmap = view.getDrawingCache();
                    //将截图保存到SD卡根目录的test.png图像文件中
                    FileOutputStream fos = new FileOutputStream("/sdcard/test.png");
                    //将Bitmap对象中的图像数据压缩成png格式的图像数据，并将这些数据保存在test.png文件中
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    //关闭文件输出流
                    fos.close();
                    Log.d("abc", "布局截图成功");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        changeColor = (TextView) findViewById(R.id.changeColor);
        changeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置从上到下的渐变色，上面为蓝色，下面为黄色
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.BOTTOM_TOP, new int[]{Color.YELLOW, Color.BLUE});
                //设置当前窗口的渐变背景色
                rl.setBackgroundDrawable(gradientDrawable);
            }
        });

    }


}
