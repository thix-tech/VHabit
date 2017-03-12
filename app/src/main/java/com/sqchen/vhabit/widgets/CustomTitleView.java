package com.sqchen.vhabit.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;

/**
 * Created by Administrator on 2017/2/28.
 */

public class CustomTitleView extends LinearLayout {
    /**
     * 控件声明
     */
    private TextView mTxtLeft,mTxtCenter,mTxtRight;
    private ImageView mImgLeft,mImgRight;

    /**
     * 构造函数
     * @param context   上下文环境
     */
    public CustomTitleView(Context context){
        super(context);
        initView(context);
    }

    public CustomTitleView(Context context, AttributeSet attr){
        super(context,attr);
        initView(context);
    }

    /**
     * 初始化控件
     * @param context
     */
    public void initView(Context context) {
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.view_title,this,true);
        mTxtLeft = (TextView) findViewById(R.id.title_txt_left);
        mTxtCenter = (TextView) findViewById(R.id.title_txt_center);
        mTxtRight = (TextView) findViewById(R.id.title_txt_right);
        mImgLeft = (ImageView) findViewById(R.id.title_img_left);
        mImgRight = (ImageView) findViewById(R.id.title_img_right);
    }

    /**
     * 获得可视化控件
     * @return
     */
    public TextView getTxtLeft() {
        mTxtLeft.setVisibility(View.VISIBLE);
        return mTxtLeft;
    }

    public TextView getTxtCenter() {
        mTxtCenter.setVisibility(View.VISIBLE);
        return mTxtCenter;
    }

    public TextView getTxtRight() {
        mTxtRight.setVisibility(View.VISIBLE);
        return mTxtRight;
    }

    public ImageView getImgLeft() {
        mImgLeft.setVisibility(View.VISIBLE);
        return mImgLeft;
    }

    public ImageView getImgRight() {
        mImgRight.setVisibility(View.VISIBLE);
        return mImgRight;
    }

    /**
     * 设置文本
     * @param text
     */
    public void setTxtLeft(String text) {
        getTxtLeft().setText(text);
    }

    public void setTxtCenter(String text) {
        getTxtCenter().setText(text);
    }

    public void setTxtRight(String text) {
        getTxtRight().setText(text);
    }

    /**
     * 设置左边的TextView的左边的图标
     *
     * @param iconId
     */
    public void setTxtLeftIcon(int iconId) {
        Drawable arrowLeft = getResources().getDrawable(iconId);
        arrowLeft.setBounds(0, 0, arrowLeft.getMinimumWidth(),
                arrowLeft.getMinimumHeight());
        getTxtLeft().setCompoundDrawables(arrowLeft, null, null, null);
    }

    /**
     * 设置右边的TextView的右边的图标
     *
     * @param iconId
     */
    public void setTxtRightIcon(int iconId) {
        Drawable arrowLeft = getResources().getDrawable(iconId);
        arrowLeft.setBounds(0, 0, arrowLeft.getMinimumWidth(),
                arrowLeft.getMinimumHeight());
        getTxtRight().setText("");
        getTxtRight().setCompoundDrawables(null, null, arrowLeft, null);
    }

    /**
     * 设置图标
     * @param imgId
     */
    public void setImgLeft(int imgId) {
        getImgLeft().setImageResource(imgId);
    }

    public void setImgRight(int imgId) {
        getImgRight().setImageResource(imgId);
    }

    /**
     * 设置控件监听器
     * @param listener
     */
    public void setTxtLeftOnClickListener(OnClickListener listener) {
        getTxtLeft().setOnClickListener(listener);
    }

    public void setTxtCenterOnClickListener(OnClickListener listener) {
        getTxtCenter().setOnClickListener(listener);
    }

    public void setTxtRightOnClickListener(OnClickListener listener) {
        getTxtRight().setOnClickListener(listener);
    }

    public void setImgLeftOnClickListener(OnClickListener listener) {
        getImgLeft().setOnClickListener(listener);
    }

    public void setImgRightOnClickListener(OnClickListener listener) {
        getImgRight().setOnClickListener(listener);
    }


}
