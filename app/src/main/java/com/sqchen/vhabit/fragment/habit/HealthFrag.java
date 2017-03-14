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
public class HealthFrag extends Fragment {

    private ListView mListView;

    private NewHabitListAdapter mAdapter;

    private List<Habit> mHabitList;

    public HealthFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_health, container, false);

        initData();
        mListView = (ListView) rootView.findViewById(R.id.new_health_list_view);
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
        mHabitList.add(new Habit(R.drawable.tree_seed,"吃早餐",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"不喝碳酸饮料",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"吃水果",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"戒烟：我能",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"早起",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天早上空腹喝喝水一杯",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"早睡",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"减肥",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"多喝水",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"不吃零食",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"不翘二郎腿",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"称体重",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"拒绝夜宵",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"睡前刷牙",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"挺直腰杆，不能驼背",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"睡午觉",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天晚上泡脚",785255));
    }

}
