package com.sqchen.vhabit.activity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.fragment.find.FindFrag;
import com.sqchen.vhabit.fragment.habit.HabitFrag;
import com.sqchen.vhabit.fragment.message.MessageFrag;
import com.sqchen.vhabit.fragment.personal.PersonalFrag;
import com.sqchen.vhabit.util.MyDatabaseHelper;

/**
 * 主界面Activity，用于承载四个fragment
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener{

    /**
     * 四个fragment进行切换
     */
    private HabitFrag mHabitFrag;

    private FindFrag mFindFrag;

    private MessageFrag mMessageFrag;

    private PersonalFrag mPersonalFrag;

    /**
     * 底部四个tab切换按钮
     */
    private RelativeLayout mHabitBtn;

    private RelativeLayout mFindBtn;

    private RelativeLayout mMessageBtn;

    private RelativeLayout mPersonalBtn;

    private TextView tvHabit,tvFind,tvMessage,tvPersonal;
    private ImageView ivHabit,ivFind,ivMessage,ivPersonal;

    /**
     * 用于管理fragment
     */
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mManager = getSupportFragmentManager();
        setTabSelection(0);
//        outputLog();
    }

    private void initViews() {
        //获取底部栏文字的控件实例
        tvHabit = (TextView) findViewById(R.id.tv_habit);
        tvFind = (TextView) findViewById(R.id.tv_find);
        tvMessage = (TextView) findViewById(R.id.tv_message);
        tvPersonal = (TextView) findViewById(R.id.tv_personal);

        //获取底部栏图片的控件实例
        ivHabit = (ImageView) findViewById(R.id.iv_habit);
        ivFind = (ImageView) findViewById(R.id.iv_find);
        ivMessage = (ImageView) findViewById(R.id.iv_message);
        ivPersonal = (ImageView) findViewById(R.id.iv_personal);

        //获取底部栏四个tab区域的控件实例
        mHabitBtn = (RelativeLayout) findViewById(R.id.rel_habit);
        mFindBtn = (RelativeLayout) findViewById(R.id.rel_find);
        mMessageBtn = (RelativeLayout) findViewById(R.id.rel_message);
        mPersonalBtn = (RelativeLayout) findViewById(R.id.rel_personal);

        //设置监听器
        mHabitBtn.setOnClickListener(this);
        mFindBtn.setOnClickListener(this);
        mMessageBtn.setOnClickListener(this);
        mPersonalBtn.setOnClickListener(this);
    }

    /**
     * 设置点击事件
     * @param view
     *          触发点击事件的组件
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.rel_habit:
                setTabSelection(0);
                break;
            case R.id.rel_find:
                setTabSelection(1);
                break;
            case R.id.rel_message:
                setTabSelection(2);
                break;
            case R.id.rel_personal:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        //重置按钮
        resetBtn();
        //开启一个Fragment事务
        FragmentTransaction transaction = mManager.beginTransaction();
        //先隐藏掉所有的fragment，以防止有多个fragment重叠显示
        hideFragments(transaction);
        switch(index) {
            case 0:
                //改变为选中状态的图片和文字颜色
                ivHabit.setImageResource(R.drawable.habit_pressed);
                tvHabit.setTextColor(getResources().getColor(R.color.textPressed));
                //如果该fragment不存在，则创建并添加到界面上
                if(mHabitFrag == null) {
                    mHabitFrag = new HabitFrag();
                    transaction.add(R.id.content_layout,mHabitFrag);
                } else {
                    //否则，直接显示出来
                    transaction.show(mHabitFrag);
                }
                break;
            case 1:
                ivFind.setImageResource(R.drawable.find_pressed);
                tvFind.setTextColor(getResources().getColor(R.color.textPressed));
                if(mFindFrag == null) {
                    mFindFrag = new FindFrag();
                    transaction.add(R.id.content_layout,mFindFrag);
                } else{
                    transaction.show(mFindFrag);
                }
                break;
            case 2:
                ivMessage.setImageResource(R.drawable.message_pressed);
                tvMessage.setTextColor(getResources().getColor(R.color.textPressed));
                if(mMessageFrag == null) {
                    mMessageFrag = new MessageFrag();
                    transaction.add(R.id.content_layout,mMessageFrag);
                } else {
                    transaction.show(mMessageFrag);
                }
                break;
            case 3:
                ivPersonal.setImageResource(R.drawable.my_pressed);
                tvPersonal.setTextColor(getResources().getColor(R.color.textPressed));
                if(mPersonalFrag == null) {
                    mPersonalFrag = new PersonalFrag();
                    transaction.add(R.id.content_layout,mPersonalFrag);
                } else{
                    transaction.show(mPersonalFrag);
                }
                break;
            default:
                break;
        }
        //提交事务
        transaction.commit();
    }

    /**
     * 将所有选中状态清除掉，即改变底部栏文字颜色和图片
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if(mHabitFrag != null) {
            transaction.hide(mHabitFrag);
        }
        if(mFindFrag != null) {
            transaction.hide(mFindFrag);
        }
        if(mMessageFrag != null) {
            transaction.hide(mMessageFrag);
        }
        if(mPersonalFrag != null) {
            transaction.hide(mPersonalFrag);
        }
    }

    /**
     * 将底部栏文字和图片切换为未选中状态
     */
    private void resetBtn() {
        ivHabit.setImageResource(R.drawable.habit_normal);
        ivFind.setImageResource(R.drawable.find_normal);
        ivMessage.setImageResource(R.drawable.message_normal);
        ivPersonal.setImageResource(R.drawable.my_normal);

        tvHabit.setTextColor(getResources().getColor(R.color.textNormal));
        tvFind.setTextColor(getResources().getColor(R.color.textNormal));
        tvMessage.setTextColor(getResources().getColor(R.color.textNormal));
        tvPersonal.setTextColor(getResources().getColor(R.color.textNormal));
    }

}
