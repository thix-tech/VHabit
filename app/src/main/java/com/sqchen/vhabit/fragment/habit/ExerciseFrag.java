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
public class ExerciseFrag extends Fragment {

    private ListView mListView;

    private NewHabitListAdapter mAdapter;

    private List<Habit> mHabitList;

    public ExerciseFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);

        initData();
        mListView = (ListView) rootView.findViewById(R.id.new_exercise_list_view);
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
        mHabitList.add(new Habit(R.drawable.tree_seed,"PLANK",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"练瑜伽",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"瘦腿",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天练健美操",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"跑步",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天健身一小时",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"俯卧撑",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"仰卧起坐",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天深蹲100下",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天运动",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"练腹肌",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"睡前瘦腿",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"晚上跑步",785255));
    }

}
