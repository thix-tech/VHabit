package com.sqchen.vhabit.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/15.
 */

public class UserAndHabit extends BmobObject implements Serializable{

    private String userAccount;

    private String habitName;

    private boolean isFinished;

    private String finishedTime;

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

}
