package com.sqchen.vhabit.fragment.habit;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.MyHabitDetails;
import com.sqchen.vhabit.activity.NewHabitActivity;
import com.sqchen.vhabit.adapter.HabitListAdapter;
import com.sqchen.vhabit.bean.Habit;
import com.sqchen.vhabit.bean.UserAndHabit;
import com.sqchen.vhabit.util.SharePreferencesUtil;
import com.sqchen.vhabit.util.ToastUtil;
import com.sqchen.vhabit.widget.CustomTitleView;
import com.sqchen.vhabit.widget.DlgLoading;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/2/25.
 */

public class HabitFrag extends Fragment {
    //顶部标题栏控件
    private CustomTitleView habitTitleView;

    //习惯列表框控件
    private ListView mHabitListView;

    //习惯列表框适配器
    private HabitListAdapter mListAdapter;

    //习惯数组
    private List<Habit> mDataList = new ArrayList<>();

    //“用户习惯”数组
    private List<UserAndHabit> mUserAndHabits = new ArrayList<UserAndHabit>();

    private DlgLoading mDlgLoading;

    private ImageView mFinishedImg;

    //签到数组，用于存储listview每一项对应的习惯是否已经签到
    private Boolean[] isFinishedList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        //加载布局
        View rootView = inflater.inflate(R.layout.fragment_habit,container,false);

        //获取控件实例
        habitTitleView = (CustomTitleView) rootView.findViewById(R.id.frag_title_habit);
        habitTitleView.setTxtCenter(getString(R.string.frag_title_center));
        habitTitleView.setImgRight(R.drawable.ic_banner_add_habit);
        habitTitleView.setImgRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewHabitActivity.class);
                startActivity(intent);
            }
        });
        mDlgLoading = new DlgLoading(getContext());

        initData();

        mHabitListView = (ListView) rootView.findViewById(R.id.lv_habit);
        Log.d("user_habit","mDataList大小：" + mDataList.size() + "\n");


        mHabitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), MyHabitDetails.class);
                intent.putExtra("SELECT_HABIT",mDataList.get(position));
                startActivity(intent);
            }
        });

        return rootView;
    }

    /**
     * 初始化习惯数组数据
     */
    private void initData() {
        getUserHabitList();
    }

    /**
     * 初始化签到图标的状态
     */
    private void initImgStatus() {
        Log.d("sqchen","签到图标数组大小为：" + isFinishedList.length);
        for(int i = 0;i < isFinishedList.length;i++) {
            Log.d("sqchen","签到图标数组第"+ i +"项是：" + isFinishedList[i]);
            mFinishedImg = (ImageView) mHabitListView.getChildAt(i).findViewById(R.id.habit_is_finish);
            if(isFinishedList[i]) {
                mFinishedImg.setVisibility(View.VISIBLE);
            } else {
                mFinishedImg.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 先根据用户账号在UserAndHabit表中找到习惯
     */
    private void getUserHabitList() {
        mDlgLoading.show("数据加载中...");
        BmobQuery<UserAndHabit> userHabitQuery = new BmobQuery<UserAndHabit>();
        userHabitQuery.addWhereEqualTo("userAccount", SharePreferencesUtil.getUserAccount(getContext()));
        userHabitQuery.findObjects(new FindListener<UserAndHabit>() {
            @Override
            public void done(List<UserAndHabit> list, BmobException e) {
                if(e == null) {
                    //如果没有数据，直接返回
                    if(list.size() == 0) {
                        ToastUtil.toastShow(getContext(),"暂无数据");
                        mDlgLoading.dismiss();
                        return;
                    }
//                    isFinishedList = new Boolean[list.size()];
                    int i = 0;
                    for(UserAndHabit userAndHabit :list) {
                        //获取习惯数据
                        mUserAndHabits = list;
                        getHabit(userAndHabit);
//                        isFinishedList[i] = userAndHabit.isFinished();
//                        i++;
                    }
//                    initImgStatus();
                } else {
                    ToastUtil.toastShow(getContext(),"获取用户习惯数据失败！");
                }
            }
        });
    }

    /**
     *  再在表Habit中获取习惯数据
     * @param userAndHabit
     */
    private void getHabit(UserAndHabit userAndHabit) {
        BmobQuery<Habit> habitQuery = new BmobQuery<Habit>();
        habitQuery.addWhereEqualTo("habitName",userAndHabit.getHabitName());
        habitQuery.findObjects(new FindListener<Habit>() {
            @Override
            public void done(List<Habit> list, BmobException e) {
                Log.d("user_habit","习惯大小：" + list.size() + "\n");
                if(e == null) {
                    for(Habit habit : list) {
                        Log.d("user_habit","习惯名称：" + habit.getHabitName() + "\n");
                        mDataList.add(habit);
                    }
                    mListAdapter = new HabitListAdapter(getContext(),mDataList,mUserAndHabits);
                    mHabitListView.setAdapter(mListAdapter);
                    mDlgLoading.dismiss();
                } else {
                    ToastUtil.toastShow(getContext(),"获取习惯数据失败！");
                }
                mDlgLoading.dismiss();
            }
        });
    }

}
