package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.DynamicListAdapter;
import com.sqchen.vhabit.bean.Dynamic;
import com.sqchen.vhabit.bean.Habit;
import com.sqchen.vhabit.bean.User;
import com.sqchen.vhabit.bean.UserAndHabit;
import com.sqchen.vhabit.util.SharePreferencesUtil;
import com.sqchen.vhabit.util.ToastUtil;
import com.sqchen.vhabit.widget.CustomTitleView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class HabitDetailsActivity extends Activity {

    private CustomTitleView mTitleView;

    private ListView mListView;

    private List<Dynamic> mDynamicList;

    private DynamicListAdapter mAdapter;

    //加入该习惯按钮
    private Button btnAddHabit;

    //加入该习惯布局
    private LinearLayout linAddHabit;

    //从HabitFrag传递过来的Intent
    private Intent mIntent = null;

    //当前查看的习惯
    private Habit selectHabit;

    //“我”是否选择了该习惯
    private boolean isSelected;

    //坚持该习惯的人数
    private TextView txtSelect;

    //显示当前习惯名称
    private TextView txtHabitName;

    //用于存储坚持该习惯的人数
    private int selectNum;

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
        mTitleView.setTxtCenter(selectHabit.getHabitName());
        mTitleView.setImgRight(R.drawable.daren_bang);
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        linAddHabit = (LinearLayout) findViewById(R.id.lin_add_habit);
        btnAddHabit = (Button) findViewById(R.id.btn_add_habit);
        //显示当前习惯名称
        txtHabitName = (TextView) findViewById(R.id.txt_select_habit_name);
        txtHabitName.setText(selectHabit.getHabitName());
        //显示当前习惯坚持人数
        txtSelect = (TextView) findViewById(R.id.txt_select_num);
        getSelectNum();

        //判断是不是我的习惯
        isMyHabit();
        btnAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加入习惯
                selectHabit();
            }
        });
        mListView = (ListView) findViewById(R.id.habit_details_list_view);
        getHabitDynamic();

    }

    private void initData() {
        mIntent = getIntent();
        selectHabit = (Habit) mIntent.getSerializableExtra("SELECT_HABIT");
        mDynamicList = new ArrayList<Dynamic>();
    }


    /**
     * 加载坚持该习惯的人数
     */
    private void getSelectNum() {
        BmobQuery<UserAndHabit>  userHabitQuery = new BmobQuery<UserAndHabit>();
        userHabitQuery.addWhereEqualTo("habitName",selectHabit.getHabitName());
        userHabitQuery.findObjects(new FindListener<UserAndHabit>() {
            @Override
            public void done(List<UserAndHabit> list, BmobException e) {
                if(e == null) {
                    selectNum = list.size();
                    txtSelect.setText(selectNum + "人在坚持");
                    Log.d("SELECT_NUM","坚持人数个数：" + String.valueOf(selectNum));
                } else {
                    selectNum = 0;
                    txtSelect.setText(selectNum + "人在坚持");
                }
            }
        });
    }


    /**
     * 判断是否是我加入的习惯
     * @return
     */
    private boolean isMyHabit() {
        BmobQuery<UserAndHabit> userHabitQuery = new BmobQuery<UserAndHabit>();
        userHabitQuery.addWhereEqualTo("userAccount", SharePreferencesUtil.getUserAccount(this));
        userHabitQuery.addWhereEqualTo("habitName",selectHabit.getHabitName());
        userHabitQuery.findObjects(new FindListener<UserAndHabit>() {
            @Override
            public void done(List<UserAndHabit> list, BmobException e) {
                if(e == null) {
                    if(list.size() == 1) {
                        //如果是我的习惯，则隐藏布局
                        isSelected = true;
                        linAddHabit.setVisibility(View.GONE);
                    } else { //否则显示布局
                        isSelected = false;
                        linAddHabit.setVisibility(View.VISIBLE);
                    }
                } else {
                    ToastUtil.toastShow(getApplicationContext(),"加入习惯失败!");
                }
            }
        });
        return isSelected;
    }

    /**
     * 加入习惯
     */
    private void selectHabit() {
        if(isSelected)
            return;
        UserAndHabit userAndHabit = new UserAndHabit();
        userAndHabit.setUserAccount(SharePreferencesUtil.getUserAccount(this));
        userAndHabit.setHabitName(selectHabit.getHabitName());
        userAndHabit.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null) {
                    //更新该习惯的坚持人数
                    getHabitId();
                    linAddHabit.setVisibility(View.GONE);
                } else {
                    ToastUtil.toastShow(getApplicationContext(),"加入失败！");
                    linAddHabit.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 更新坚持该习惯的人数数据
     */
    private void getHabitId() {
        BmobQuery<Habit>  habit = new BmobQuery<Habit>();
        Log.d("SELECT_NUM","当前选择的习惯：" + selectHabit.getHabitName());
        habit.addWhereEqualTo("habitName",selectHabit.getHabitName());
        habit.findObjects(new FindListener<Habit>() {
            @Override
            public void done(List<Habit> list, BmobException e) {
                if(e == null) {
                    Log.d("SELECT_NUM","该习惯个数:" + list.size());
                    if(list.size() == 1) {
                        //将习惯的objectId传入，作为修改坚持人数的条件，因为Bmob只支持objectId的条件修改
                        updateSelectNum(list.get(0).getObjectId());
                    } else {
                        ToastUtil.toastShow(getApplicationContext(),"该习惯不存在！");
                    }
                } else {
                    ToastUtil.toastShow(getApplicationContext(),"查询习惯数据失败！");
                }
            }
        });
    }

    /**
     * 修改习惯坚持人数
     * @param habitId
     */
    private void updateSelectNum(String habitId) {
        Log.d("SELECT_NUM","坚持人数在我加入之前的个数：" + String.valueOf(selectNum));
        //修改之前再加载一次当前坚持人数的数据，因为在点击到当前页面和点击“加入”之间可能会有新增数据
        getSelectNum();
        Habit habit = new Habit();
        //将最新的selectNum + 1传入
        habit.setValue("selectedNum",selectNum + 1);
        Log.d("SELECT_NUM","坚持人数在我加入之后的个数：" + String.valueOf(selectNum + 1));
        habit.update(habitId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null) {
                    ToastUtil.toastShow(getApplicationContext(),"加入成功！");
                } else {
                    ToastUtil.toastShow(getApplicationContext(),"加入失败！");
                }
            }
        });
    }

    /**
     * 加载该习惯的所有动态
     */
    private void getHabitDynamic() {
        BmobQuery<Dynamic> dynamiQuery = new BmobQuery<Dynamic>();
        dynamiQuery.addWhereEqualTo("habitName",selectHabit.getHabitName());
        dynamiQuery.findObjects(new FindListener<Dynamic>() {
            @Override
            public void done(List<Dynamic> list, BmobException e) {
                if(e == null) {
                    for(Dynamic dynamic : list) {
                        mDynamicList.add(dynamic);
                    }
                    mAdapter = new DynamicListAdapter(HabitDetailsActivity.this,mDynamicList,false,true);
                    mListView.setAdapter(mAdapter);
                }
            }
        });
    }
}
