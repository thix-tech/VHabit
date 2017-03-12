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
public class LearningFrag extends Fragment {


    private ListView mListView;

    private NewHabitListAdapter mAdapter;

    private List<Habit> mHabitList;

    public LearningFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_learning, container, false);

        initData();
        mListView = (ListView) rootView.findViewById(R.id.new_learn_list_view);
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
        mHabitList.add(new Habit(R.drawable.tree_seed,"画画",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天看书一小时",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"记手账",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"摄影",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"左手练字",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"舒尔特训练",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"100天彩绘曼陀罗",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天拍张照片",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"擂文",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天点评一个APP",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"TED",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"简笔画",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天练字一小时",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"背单词",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天练唱一首歌",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"坚持写日记",785255));
    }

}
