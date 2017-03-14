package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.widget.CustomTitleView;

public class BeHelperActivity extends Activity {

    private CustomTitleView mTitleView;

    private TextView mFrameTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_helper);
        initViews();
    }

    private void initViews() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("我是帮帮手");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFrameTips = (TextView) findViewById(R.id.frame_center_text);
        mFrameTips.setText("暂时无法申请成为帮帮手");
    }
}
