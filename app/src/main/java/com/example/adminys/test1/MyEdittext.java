package com.example.adminys.test1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by Administrator on 2015/12/17 0017.
 * 自定义EditText，
 * 功能1：实现提示信息保留，输入从提示信息右边开始，需要在调用时，用上paddingLeft属性
 * 功能2：实现清空全部输入
 */
public class MyEdittext extends EditText {

    private Drawable imgUnable;
    private Drawable imgAble;
    private Context mContext;

    public MyEdittext(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public MyEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public MyEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public MyEdittext(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init(){
        imgUnable = mContext.getResources().getDrawable(R.drawable.delete_gray);
        imgAble = mContext.getResources().getDrawable(R.drawable.delete);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        setDrawable();
    }
    //设置删除图片
    private void setDrawable(){
        if(length()<1){
            setCompoundDrawablesWithIntrinsicBounds(null,null,imgUnable,null);
        }else{
            setCompoundDrawablesWithIntrinsicBounds(null,null,imgAble,null);
        }
    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(imgAble!=null && event.getAction() == MotionEvent.ACTION_UP){
            int eventX = (int)event.getRawX();
            int eventY = (int)event.getRawY();
            Log.d("abc","eventX="+eventX+",eventY="+eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left=rect.left-50;
            if(rect.contains(eventX,eventY))
                setText("");
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(36);
        paint.setColor(Color.GRAY);
        canvas.drawText("提示内容：", 2, getHeight() / 2 + 5, paint);
        super.onDraw(canvas);
    }
}
