package com.sqchen.vhabit.fragment.habit;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

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

        mListView = (ListView) rootView.findViewById(R.id.new_hot_list_view);
        initData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), HabitDetailsActivity.class);
                intent.putExtra("SELECT_HABIT",mHabitList.get(position));
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void initData() {
        mHabitList = new ArrayList<Habit>();
        BmobQuery<Habit> habitQuery = new BmobQuery<Habit>();
        habitQuery.findObjects(new FindListener<Habit>() {
            @Override
            public void done(List<Habit> list, BmobException e) {
                if(e == null) {
                    for(Habit habit : list) {
                        mHabitList.add(habit);
                    }
                    mAdapter = new NewHabitListAdapter(getContext(),mHabitList);
                    mListView.setAdapter(mAdapter);
                    Log.d("sqchen","成功获取所有习惯！");
                } else {
                    Log.d("sqchen","获取所有习惯失败！");
                }
            }
        });
    }

}
