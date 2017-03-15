package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.DynamicListAdapter;
import com.sqchen.vhabit.bean.Dynamic;
import com.sqchen.vhabit.widget.CustomTitleView;

import java.util.ArrayList;
import java.util.List;

public class HabitDetailsActivity extends Activity {

    private CustomTitleView mTitleView;

    private ListView mListView;

    private List<Dynamic> mDynamicList;

    private DynamicListAdapter mAdapter;

    private boolean isAddHabit = false;

    private Button btnAddHabit;

    private LinearLayout linAddHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_details);
        initData();
        initView();
    }

    private void initView() {

        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("对一天进行回顾");
        mTitleView.setImgRight(R.drawable.daren_bang);
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddHabit = (Button) findViewById(R.id.btn_add_habit);
        linAddHabit = (LinearLayout) findViewById(R.id.lin_add_habit);

        mListView = (ListView) findViewById(R.id.habit_details_list_view);
        mAdapter = new DynamicListAdapter(HabitDetailsActivity.this,mDynamicList,false,true);
        mListView.setAdapter(mAdapter);

    }

    private void initData() {
        mDynamicList = new ArrayList<Dynamic>();
        mDynamicList.add(new Dynamic(R.drawable.user_1,"我是柠檬君","#对一天进行回顾#","今天 12:53","11天",
                0,"我想要你",3));
        mDynamicList.add(new Dynamic(R.drawable.user_5,"隐进盛夏的两脚兽","#对一天进行回顾#","2月26日","18天",
                0,"红菜心开花了，田野的气息",3));
        mDynamicList.add(new Dynamic(R.drawable.user_3,"南木","#对一天进行回顾#","昨天 11:14","289天",
                0,"大通道真的好美",3));
        mDynamicList.add(new Dynamic(R.drawable.user_1,"我是柠檬君","#对一天进行回顾#","昨天 20:14","291天",
                0,"奥地利表现主义；毕加索与勃拉克之后的巴黎立体主义；意大利未来主义。各国艺术的线代主义化，" +
                "我觉得这些作品并不需要用来理解，只能用来感受。",3));
    }


}
