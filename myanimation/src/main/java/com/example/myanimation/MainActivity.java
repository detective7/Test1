package com.example.myanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private View view;
    private Button btn1, btn2,btn3,btn4;
    private ImageView iv1,iv2,iv3,iv4;
    private Animation myAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view=View.inflate(this, R.layout.activity_main, null);
        setContentView(view);

        btn1 = (Button) findViewById(R.id.alphaX);
        btn2 = (Button) findViewById(R.id.scaleX);
        btn3 = (Button) findViewById(R.id.translateX);
        btn4 = (Button) findViewById(R.id.rotateX);
        iv1 = (ImageView)findViewById(R.id.blue);
        iv2 = (ImageView)findViewById(R.id.rb);
        iv3 = (ImageView)findViewById(R.id.ub);
        iv4 = (ImageView)findViewById(R.id.ue);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation = AnimationUtils.loadAnimation(iv1.getContext(),R.anim.alpha);
                iv1.startAnimation(myAnimation);
//                myAnimation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation arg0) {}   //在动画开始时使用
//
//                    @Override
//                    public void onAnimationRepeat(Animation arg0) {}  //在动画重复时使用
//
//                    @Override
//                    public void onAnimationEnd(Animation arg0) {
//                        Toast.makeText(MainActivity.this, "在动画结束时使用", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation = AnimationUtils.loadAnimation(iv2.getContext(),R.anim.scale);
                iv2.startAnimation(myAnimation);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation = AnimationUtils.loadAnimation(iv3.getContext(),R.anim.translate);
                iv3.startAnimation(myAnimation);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnimation = AnimationUtils.loadAnimation(iv4.getContext(),R.anim.rotate);
                iv4.startAnimation(myAnimation);
            }
        });
    }


}
