package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.DynamicListAdapter;
import com.sqchen.vhabit.beans.Dynamic;
import com.sqchen.vhabit.widgets.CustomTitleView;
import com.sqchen.vhabit.widgets.ListViewForScrollView;
import com.sqchen.vhabit.widgets.RingProgressView;

import java.util.ArrayList;
import java.util.List;

public class MyHabitDetails extends Activity {

    private ImageView checkImgBtn;

    private LinearLayout mCheckWhiteLin;

    private CustomTitleView mTitleView;

    private TextView todayTxt;

    private ImageView mRecordImg;

    private RingProgressView mProgressView;

    private FrameLayout mFrameLayout;

    private LinearLayout mGrowLin;

    private ListViewForScrollView mListView;

    private DynamicListAdapter mAdapter;

    private List<Dynamic> mDynamicList;

    private ScrollView mScrollView;

    private boolean isCheck = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_habit_details);

        mTitleView = (CustomTitleView) findViewById(R.id.habit_details_title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("对一天进行回顾");
        mTitleView.setTxtRight("工具");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mProgressView = (RingProgressView) findViewById(R.id.ring_progress);

        mFrameLayout = (FrameLayout) findViewById(R.id.frame_ring_progress);
        checkImgBtn = (ImageView) findViewById(R.id.check_btn);
        mCheckWhiteLin = (LinearLayout) findViewById(R.id.habit_check_white_lin);
        todayTxt = (TextView) findViewById(R.id.habit_details_today);
        mRecordImg = (ImageView) findViewById(R.id.habit_details_record);

        checkImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheck == false) {
                    isCheck = mProgressView.startUpdate(checkImgBtn,mFrameLayout,mRecordImg);
                } else {
                    isCheck = mProgressView.cancelUpdate(checkImgBtn,mFrameLayout,mRecordImg);
                }

            }
        });

        //生长统计布局
        mGrowLin = (LinearLayout) findViewById(R.id.habit_details_grow_lin);

        initDynamics();
        mListView = (ListViewForScrollView) findViewById(R.id.habit_details_listview);
        mAdapter = new DynamicListAdapter(this,mDynamicList,false,false);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),DynamicDetails.class);
                intent.putExtra("selectedDynamic",mDynamicList.get(position));
                startActivity(intent);
            }
        });

        mScrollView = (ScrollView) findViewById(R.id.habit_details_scrollview);
        mScrollView.smoothScrollTo(0,0);
    }

    //初始化点赞动态用户数组的数据
    private void initDynamics() {
        mDynamicList = new ArrayList<Dynamic>();
        mDynamicList.add(new Dynamic(R.drawable.user_1,"我是柠檬君","#对一天进行回顾#","今天 12:53","11天",
                0,"我想要你",null,3));
        mDynamicList.add(new Dynamic(R.drawable.user_5,"隐进盛夏的两脚兽","#对一天进行回顾#","2月26日","18天",
                0,"红菜心开花了，田野的气息",null,3));
        mDynamicList.add(new Dynamic(R.drawable.user_3,"南木","#对一天进行回顾#","昨天 11:14","289天",
                0,"大通道真的好美",null,3));
        mDynamicList.add(new Dynamic(R.drawable.user_1,"我是柠檬君","#对一天进行回顾#","昨天 20:14","291天",
                0,"奥地利表现主义；毕加索与勃拉克之后的巴黎立体主义；意大利未来主义。各国艺术的线代主义化，" +
                "我觉得这些作品并不需要用来理解，只能用来感受。",null,3));
    }
}
