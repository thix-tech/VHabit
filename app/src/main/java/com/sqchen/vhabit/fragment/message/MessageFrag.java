package com.sqchen.vhabit.fragment.message;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.FragmentDealAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFrag extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener{

    //顶部选项卡按钮，“私信”，“通知”
    private Button msgLetterBtn,msgNoticeBtn;

    //顶部标题栏右侧图片，“发起聊天”
    private ImageView msgChatImg;

    // 用于填充fragment
    ViewPager mViewPager;

    //所有按钮的实例数组
    private Button[] btnArgs;

    // fragment集合，对应各个界面
    private ArrayList<Fragment> mFragments;

    //用于装填选项卡的适配器
    private FragmentDealAdapter msgAdapter;

    //顶部tab标识图片的开始横坐标位置
    private float cursorX = 0;

    //底部标识图片控件
    private ImageView mLineImg;

    //中间tab布局
    private LinearLayout mLinCenter;

    //用于存储中间tab布局相对于父布局的左边距
    private int mLeftCenter;

    //用于存储中间的tab布局的宽度
    private int mImgWidth;

    //用于判断是否已经测量过中间的tab布局的宽度
    private boolean isMeasured = false;

    public MessageFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载布局
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        //获取控件实例
        mViewPager = (ViewPager) rootView.findViewById(R.id.message_viewpager);
        msgLetterBtn = (Button) rootView.findViewById(R.id.message_title_letter);
        msgNoticeBtn = (Button) rootView.findViewById(R.id.message_title_notice);
        msgChatImg = (ImageView) rootView.findViewById(R.id.message_title_chat);
        mLineImg = (ImageView) rootView.findViewById(R.id.msg_top_bottom);
        mLinCenter = (LinearLayout) rootView.findViewById(R.id.title_message_center);

        //初始化按钮数组
        btnArgs = new Button[]{msgLetterBtn,msgNoticeBtn};
        mLineImg.setBackgroundColor(getResources().getColor(R.color.white));

        /*
        在onCreate发生时只是进行数据初始化，此时还没有正式地绘制图形，
        需要获取到控件的观察者，onPreDraw会在绘制控件之前回调。
        但是此方法在之后可能会被反复调用，所以要加一个变量进行判断
        參考鏈接：http://www.cnblogs.com/wt616/archive/2012/05/11/2496180.html
         */
        ViewTreeObserver treeObserver = mLinCenter.getViewTreeObserver();
        treeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                //还没测量过时，测量、赋值
                if(!isMeasured) {
                    mImgWidth = mLinCenter.getMeasuredWidth() / 2;
                    Log.d("sqchen","观察者：" + mImgWidth);
                    mLeftCenter = mLinCenter.getLeft();

                    cursorAnim(0,mImgWidth,mLeftCenter);
                    //测量过后改变变量值
                    isMeasured = true;
                }
                return true;
            }
        });
//        treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                mImgWidth = mLinCenter.getWidth() / 2;
//                mLeftCenter = mLinCenter.getLeft();
//            }
//        });

        //填充选项卡
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new MsgLetterFrag());
        mFragments.add(new MsgNoticeFrag());

        //初始化适配器
        msgAdapter = new FragmentDealAdapter(getFragmentManager(),mFragments);
        //绑定适配器
        mViewPager.setAdapter(msgAdapter);
        //设置监听器
        mViewPager.setOnPageChangeListener(this);

        //设置按钮监听器
        msgLetterBtn.setOnClickListener(this);
        msgNoticeBtn.setOnClickListener(this);

        //先将所有按钮文字颜色重置为未选中状态
        resetStatus();
        //再将第一个按钮文字状态设置为选中状态，即初次加载时先显示第一个页面
        msgLetterBtn.setTextColor(getResources().getColor(R.color.white));
        msgLetterBtn.setTextSize(18);
//        cursorAnim(0);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.message_title_letter:
                mViewPager.setCurrentItem(0);
                cursorAnim(0,mImgWidth,mLeftCenter);
                break;
            case R.id.message_title_notice:
                mViewPager.setCurrentItem(1);
                cursorAnim(1,mImgWidth,mLeftCenter);
                break;
        }
    }

    /**
     * 重置所有按钮的文字颜色和大小
     */
    private void resetStatus() {
        msgLetterBtn.setTextColor(getResources().getColor(R.color.text_no_focus));
        msgLetterBtn.setTextSize(18);
        msgNoticeBtn.setTextColor(getResources().getColor(R.color.text_no_focus));
        msgNoticeBtn.setTextSize(18);
    }


    /**
     *  实现ViewPager监听器时需要实现这几个方法，滑动状态改变时、滑动时、滑动结束时（即已确定页面）
     * @param arg0
     */

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0,float arg1,int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        //每次滑动时先将所有按钮的文字状态重置为未选中状态
        resetStatus();
        //再将选中项设置为选中状态
        btnArgs[arg0].setTextColor(getResources().getColor(R.color.white));
        btnArgs[arg0].setTextSize(18);
        cursorAnim(arg0,mImgWidth,mLeftCenter);

    }



    /**
     * 设置顶部tab的底部标识
     * @param position
     */
    private void cursorAnim(int position,int lineWidth,int lineLeft) {

        //获取底部标识的布局参数对象
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLineImg.getLayoutParams();
        //设置底部标识的宽度为屏幕宽度的1/6
        lp.width = lineWidth;
        mLineImg.setLayoutParams(lp);
        Log.d("sqchen","" + mImgWidth);

        //先将底部标识的开始横坐标设置为0
        cursorX = 0;
        //设置底部标识的开始横坐标
        cursorX = lineLeft + lineWidth * position;
        mLineImg.setX(cursorX);
    }

}
