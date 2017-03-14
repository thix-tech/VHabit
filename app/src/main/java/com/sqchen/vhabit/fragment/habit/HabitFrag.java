package com.sqchen.vhabit.fragment.habit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.MyHabitDetails;
import com.sqchen.vhabit.activity.NewHabitActivity;
import com.sqchen.vhabit.adapter.HabitListAdapter;
import com.sqchen.vhabit.bean.Habit;
import com.sqchen.vhabit.widget.CustomTitleView;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        //加载布局
        View rootView = inflater.inflate(R.layout.fragment_habit,container,false);

        //获取控件实例
        habitTitleView = (CustomTitleView) rootView.findViewById(R.id.frag_title_habit);
//        habitTitleView.setTxtLeft(getString(R.string.frag_title_left));
        habitTitleView.setTxtCenter(getString(R.string.frag_title_center));
        habitTitleView.setImgRight(R.drawable.ic_banner_add_habit);
        habitTitleView.setImgRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewHabitActivity.class);
                startActivity(intent);
            }
        });

        //初始化习惯数组
        mDataList.add(new Habit(R.drawable.tree_seed,"每天都想一下自己想要什么","已坚持10天"));
        mDataList.add(new Habit(R.drawable.tree_seed,"对一天进行回顾","已坚持3天"));
        mDataList.add(new Habit(R.drawable.tree_seed,"不随便发火，控制情绪","已坚持5天"));
        mDataList.add(new Habit(R.drawable.tree_seed,"Android","已坚持2天"));
        mDataList.add(new Habit(R.drawable.tree_seed,"多喝水","已坚持6天"));
        mDataList.add(new Habit(R.drawable.tree_seed,"每天看书一小时","已坚持1天"));
        mDataList.add(new Habit(R.drawable.tree_seed,"跑步","已坚持1天"));
        mDataList.add(new Habit(R.drawable.tree_seed,"戒烟：我能","已坚持2天"));

        mHabitListView = (ListView) rootView.findViewById(R.id.lv_habit);
        mListAdapter = new HabitListAdapter(getContext(),mDataList);
        mHabitListView.setAdapter(mListAdapter);
        mHabitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), MyHabitDetails.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
