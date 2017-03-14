package com.sqchen.vhabit.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.FragmentDealAdapter;
import com.sqchen.vhabit.fragment.find.SearchHabitFrag;
import com.sqchen.vhabit.fragment.find.SearchUserFrag;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private TextView txtSearch;

    private EditText editSearch;

    private LinearLayout linHabit;

    private LinearLayout linUser;

    private TextView txtHabit;

    private TextView txtUser;

    private ImageView imgLine;

    private LinearLayout[] linArgs;

    private TextView[] txtArgs;

    private int cursorX;

    private ViewPager mViewPager;

    private FragmentDealAdapter mAdapter;

    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach);
        initView();
    }

    private void initView() {

        txtSearch = (TextView) findViewById(R.id.txt_cancel);
        editSearch = (EditText) findViewById(R.id.edit_search);
        txtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linHabit = (LinearLayout) findViewById(R.id.search_lin_habit);
        linUser = (LinearLayout) findViewById(R.id.search_lin_user);
        imgLine = (ImageView) findViewById(R.id.search_lines_img);
        mViewPager = (ViewPager) findViewById(R.id.search_view_pager);

        linHabit.setOnClickListener(this);
        linUser.setOnClickListener(this);
        linArgs = new LinearLayout[]{linHabit,linUser};
        imgLine.setBackgroundColor(getResources().getColor(R.color.green));

        txtHabit = (TextView) findViewById(R.id.search_txt_habit);
        txtUser = (TextView) findViewById(R.id.search_txt_user);
        txtArgs = new TextView[]{txtHabit,txtUser};

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(new SearchHabitFrag());
        mFragmentList.add(new SearchUserFrag());

        mAdapter = new FragmentDealAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);

        resetTxtStatus();
        txtHabit.setTextColor(getResources().getColor(R.color.green));
        cursorAnim(0);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.search_lin_habit:
                mViewPager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.search_lin_user:
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
    public void onPageSelected(int position) {
        resetTxtStatus();
        txtArgs[position].setTextColor(getResources().getColor(R.color.green));
        cursorAnim(position);
    }

    private void resetTxtStatus() {
        txtHabit.setTextColor(getResources().getColor(R.color.textBlack));
        txtUser.setTextColor(getResources().getColor(R.color.textBlack));
    }

    private void cursorAnim(int position) {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        int screenWidth = dpMetrics.widthPixels;

        cursorX = 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imgLine.getLayoutParams();
        layoutParams.width = screenWidth / 2;
        imgLine.setLayoutParams(layoutParams);
        cursorX = screenWidth / 2 * position;
        imgLine.setX(cursorX);
    }

}
