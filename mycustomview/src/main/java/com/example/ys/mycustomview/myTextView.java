package com.example.ys.mycustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 作者： Ys
 * 日期： 2016/7/9
 * 功能：
 */
public class myTextView extends TextView {

    Paint mPaint1,mPaint2;
    int mViewWidth,mTranslate;
    LinearGradient mLinearGradient;
    Matrix mGradientMatrix;

    public myTextView(Context context) {
        super(context);
    }

    public myTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public myTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth==0){
            mViewWidth = getMeasuredWidth();
            if(mViewWidth>0){
                mPaint1=getPaint();
                mLinearGradient = new LinearGradient(
                        0,
                        0,
                        mViewWidth,
                        0,
                        new int[]{
                                Color.BLUE,0xffffffff,Color.BLUE
                        },
                        null,
                        Shader.TileMode.CLAMP
                );
                mPaint1.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        mPaint1 = new Paint();
//        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
//        mPaint1.setStyle(Paint.Style.FILL);
//        mPaint2 = new Paint();
//        mPaint2.setColor(Color.YELLOW);
//        mPaint2.setStyle(Paint.Style.FILL);
//        //绘制外层矩形
//        canvas.drawRect(
//                0,
//                0,
//                getMeasuredWidth(),
//                getMeasuredHeight(),
//                mPaint1
//        );
//        //绘制内层矩形
//        canvas.drawRect(
//                5,
//                5,
//                getMeasuredWidth()-5,
//                getMeasuredHeight()-5,
//                mPaint2
//        );
//        canvas.save();
//        //绘制文字前平移10像素
//        canvas.translate(5,0);

        super.onDraw(canvas);
        if(mGradientMatrix!=null){
            mTranslate+=mViewWidth/5;
            if(mTranslate>2*mViewWidth){
                mTranslate=-mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
//        canvas.restore();
    }
}
