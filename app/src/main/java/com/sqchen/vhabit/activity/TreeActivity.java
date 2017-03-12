package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.widgets.CustomTitleView;

public class TreeActivity extends Activity {

    private CustomTitleView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);
        initView();
    }

    private void initView() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setTxtCenter("对一天进行回顾");
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
