package com.sqchen.vhabit.activity;

import android.content.Intent;
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
import com.sqchen.vhabit.fragment.personal.FollowedFrag;
import com.sqchen.vhabit.fragment.personal.FollowingFrag;
import com.sqchen.vhabit.fragment.personal.ShareVHabit;
import com.sqchen.vhabit.widgets.CustomTitleView;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private CustomTitleView mTitleView;

    private LinearLayout mFollowing;

    private LinearLayout mFollowed;

    private ImageView mLinesImg;

    private ViewPager mViewPager;

    private FragmentDealAdapter mAdapter;

    private List<Fragment> mFragmentList;

    private TextView[] txtArgs;

    private TextView mFollowingTxt;

    private TextView mFollowedTxt;

    private int cursorX;

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        initView();
    }

    private void initView() {

        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("我的好友");
        mTitleView.setImgRight(R.drawable.ic_banner_add_friend);
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleView.setImgRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShareVHabit.class);
                startActivity(intent);
            }
        });

        mFollowingTxt = (TextView) findViewById(R.id.friends_following_txt);
        mFollowedTxt = (TextView) findViewById(R.id.friends_followed_txt);
        txtArgs = new TextView[]{mFollowingTxt,mFollowedTxt};

        mFollowed = (LinearLayout) findViewById(R.id.lin_friends_followed);
        mFollowing = (LinearLayout) findViewById(R.id.lin_friends_following);
        mFollowing.setOnClickListener(this);
        mFollowed.setOnClickListener(this);

        mLinesImg = (ImageView) findViewById(R.id.friends_top_lines);
        mLinesImg.setBackgroundColor(getResources().getColor(R.color.green));

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new FollowingFrag());
        mFragmentList.add(new FollowedFrag());

        mViewPager = (ViewPager) findViewById(R.id.friends_view_pager);
        mViewPager.setOnPageChangeListener(this);
        mAdapter = new FragmentDealAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mAdapter);

        //先重置文本状态
        resetTxtStatus();
        //获取从上一个页面传递来的intent实例
        mIntent = getIntent();
        //取出键值
        int tabType = mIntent.getIntExtra("tab_type",3);
        //如果为0，即用户点击关注，先显示关注界面
        if(tabType == 0) {
            mFollowingTxt.setTextColor(getResources().getColor(R.color.green));
            cursorAnim(0);
            mViewPager.setCurrentItem(0);
        } else if(tabType == 1){            //如果为1，即用户点击粉丝，先显示粉丝界面
            mFollowedTxt.setTextColor(getResources().getColor(R.color.green));
            cursorAnim(1);
            mViewPager.setCurrentItem(1);
        }

    }

    private void resetTxtStatus() {
        mFollowingTxt.setTextColor(getResources().getColor(R.color.textBlack));
        mFollowedTxt.setTextColor(getResources().getColor(R.color.textBlack));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.lin_friends_following:
                mViewPager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.lin_friends_followed:
                mViewPager.setCurrentItem(1);
                cursorAnim(1);
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0,float arg1,int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        resetTxtStatus();
        txtArgs[arg0].setTextColor(getResources().getColor(R.color.green));
        cursorAnim(arg0);
    }

    private void cursorAnim(int position) {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        int screenWidth = dpMetrics.widthPixels;

        cursorX = 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLinesImg.getLayoutParams();
        layoutParams.width = screenWidth / 2;
        mLinesImg.setLayoutParams(layoutParams);
        cursorX = screenWidth / 2 * position;
        mLinesImg.setX(cursorX);
    }

}
