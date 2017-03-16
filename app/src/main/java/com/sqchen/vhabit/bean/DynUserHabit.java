package com.sqchen.vhabit.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/16.
 */

public class DynUserHabit extends BmobObject implements Serializable{

    //动态编号
    private String dynamicId;

    //动态所属习惯
    private String habitName;

    //动态所属用户账号
    private String userAccount;

    private String publishTimeStr;

    public DynUserHabit() {

    }

    public DynUserHabit(String habitName, String userAccount) {
        this.habitName = habitName;
        this.userAccount = userAccount;
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

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
    }
}
