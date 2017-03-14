package com.sqchen.vhabit.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.FragmentDealAdapter;
import com.sqchen.vhabit.fragment.habit.EfficiencyFrag;
import com.sqchen.vhabit.fragment.habit.ExerciseFrag;
import com.sqchen.vhabit.fragment.habit.HealthFrag;
import com.sqchen.vhabit.fragment.habit.HotFrag;
import com.sqchen.vhabit.fragment.habit.LearningFrag;
import com.sqchen.vhabit.fragment.habit.ThinkingFrag;
import com.sqchen.vhabit.widget.CustomTitleView;

import java.util.ArrayList;
import java.util.List;

/**
 * “添加习惯”界面
 */
public class NewHabitActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    //顶部标题栏
    private CustomTitleView mTitleView;

    //用于装载fragment
    private ViewPager mViewPager;

    //fragment集合
    private List<Fragment> mFragments;

    //ViewPager适配器
    private FragmentDealAdapter mAdapter;

    //顶部tab选项文本控件
    private TextView txtHot,txtHealth,txtExercise,txtLearning,txtEfficiency,txtThinking;

    //顶部tab文本控件集合
    private TextView[] txtArgs;

    //顶部tab的标识图片
    private ImageView mLineImg;

    //顶部tab标识图片的开始横坐标位置
    private float cursorX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);
        initView();
    }

    private void initView() {
        //标题栏控件初始化
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtRight("创建");
        mTitleView.setTxtCenter("更多习惯");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取控件实例
        mViewPager = (ViewPager) findViewById(R.id.new_habit_view_pager);
        txtHot = (TextView) findViewById(R.id.new_habit_hot);
        txtHealth = (TextView) findViewById(R.id.new_habit_health);
        txtExercise = (TextView) findViewById(R.id.new_habit_exercise);
        txtLearning = (TextView) findViewById(R.id.new_habit_learning);
        txtEfficiency = (TextView) findViewById(R.id.new_habit_efficiency);
        txtThinking = (TextView) findViewById(R.id.new_habit_thinking);

        //初始化文本控件集合
        txtArgs = new TextView[]{txtHot,txtHealth,txtExercise,txtLearning,txtEfficiency,txtThinking};

        //初始化顶部tab标识
        mLineImg = (ImageView) findViewById(R.id.new_habit_bottom);
        mLineImg.setBackgroundColor(getResources().getColor(R.color.green));

        //注册文本控件的点击事件
        txtHot.setOnClickListener(this);
        txtHealth.setOnClickListener(this);
        txtExercise.setOnClickListener(this);
        txtLearning.setOnClickListener(this);
        txtEfficiency.setOnClickListener(this);
        txtThinking.setOnClickListener(this);

        //初始化fragment
        mFragments = new ArrayList<Fragment>();
        mFragments.add( new HotFrag());
        mFragments.add( new HealthFrag());
        mFragments.add(new ExerciseFrag());
        mFragments.add(new LearningFrag());
        mFragments.add(new EfficiencyFrag());
        mFragments.add(new ThinkingFrag());

        //设置ViewPager的滑动监听器
        mViewPager.setOnPageChangeListener(this);
        //初始化适配器
        mAdapter = new FragmentDealAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mAdapter);

        //先将所有文本控件状态重置为未选中状态
        resetTextColor();
        //再将第一个文本控件设置为选中状态
        txtHot.setTextColor(getResources().getColor(R.color.green));
        cursorAnim(0);
    }

    /**
     * 重置文本控件为未选中状态
     */
    private void resetTextColor() {
        txtHot.setTextColor(getResources().getColor(R.color.text_no_letter_first));
        txtHealth.setTextColor(getResources().getColor(R.color.text_no_letter_first));
        txtLearning.setTextColor(getResources().getColor(R.color.text_no_letter_first));
        txtEfficiency.setTextColor(getResources().getColor(R.color.text_no_letter_first));
        txtExercise.setTextColor(getResources().getColor(R.color.text_no_letter_first));
        txtThinking.setTextColor(getResources().getColor(R.color.text_no_letter_first));
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.new_habit_hot:
                mViewPager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.new_habit_health:
                mViewPager.setCurrentItem(1);
                cursorAnim(1);
                break;
            case R.id.new_habit_exercise:
                mViewPager.setCurrentItem(2);
                cursorAnim(2);
                break;
            case R.id.new_habit_learning:
                mViewPager.setCurrentItem(3);
                cursorAnim(3);
                break;
            case R.id.new_habit_efficiency:
                mViewPager.setCurrentItem(4);
                cursorAnim(4);
                break;
            case R.id.new_habit_thinking:
                mViewPager.setCurrentItem(5);
                cursorAnim(5);
                break;
        }
    }

    /**
     * 滑动时触发该方法
     * @param arg0
     * @param arg1
     * @param arg2
     */
    @Override
    public void onPageScrolled(int arg0,float arg1,int arg2) {

    }

    /**
     * 滑动状态改变时，分为滑动开始、滑动中、滑动结束
     * @param arg0
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    /**
     * 选中某项时触发该方法
     * @param arg0
     */
    @Override
    public void onPageSelected(int arg0) {
        //先重置为未选中状态
        resetTextColor();
        //再将选中项设置为选中状态
        txtArgs[arg0].setTextColor(getResources().getColor(R.color.green));
        cursorAnim(arg0);
    }

    /**
     * 设置顶部tab的底部标识
     * @param position
     */
    private void cursorAnim(int position) {
        //获取屏幕宽度
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        int screenWidth = dpMetrics.widthPixels;

        //先将底部标识的开始横坐标设置为0
        cursorX = 0;
        //获取底部标识的布局参数对象
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mLineImg.getLayoutParams();
        //设置底部标识的宽度为屏幕宽度的1/6
        lp.width = screenWidth / 6;
        mLineImg.setLayoutParams(lp);
        //设置底部标识的开始横坐标
        cursorX = screenWidth / 6 * position;
        mLineImg.setX(cursorX);
    }
}
