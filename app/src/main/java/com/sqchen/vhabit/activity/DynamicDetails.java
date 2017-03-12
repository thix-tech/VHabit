package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.beans.Dynamic;
import com.sqchen.vhabit.utils.BitmapUtil;
import com.sqchen.vhabit.widgets.CustomTitleView;

public class DynamicDetails extends Activity {

    private CustomTitleView mTitleView;

    private Intent mIntent;

    private ImageView mUserIcon;

    private TextView mUserName;

    private TextView mPublishTime;

    private TextView mHabit;

    private TextView mDuration;

    private ImageView mDynamicImg;

    private TextView mDynamicTxt;

    private TextView mLikedNum;

    private TextView mCommentNum;

    private LinearLayout mLinLiked;

    private Dynamic mDynamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_details);

        initData();
        initViews();
    }

    private void initViews() {

        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("详情");
        mTitleView.setTxtRight("收藏");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mUserIcon = (ImageView) findViewById(R.id.dynamic_details_icon);
        mUserName = (TextView) findViewById(R.id.dynamic_details_user_name);
        mPublishTime = (TextView) findViewById(R.id.dynamic_details_publish_time);
        mHabit = (TextView) findViewById(R.id.dynamic_details_habit);
        mDuration = (TextView) findViewById(R.id.dynamic_details_duration);
        mDynamicImg = (ImageView) findViewById(R.id.dynamic_details_img);
        mDynamicTxt = (TextView) findViewById(R.id.dynamic_details_text);
        mLikedNum = (TextView) findViewById(R.id.dynamic_details_liked_num);
        mCommentNum = (TextView) findViewById(R.id.dynamic_details_comment_num);
        mLinLiked = (LinearLayout) findViewById(R.id.dynamic_details_lin_liked);

        mUserIcon.setImageResource(mDynamic.getUserIconId());
        mUserName.setText(mDynamic.getUserName());
        mPublishTime.setText(mDynamic.getPublishTimeStr());
        mDuration.setText(mDynamic.getDurationStr());

        //如果有动态主题图片，则显示图片布局
        if(mDynamic.getDynamicImgId() != 0) {
            mDynamicImg.setVisibility(View.VISIBLE);
            mDynamicImg.setImageBitmap(BitmapUtil.readBitmap(this,mDynamic.getDynamicImgId()));
        } else {    //否则，隐藏图片布局
            mDynamicImg.setVisibility(View.GONE);
        }
        mDynamicTxt.setText(mDynamic.getDynamicTxt());
        //如果有人点赞，则显示点赞布局
        if(mDynamic.getLikedUsers() != null) {
            mLinLiked.setVisibility(View.VISIBLE);
            mLikedNum.setText(String.valueOf(mDynamic.getLikedUsers().size()));
        } else {    //否则，隐藏点赞布局
            mLinLiked.setVisibility(View.GONE);
            mLikedNum.setText("0");
        }
        mCommentNum.setText(String.valueOf(mDynamic.getCommentNum()));

        mHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HabitDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取点击项的动态实体对象
     */
    private void initData() {
        mIntent = getIntent();
        mDynamic = (Dynamic) mIntent.getSerializableExtra("selectedDynamic");
    }

}
