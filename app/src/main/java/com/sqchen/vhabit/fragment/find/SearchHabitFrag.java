package com.sqchen.vhabit.fragment.find;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.HabitDetailsActivity;
import com.sqchen.vhabit.bean.Habit;
import com.sqchen.vhabit.util.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchHabitFrag extends Fragment {

    private LinearLayout mHabitLin1;

    private LinearLayout mHabitLin2;

    private ImageView mHabitIcon1;

    private ImageView mHabitIcon2;

    private TextView mHabitName1;

    private TextView mHabitName2;

    private TextView mHabitNum1;

    private TextView mHabitNum2;

    private List<Habit> mHabitList;

    public SearchHabitFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_habit, container, false);

        initData();

        mHabitLin1 = (LinearLayout) rootView.findViewById(R.id.lin_hot_habit_1);
        mHabitLin2 = (LinearLayout) rootView.findViewById(R.id.lin_hot_habit_2);
        mHabitIcon1 = (ImageView) rootView.findViewById(R.id.habit_icon_1);
        mHabitIcon2 = (ImageView) rootView.findViewById(R.id.habit_icon_2);
        mHabitName1 = (TextView) rootView.findViewById(R.id.habit_name_1);
        mHabitName2 = (TextView) rootView.findViewById(R.id.habit_name_2);
        mHabitNum1 = (TextView) rootView.findViewById(R.id.habit_num_1);
        mHabitNum2 = (TextView) rootView.findViewById(R.id.habit_num_2);

        mHabitLin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HabitDetailsActivity.class);
                startActivity(intent);
            }
        });

        mHabitLin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),HabitDetailsActivity.class);
                startActivity(intent);
            }
        });
        mHabitIcon1.setImageBitmap(BitmapUtil.readBitmap(getContext(),mHabitList.get(0).getHabitImageId()));
        mHabitName1.setText(mHabitList.get(0).getHabitName());
        mHabitNum1.setText(mHabitList.get(0).getSelectedNum() + getString(R.string.str_selected_num));

        mHabitIcon2.setImageBitmap(BitmapUtil.readBitmap(getContext(),mHabitList.get(1).getHabitImageId()));
        mHabitName2.setText(mHabitList.get(1).getHabitName());
        mHabitNum2.setText(mHabitList.get(1).getSelectedNum() + getString(R.string.str_selected_num));

        return rootView;
    }

    private void initData() {
        mHabitList = new ArrayList<Habit>();
        mHabitList.add(new Habit(R.drawable.tree_seed,"美食",5552));
        mHabitList.add(new Habit(R.drawable.tree_seed,"女子力提升",283));
    }


}
