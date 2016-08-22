package com.example.ys.mycustomview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者： Ys
 * 日期： 2016/8/18
 * 功能：
 */
public class CustomPart extends View {

    //屏幕高宽
    private int height, width;
    private Context context;
    int mCircleXY;
    float mRadius,mShowTextSize;

    RectF mArcRectF;
    Paint mCirclePaint,mArcPaint,mTextPaint;
    float mSweepAngle;

    public CustomPart(Context context) {
        super(context);
        this.context = context;
        this.setWillNotDraw(false);
    }

    public CustomPart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setWillNotDraw(false);
    }

    public CustomPart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        //将值设置进去，可能是super.onMeasureSpec可以代替，才不会报IllegalStateException错误
        //setMeasuredDimension(width,height);
        initSize();
    }

    private void initSize() {

        mCircleXY = width / 2;
        mRadius = (float) (width * 0.5 / 2);

        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setAntiAlias(true);

        mArcPaint = new Paint();
        mArcPaint.setColor(Color.GREEN);
        mArcPaint.setStrokeWidth(50);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setAntiAlias(true);
        mShowTextSize = 50;
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        //绘制弧线，需要指定其椭圆的外接矩形
        mArcRectF = new RectF((float) (width * 0.1), (float) (width * 0.1), (float) (width * 0.9), (float) (width * 0.9));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制圆
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mCirclePaint);
        //绘制弧线
        canvas.drawArc(mArcRectF,180,mSweepAngle,false,mArcPaint);
        //绘制文字
        String mShowText ="Test text!" ;

        canvas.drawText(mShowText,0,mShowText.length(),mCircleXY,mCircleXY+(mShowTextSize/4),mTextPaint);
    }

    public void setSweepValue(float sweepValue){
        if(sweepValue!=0){
            mSweepAngle = sweepValue;
        }else{
            mSweepAngle=25;
        }
        this.invalidate();
//        ((View)this.getParent()).invalidate();
    }


}
