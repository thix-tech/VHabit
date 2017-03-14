package com.sqchen.vhabit.fragment.personal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.HistoryReviewActivity;
import com.sqchen.vhabit.adapter.ByHabitListAdapter;
import com.sqchen.vhabit.bean.Dynamic;
import com.sqchen.vhabit.widget.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ByHabitFrag extends Fragment {
    //列表框
    private ListViewForScrollView mListView;

    //适配器
    private ByHabitListAdapter mAdapter;

    //动态数组
    private List<Dynamic> mDynamicList;


    public ByHabitFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载布局
        View rootView = inflater.inflate(R.layout.fragment_by_habit, container, false);
        //获取控件实例
        mListView = (ListViewForScrollView) rootView.findViewById(R.id.personal_habit_list_view);

        //初始化动态数组
        initDynamics();
        mAdapter = new ByHabitListAdapter(getContext(),mDynamicList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), HistoryReviewActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    //初始化动态数组
    private void initDynamics() {
        mDynamicList = new ArrayList<Dynamic>();
        mDynamicList.add(new Dynamic("跑步","2015 10.23","2017 03.03","1"));
        mDynamicList.add(new Dynamic("每天看书一小时","2015 10.23","2017 03.03","1"));
        mDynamicList.add(new Dynamic("Android","2015 10.23","2017 03.03","2"));
        mDynamicList.add(new Dynamic("控制情绪","2015 10.23","2017 03.03","5"));
        mDynamicList.add(new Dynamic("多喝水","2017 02.22","2017 03.03","6","今天咳嗽好一点了" +
                "。现在应该说是昨天了。昨天姐姐生日。姐姐长成了大人，三个孩子的母亲。生日快乐。","2017.02.24"));
        mDynamicList.add(new Dynamic("每天想一下自己想要什么","2017 02.22","2017 03.03","8","想要让家人过上更好的生活，" +
                "想要一个还算不错的自己。","2017.02.24"));
        mDynamicList.add(new Dynamic("戒烟：我能","2017 02.22","2017 03.03","2","3根。","2017.02.28"));
        mDynamicList.add(new Dynamic("对一天进行回顾","2017 02.24","2017 03.03","3","在游戏里发生不愉快是一件很愚蠢的事。","2017.02.24"));
    }

}
