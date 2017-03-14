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
import com.sqchen.vhabit.bean.Habit;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EfficiencyFrag extends Fragment {

    private ListView mListView;

    private NewHabitListAdapter mAdapter;

    private List<Habit> mHabitList;

    public EfficiencyFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_efficiency, container, false);

        initData();
        mListView = (ListView) rootView.findViewById(R.id.new_efficiency_list_view);
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
        mHabitList.add(new Habit(R.drawable.tree_seed,"记账",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"番茄时间",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"对一天进行回顾",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"战胜懒惰",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"GTD",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"拒绝万恶拖延症",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"12点之前睡觉",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"高效利用时间",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天早上做计划",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"今日事，今日毕",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天玩手机不超过1小时",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"存一块钱",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"不乱花钱",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"洗澡后快速洗掉小衣服",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"早上不赖床",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"保持房间整洁",785255));
    }
}
