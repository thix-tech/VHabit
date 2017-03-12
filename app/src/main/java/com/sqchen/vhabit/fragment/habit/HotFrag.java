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
import com.sqchen.vhabit.activity.HabitDetailsActivity;
import com.sqchen.vhabit.adapter.NewHabitListAdapter;
import com.sqchen.vhabit.beans.Habit;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFrag extends Fragment {

    private ListView mListView;

    private NewHabitListAdapter mAdapter;

    private List<Habit> mHabitList;

    public HotFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hot, container, false);

        initData();
        mListView = (ListView) rootView.findViewById(R.id.new_hot_list_view);
        mAdapter = new NewHabitListAdapter(getContext(),mHabitList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), HabitDetailsActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void initData() {
        mHabitList = new ArrayList<Habit>();
        mHabitList.add(new Habit(R.drawable.tree_seed,"早起",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天看书一小时",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"画画",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"PLANK",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"早睡",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"吃早餐",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"跑步",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"多喝水",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"美食",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"减肥",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天拍张照片",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"一句话日记",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"学英语",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天运动",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"健身",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每日一图",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"摄影",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"坚持每天到VHabit签到",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"保持正能量",785255));
    }

}
