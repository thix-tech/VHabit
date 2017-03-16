package com.sqchen.vhabit.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/15.
 */

public class UserAndHabit extends BmobObject implements Serializable{

    //坚持该习惯的用户账号
    private String userAccount;

    //习惯名称
    private String habitName;

    //记录今日是否签到
    private boolean isFinished;

    //记录最近一次签到时间
    private String finishedTime;

    //加入该习惯的开始时间
    private String habitBeginTime;

    //退出该习惯的结束时间
    private String habitOverTime;

    //习惯坚持天数
    private int durationNum;


    public UserAndHabit() {

    }
    public UserAndHabit(String habitName) {
        this.habitName = habitName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getHabitBeginTime() {
        return habitBeginTime;
    }

    public void setHabitBeginTime(String habitBeginTime) {
        this.habitBeginTime = habitBeginTime;
    }

    public String getHabitOverTime() {
        return habitOverTime;
    }

    public void setHabitOverTime(String habitOverTime) {
        this.habitOverTime = habitOverTime;
    }

    public int getDurationNum() {
        return durationNum;
    }

    public void setDurationNum(int durationNum) {
        this.durationNum = durationNum;
    }
}
