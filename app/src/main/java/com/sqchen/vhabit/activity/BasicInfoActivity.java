package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.widget.CustomTitleView;

public class BasicInfoActivity extends Activity {

    private CustomTitleView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);

        mTitleView = (CustomTitleView) findViewById(R.id.basic_info_title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("基本资料");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
