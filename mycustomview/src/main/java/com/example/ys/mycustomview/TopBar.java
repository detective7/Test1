package com.example.ys.mycustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 作者： Ys
 * 日期： 2016/7/10
 * 功能：
 */
public class TopBar extends RelativeLayout {

    Context context;
    int mLeftTextColor,mRightTextColor,mTitleTextColor;
    Drawable mLeftBackground,mRightBackground;
    String mLeftText,mRightText,mTitle;
    float mTitleTextSize;

    Button mLeftButton,mRightButton;
    TextView mTitleView;

    topbarClickListener mListener;

    private LayoutParams mLeftParams,mRightParams,mTitleParams;

    public TopBar(Context context) {
        super(context);
        this.context = context;
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //通过这个方法，将你在atts.xml中定义的declare-styleable的所有属性的值都存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.TopBar);

        //从TypedArray中取出对应的值来为对应的属性赋值
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor,0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor,0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize,10);
        mTitle = ta.getString(R.styleable.TopBar_titleText);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleColor,0);

        //获取完TypeArray的值后，一般要调用recyle方法来避免重新创建的时候的错误
        ta.recycle();

        //组合控件，将以上取到的值赋予按钮，文本
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new Button(context);

        //赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        //为组件设置相应的布局元素
        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mLeftButton,mLeftParams);

        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        addView(mRightButton,mRightParams);

        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView,mTitleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.leftClick();
            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.rightClick();
            }
        });
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface topbarClickListener{
        void leftClick();
        void rightClick();
    }

    public void setOnTopbarClickListener(topbarClickListener mListener){
        this.mListener = mListener;
    }

    /**
     * @param id 区分按钮
     * @param flag 区分是否显示
     */
    public void setButtonVisable(int id,boolean flag){
        if(flag){
            if(id==0){
                mLeftButton.setVisibility(VISIBLE);
            }else {
                mRightButton.setVisibility(VISIBLE);
            }
        }else{
            if(id==0){
                mLeftButton.setVisibility(GONE);
            }else {
                mRightButton.setVisibility(GONE);
            }
        }
    }
}
