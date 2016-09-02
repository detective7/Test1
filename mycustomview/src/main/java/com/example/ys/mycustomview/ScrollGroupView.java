package com.example.ys.mycustomview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 作者： Ys
 * 日期： 2016/9/2
 * 功能：
 */
public class ScrollGroupView extends ViewGroup {

    private int mScreenHeight,mLastY,mStart,mEnd;
    private Scroller mScroller;


    public ScrollGroupView(Context context) {
        super(context);
        initView(context);
    }

    public ScrollGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScrollGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mScreenHeight = MeasureSpec.getSize(heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childCount = getChildCount();
        //设置VIewGroup的高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight*childCount;
        setLayoutParams(mlp);
        for (int n = 0; n < childCount; n++) {
            View child = getChildAt(n);
            if(child.getVisibility()!= View.GONE){
                child.layout(i,n*mScreenHeight,i2,(n+1)*mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       int y = (int) event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                if(getScrollY()<0){
                    dy = 0;
                }
                if(getScrollY()>getHeight()-mScreenHeight){
                    dy=0;
                }
                scrollBy(0,dy);
                mLastY=y;
                break;
        }
        return true;
    }
}
