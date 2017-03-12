package com.sqchen.vhabit.fragment.find;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.SearchActivity;
import com.sqchen.vhabit.adapter.FragmentDealAdapter;

import java.util.ArrayList;

/**
 * “发现”页面 参考连接：http://blog.csdn.net/it_zjyang/article/details/51584439
 * A simple {@link Fragment} subclass.
 */
public class FindFrag extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener{

    //用于装填fragment
    private ViewPager mViewPager;

    //顶部选项卡的按钮
    private Button findHotBtn,findFocusBtn,findNewBtn;

    //顶部按钮实例数组
    private Button[] btnArgs;

    //fragment集合，对应各个界面
    private ArrayList<Fragment> fragments;

    //用于装填选项卡的适配器
    private FragmentDealAdapter findAdapter;

    private View blankView;

    private ImageView mSearchImg;

    private ImageView mLineImg;

    //顶部tab标识图片的开始横坐标位置
    private float cursorX = 0;


    public FindFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载布局
        View rootView = inflater.inflate(R.layout.fragment_find,container,false);

        //获取控件实例
        mViewPager = (ViewPager) rootView.findViewById(R.id.find_viewpager);
        findFocusBtn = (Button) rootView.findViewById(R.id.find_title_focus);
        findHotBtn = (Button) rootView.findViewById(R.id.find_title_hot);
        findNewBtn = (Button) rootView.findViewById(R.id.find_title_new);
        blankView = (View) rootView.findViewById(R.id.find_blank_view);
        mSearchImg = (ImageView) rootView.findViewById(R.id.find_search);
        mLineImg = (ImageView) rootView.findViewById(R.id.find_top_bottom);

        mLineImg.setBackgroundColor(getResources().getColor(R.color.white));
        mSearchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        //初始化按钮数组
        btnArgs = new Button[]{findHotBtn,findFocusBtn,findNewBtn};

        //填充选项卡
        fragments = new ArrayList<Fragment>();
        fragments.add(new FindHotFrag());
        fragments.add(new FindFocusFrag());
        fragments.add(new FindNewFrag());

        //初始化适配器
        findAdapter = new FragmentDealAdapter(getFragmentManager(),fragments);

        //绑定适配器
        mViewPager.setAdapter(findAdapter);

        //设置ViewPager监听器
        mViewPager.setOnPageChangeListener(this);

        //设置按钮监听器
        findFocusBtn.setOnClickListener(this);
        findHotBtn.setOnClickListener(this);
        findNewBtn.setOnClickListener(this);

        //先将所有按钮文字颜色重置为未选中状态
        resetSatus();
        //再将第一个按钮文字设置为选中状态，即初次加载时先显示第一个页面
        findHotBtn.setTextColor(getResources().getColor(R.color.white));
        findHotBtn.setTextSize(18);
        cursorAnim(0);


        return rootView;
    }

    /**
     * 重置按钮文字的颜色状态
     */
    private void resetSatus() {
        findFocusBtn.setTextColor(getResources().getColor(R.color.text_no_focus));
        findFocusBtn.setTextSize(17);
        findHotBtn.setTextColor(getResources().getColor(R.color.text_no_focus));
        findHotBtn.setTextSize(17);
        findNewBtn.setTextColor(getResources().getColor(R.color.text_no_focus));
        findNewBtn.setTextSize(17);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.find_title_hot:
                mViewPager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.find_title_focus:
                mViewPager.setCurrentItem(1);
                cursorAnim(1);
                break;
            case R.id.find_title_new:
                mViewPager.setCurrentItem(2);
                cursorAnim(2);
                break;
        }
    }

    /**
     * 实现ViewPager监听器时需要实现这几个方法，滑动状态改变时、滑动时、滑动结束时（即已确定页面）
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
        //每次滑动后先将所有按钮文字重置为未选中状态
        resetSatus();
        //再将选中项设置为选中状态
        btnArgs[arg0].setTextColor(getResources().getColor(R.color.white));
        btnArgs[arg0].setTextSize(18);
        cursorAnim(arg0);
    }

    /**
     * 设置顶部tab的底部标识
     * @param position
     */
    private void cursorAnim(int position) {
        //获取屏幕宽度
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        int screenWidth = dpMetrics.widthPixels;

        //先将底部标识的开始横坐标设置为0
        cursorX = 0;
        //获取底部标识的布局参数对象
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLineImg.getLayoutParams();
        //设置底部标识的宽度为屏幕宽度的1/6
        lp.width = screenWidth / 5;
        mLineImg.setLayoutParams(lp);
        //设置底部标识的开始横坐标
        cursorX = screenWidth / 5 * (position + 1);
        mLineImg.setX(cursorX);
    }


}
