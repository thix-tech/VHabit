package com.sqchen.vhabit.fragment.personal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.widgets.CustomTitleView;

public class ShareVHabit extends Activity {

    private CustomTitleView mTitleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_vhabit);

        initView();
    }

    private void initView() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("邀请好友");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
