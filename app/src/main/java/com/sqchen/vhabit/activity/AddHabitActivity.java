package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.bean.Habit;
import com.sqchen.vhabit.bean.UserAndHabit;
import com.sqchen.vhabit.util.ToastUtil;
import com.sqchen.vhabit.widget.CustomTitleView;
import com.sqchen.vhabit.widget.DlgLoading;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddHabitActivity extends Activity {

    private CustomTitleView mTitleView;

    private EditText editHabitName;

    private String strEditHabit;

    private DlgLoading mDlgLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        initView();
    }

    private void initView() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleView.setTxtCenter("创建习惯");
        mTitleView.setTxtRight("完成");
        mTitleView.setTxtRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewHabit();
            }
        });

        mDlgLoading = new DlgLoading(this);
        editHabitName = (EditText) findViewById(R.id.edit_add_habit_name);
    }

    private void addNewHabit() {
        mDlgLoading.show("正在创建习惯...");
        getInput();
        //保存数据到Hbait表中
        Habit habit = new Habit();
        habit.setHabitName(strEditHabit);
        habit.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null) {
                    ToastUtil.toastShow(getApplicationContext(),"习惯创建成功！");
                    mDlgLoading.dismiss();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    ToastUtil.toastShow(getApplicationContext(),"该习惯已存在！");
                    mDlgLoading.dismiss();
                }
            }
        });

        //取值
        SharedPreferences pref = getSharedPreferences("userInfo",MODE_PRIVATE);
        String strAccount = pref.getString("userAccount","");
        //保存数据到UserAndHabit表中
        UserAndHabit userHabit = new UserAndHabit();
        userHabit.setHabitName(strEditHabit);
        userHabit.setUserAccount(strAccount);
        userHabit.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null) {
                    Log.d("sqchen","已添加数据到用户习惯表！");
                } else {
                    Log.d("sqchen","添加数据到用户习惯表失败！");
                }
            }
        });
    }

    private void getInput() {
        strEditHabit = editHabitName.getText().toString();
    }

}
