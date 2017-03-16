package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.sqchen.vhabit.bean.Dynamic;
import com.sqchen.vhabit.bean.Habit;
import com.sqchen.vhabit.bean.UserAndHabit;
import com.sqchen.vhabit.util.DateStrUtil;
import com.sqchen.vhabit.util.SharePreferencesUtil;
import com.sqchen.vhabit.util.ToastUtil;
import com.sqchen.vhabit.widget.CustomTitleView;
import com.sqchen.vhabit.widget.DlgLoading;
import com.sqchen.vhabit.widget.ListViewForScrollView;
import com.sqchen.vhabit.widget.RingProgressView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class MyHabitDetails extends Activity {

    //中间的签到按钮
    private ImageView checkImgBtn;

    //签到区域
    private LinearLayout mCheckWhiteLin;

    private CustomTitleView mTitleView;

    private TextView todayTxt;

    //发表动态按钮
    private ImageView mRecordImg;

    //签到按钮的外圆控件
    private RingProgressView mProgressView;

    private FrameLayout mFrameLayout;

    //周视图区域
    private LinearLayout mLinGrow;

    private ListViewForScrollView mListView;

    private DynamicListAdapter mAdapter;

    private List<Dynamic> mDynamicList;

    private ScrollView mScrollView;

    //记录今天是否已经签到
    private boolean isFinished;

    //接收HabitFrag传递过来的Intent实例
    private Intent mIntent = null;

    //当前查看的习惯
    private Habit currentHabit;

    //当前"用户习惯"，即UserAndHabit，用于获取签到信息
    private UserAndHabit currentData;

    //进度对话框
    private DlgLoading mDlgLoading;

    //当前是星期几
    private int week;

    //星期文本控件
    private TextView growTxt1,growTxt2,growTxt3,growTxt4,growTxt5,growTxt6,growTxt7;

    //星期图片控件
    private ImageView growImg1,growImg2,growImg3,growImg4,growImg5,growImg6,growImg7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_habit_details);

        mIntent = getIntent();
        currentHabit = (Habit) mIntent.getSerializableExtra("SELECT_HABIT");

        mTitleView = (CustomTitleView) findViewById(R.id.habit_details_title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter(currentHabit.getHabitName());
        mTitleView.setTxtRight(getString(R.string.str_habit_details_tool));
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mProgressView = (RingProgressView) findViewById(R.id.ring_progress);
        mDlgLoading = new DlgLoading(this);

        mFrameLayout = (FrameLayout) findViewById(R.id.frame_ring_progress);
        checkImgBtn = (ImageView) findViewById(R.id.check_btn);
        mCheckWhiteLin = (LinearLayout) findViewById(R.id.habit_check_white_lin);
        todayTxt = (TextView) findViewById(R.id.habit_details_today);
        mRecordImg = (ImageView) findViewById(R.id.habit_details_record);
        mRecordImg.setImageResource(R.drawable.checkin_record_up);
        mRecordImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyHabitDetails.this,PublishDynamicActivity.class);
                intent.putExtra("HABIT_NAME",currentHabit.getHabitName());
                startActivity(intent);
            }
        });

        growTxt1 = (TextView) findViewById(R.id.grow_txt_1);
        growTxt2 = (TextView) findViewById(R.id.grow_txt_2);
        growTxt3 = (TextView) findViewById(R.id.grow_txt_3);
        growTxt4 = (TextView) findViewById(R.id.grow_txt_4);
        growTxt5 = (TextView) findViewById(R.id.grow_txt_5);
        growTxt6 = (TextView) findViewById(R.id.grow_txt_6);
        growTxt7 = (TextView) findViewById(R.id.grow_txt_7);

        growImg1 = (ImageView) findViewById(R.id.grow_img_1);
        growImg2 = (ImageView) findViewById(R.id.grow_img_2);
        growImg3 = (ImageView) findViewById(R.id.grow_img_3);
        growImg4 = (ImageView) findViewById(R.id.grow_img_4);
        growImg5 = (ImageView) findViewById(R.id.grow_img_5);
        growImg6 = (ImageView) findViewById(R.id.grow_img_6);
        growImg7 = (ImageView) findViewById(R.id.grow_img_7);

        week = DateStrUtil.getWeek(new Date());
        initWeekStatus(week);

        //初始化签到控件的状态
        getHabitStatus();
        //设置签到控件的点击事件
        checkImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("currentData",currentData.getHabitName() + currentData.isFinished());
                checkStatus(currentData);
            }
        });

        //生长统计布局
        mLinGrow = (LinearLayout) findViewById(R.id.habit_details_grow_lin);

        mListView = (ListViewForScrollView) findViewById(R.id.habit_details_listview);
        //初始化动态数组，并绑定适配器
        initDynamics();

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
        getHabitDynamic();
    }

    /**
     * 初始化签到控件的状态
     */
    private void getHabitStatus() {
        mDlgLoading.show("状态获取中...");
        BmobQuery<UserAndHabit> userHabitQuery = new BmobQuery<UserAndHabit>();
        userHabitQuery.addWhereEqualTo("habitName",currentHabit.getHabitName());
        userHabitQuery.addWhereEqualTo("userAccount", SharePreferencesUtil.getUserAccount(this));
        userHabitQuery.findObjects(new FindListener<UserAndHabit>() {
            @Override
            public void done(List<UserAndHabit> list, BmobException e) {
                if(e == null) {
                    if(list != null && list.size() != 0) {
                        if(list.get(0).isFinished()) {
                            checkImgBtn.setImageResource(R.drawable.check_in);
                            mRecordImg.setVisibility(View.VISIBLE);
                            setGrowChecked(week);
                            isFinished = true;
                        } else {
                            checkImgBtn.setImageResource(R.drawable.check_out);
                            mRecordImg.setVisibility(View.INVISIBLE);
                            initWeekStatus(week);
                            isFinished = false;
                        }
                        currentData = list.get(0);
                    }
                } else {
                    ToastUtil.toastShow(getApplicationContext(),"您尚未加入此习惯！");
                }
                mDlgLoading.dismiss();
            }
        });
    }

    /**
     * 根据是否已经签到进行签到数据更改
     * @param userAndHabit
     */
    private void checkStatus(UserAndHabit userAndHabit) {
        String todayStr = DateStrUtil.changeDateToStr(DateStrUtil.MIN_FORMAT,new Date());
        Log.d("sqchen","今天是：" + todayStr);
        //如果还没签到，则改为已经签到，并且播放动画
        if(!isFinished) {
            changeHabitStatus(userAndHabit,true,todayStr);
            //设置周视图选中状态
            setGrowChecked(week);
            mProgressView.startUpdate(checkImgBtn,mFrameLayout,mRecordImg,currentHabit.getHabitName());
            isFinished = true;
        } else{
            changeHabitStatus(userAndHabit,false,userAndHabit.getFinishedTime());
            mProgressView.cancelUpdate(checkImgBtn,mFrameLayout,mRecordImg);
            initWeekStatus(week);
            isFinished = false;
        }
    }

    /**
     * 更新签到数据
     * @param userAndHabit
     * @param isFinished
     * @param todayStr
     */
    private void changeHabitStatus(UserAndHabit userAndHabit, final boolean isFinished, String todayStr) {
        userAndHabit.setValue("isFinished",isFinished);
        userAndHabit.setValue("finishedTime",todayStr);
        userAndHabit.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null) {
                    Log.d("sqchen","已更新isFinished为" + isFinished);
                } else{
                    Log.d("sqchen","更新isFinished为true失败");
                }
            }
        });
    }

    /**
     * 设置周视图的对应week的图片为选中状态
     * @param week
     */
    private void setGrowChecked(int week) {
        initWeekStatus(week);
        switch(week) {
            case 0:
                growImg7.setImageResource(R.drawable.check_in);
                break;
            case 1:
                growImg1.setImageResource(R.drawable.check_in);
                break;
            case 2:
                growImg2.setImageResource(R.drawable.check_in);
                break;
            case 3:
                growImg3.setImageResource(R.drawable.check_in);
                break;
            case 4:
                growImg4.setImageResource(R.drawable.check_in);
                break;
            case 5:
                growImg5.setImageResource(R.drawable.check_in);
                break;
            case 6:
                growImg6.setImageResource(R.drawable.check_in);
                break;
        }
    }


    /**
     * 初始化星期几的文本为绿色，如今天是星期五，则“五”为绿色
     * @param week
     */
    private void initWeekStatus(int week) {
        //先重置，再初始化文本，过程中其实已经包含了设置周视图的图片为未选中状态的过程
        resetGrowStatus();
        switch(week) {
            case 0:
                growTxt7.setTextColor(getResources().getColor(R.color.green));
                break;
            case 1:
                growTxt1.setTextColor(getResources().getColor(R.color.green));
                break;
            case 2:
                growTxt2.setTextColor(getResources().getColor(R.color.green));
                break;
            case 3:
                growTxt3.setTextColor(getResources().getColor(R.color.green));
                break;
            case 4:
                growTxt4.setTextColor(getResources().getColor(R.color.green));
                break;
            case 5:
                growTxt5.setTextColor(getResources().getColor(R.color.green));
                break;
            case 6:
                growTxt6.setTextColor(getResources().getColor(R.color.green));
                break;
        }
    }

    /**
     * 重置周签到视图状态
     */
    private void resetGrowStatus() {
        growImg1.setImageResource(R.drawable.check_out);
        growImg2.setImageResource(R.drawable.check_out);
        growImg3.setImageResource(R.drawable.check_out);
        growImg4.setImageResource(R.drawable.check_out);
        growImg5.setImageResource(R.drawable.check_out);
        growImg6.setImageResource(R.drawable.check_out);
        growImg7.setImageResource(R.drawable.check_out);

        growTxt1.setTextColor(getResources().getColor(R.color.text_grey_light));
        growTxt2.setTextColor(getResources().getColor(R.color.text_grey_light));
        growTxt3.setTextColor(getResources().getColor(R.color.text_grey_light));
        growTxt4.setTextColor(getResources().getColor(R.color.text_grey_light));
        growTxt5.setTextColor(getResources().getColor(R.color.text_grey_light));
        growTxt6.setTextColor(getResources().getColor(R.color.text_grey_light));
        growTxt7.setTextColor(getResources().getColor(R.color.text_grey_light));
    }

    /**
     * 加载该习惯的所有动态
     */
    private void getHabitDynamic() {
        BmobQuery<Dynamic> dynamiQuery = new BmobQuery<Dynamic>();
        dynamiQuery.addWhereEqualTo("habitName",currentHabit.getHabitName());
        dynamiQuery.findObjects(new FindListener<Dynamic>() {
            @Override
            public void done(List<Dynamic> list, BmobException e) {
                if(e == null) {
                    for(Dynamic dynamic : list) {
                        mDynamicList.add(dynamic);
                    }
                    mAdapter = new DynamicListAdapter(MyHabitDetails.this,mDynamicList,false,false);
                    mListView.setAdapter(mAdapter);
                }
            }
        });
    }

}
