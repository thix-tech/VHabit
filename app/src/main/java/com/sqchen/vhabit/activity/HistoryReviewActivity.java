package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.HistoryListAdapter;
import com.sqchen.vhabit.beans.Dynamic;
import com.sqchen.vhabit.widgets.CustomTitleView;

import java.util.ArrayList;
import java.util.List;

public class HistoryReviewActivity extends Activity {

    private CustomTitleView mTitleView;

    private List<Dynamic> mDynamicList;

    private ListView mListView;

    private HistoryListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_review);
        initData();
        initView();
    }

    private void initView() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("历史回顾");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mListView = (ListView) findViewById(R.id.history_list_view);
        mAdapter = new HistoryListAdapter(this,mDynamicList);
        mListView.setAdapter(mAdapter);

    }

    private void initData() {
        mDynamicList = new ArrayList<Dynamic>();
        mDynamicList.add(new Dynamic("多喝水","2017 02.22","2017 03.03","6","今天咳嗽好一点了" +
                "。现在应该说是昨天了。昨天姐姐生日。姐姐长成了大人，三个孩子的母亲。生日快乐。","2017.02.24"));
        mDynamicList.add(new Dynamic("每天想一下自己想要什么","2017 02.22","2017 03.03","8","想要让家人过上更好的生活，" +
                "想要一个还算不错的自己。","2017.02.24"));
        mDynamicList.add(new Dynamic("对一天进行回顾","2017 02.24","2017 03.03","3","在游戏里发生不愉快是一件很愚蠢的事。","2017.02.24"));
    }

}
