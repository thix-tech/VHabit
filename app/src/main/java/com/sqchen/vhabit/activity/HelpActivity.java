package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.widget.CustomTitleView;

public class HelpActivity extends Activity {

    private CustomTitleView mTitleView;

    private LinearLayout mLinProblems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        initViews();
    }

    private void initViews() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("帮助中心");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLinProblems = (LinearLayout) findViewById(R.id.lin_problems);
        //设置过渡动画
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_problems_item);
        //设置布局动画的显示属性
        LayoutAnimationController lac = new LayoutAnimationController(animation,0.3F);
        //设置布局动画子项显示顺序，有顺序、倒序和随机三种
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //为ViewGroup设置布局动画
        mLinProblems.setLayoutAnimation(lac);
    }
}
