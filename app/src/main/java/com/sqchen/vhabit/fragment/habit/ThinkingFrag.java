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
public class ThinkingFrag extends Fragment {

    private ListView mListView;

    private NewHabitListAdapter mAdapter;

    private List<Habit> mHabitList;

    public ThinkingFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_thinking, container, false);

        initData();
        mListView = (ListView) rootView.findViewById(R.id.new_think_list_view);
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
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天记录一件自己认为幸福的事",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天想一下自己想要什么",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"对一天进行回顾",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"不抱怨，不批评，不说闲话",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"Bible",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"一句话日记",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"思考自己的梦想",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天联系5个朋友",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"冥想",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"对一个陌生人微笑",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"对家里人态度耐心温和",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"不说脏话",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"保持正能量",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"偷偷喜欢你",785255));
        mHabitList.add(new Habit(R.drawable.tree_seed,"每天早上给自己一个微笑",785255));
    }
}
