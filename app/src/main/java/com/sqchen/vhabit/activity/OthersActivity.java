package com.sqchen.vhabit.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.FragmentDealAdapter;
import com.sqchen.vhabit.fragment.personal.ByHabitFrag;
import com.sqchen.vhabit.fragment.personal.ByTimeFrag;
import com.sqchen.vhabit.widget.CustomTitleView;
import com.sqchen.vhabit.widget.ViewPagerForScrollView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class OthersActivity extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    //顶部标题栏控件
    private CustomTitleView mTitleView;

    //适应ScrollView的ViewPager
    private ViewPagerForScrollView mViewPager;

    //按照习惯排序的标识图标
    private ImageView imgByHabit;

    //按照时间排序的标识图标
    private ImageView imgByTime;

    //排序标识图标数组
    private ImageView[] imgArgs;

    //排序标识图标的ID数组
    private int[] imgIdArgs;

    //fragment数组，即对应各个页面
    private List<Fragment> mList = new ArrayList<Fragment>();

    //ViewPager适配器
    private FragmentDealAdapter mAdapter;

    //滑动控件
    private ScrollView mScrollView;

    //头像
    private CircleImageView circlePslIcon;

    //习惯
    private LinearLayout mLinHabit;

    //关注
    private LinearLayout mLinFollowing;

    //粉丝
    private LinearLayout mLinFollowed;

    //个人信息
    private LinearLayout mLinTop;

    public OthersActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //加载布局
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others);

        //获取控件实例
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtRight("关注");
        mTitleView.setTxtCenter(getString(R.string.user_name));
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //滑动控件实例
        mScrollView = (ScrollView) findViewById(R.id.others_scrollview);

        //顶部个人信息布局实例
        mLinTop = (LinearLayout) findViewById(R.id.others_top);

        //习惯、关注、粉丝
        mLinHabit = (LinearLayout) findViewById(R.id.others_top_habit);
        mLinFollowing = (LinearLayout) findViewById(R.id.others_top_follow);
        mLinFollowed = (LinearLayout) findViewById(R.id.others_top_fans);
        mLinHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击习惯，滑动到内容视图，即习惯视图
                mScrollView.smoothScrollTo(0,mLinTop.getHeight());
            }
        });
        mLinFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendsActivity.class);
                intent.putExtra("tab_type",0);
                startActivity(intent);
            }
        });
        mLinFollowed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FriendsActivity.class);
                intent.putExtra("tab_type",1);
                startActivity(intent);
            }
        });

        circlePslIcon = (CircleImageView) findViewById(R.id.others_user_icon);

        imgByHabit = (ImageView) findViewById(R.id.others_sort_by_habit);
        imgByTime = (ImageView) findViewById(R.id.others_sort_by_time);

        //设置监听器
        imgByHabit.setOnClickListener(this);
        imgByTime.setOnClickListener(this);

        //初始化数组
        imgArgs = new ImageView[]{imgByHabit,imgByTime};
        imgIdArgs = new int[]{R.drawable.sort_by_habit_selected,R.drawable.sort_by_time_selected};

        //将两个页面fragment添加到数组
        mList.add(new ByHabitFrag());
        mList.add(new ByTimeFrag());

        mViewPager = (ViewPagerForScrollView) findViewById(R.id.others_view_pager);
        mAdapter = new FragmentDealAdapter(getSupportFragmentManager(),mList);
        mViewPager.setAdapter(mAdapter);

        //设置ViewPager滑动监听
        mViewPager.setOnPageChangeListener(this);

        //先重置为未选中状态
        resetStatus();
        //再设置为“按习惯排序”，即首次加载时先显示第1个页面
        imgByHabit.setImageResource(R.drawable.sort_by_habit_selected);

        //滚动到顶端
        mScrollView.smoothScrollTo(0,0);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.personal_sort_by_habit:
                mViewPager.setCurrentItem(0);
                resetStatus();
                imgByHabit.setImageResource(R.drawable.sort_by_habit_selected);
                break;
            case R.id.personal_sort_by_time:
                mViewPager.setCurrentItem(1);
                resetStatus();
                imgByTime.setImageResource(R.drawable.sort_by_time_selected);
                break;
        }
    }

    /**
     * 重置图片为未选中状态
     */
    private void resetStatus() {
        imgByTime.setImageResource(R.drawable.sort_by_time);
        imgByHabit.setImageResource(R.drawable.sort_by_habit);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0,float arg1,int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        //每次滑动先重置图片状态
        resetStatus();
        //再将选中项设置为选中状态
        imgArgs[arg0].setImageResource(imgIdArgs[arg0]);
    }
}
