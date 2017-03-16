package com.sqchen.vhabit.bean;

import cn.bmob.v3.BmobObject;

/**
 * 用户习惯实体类
 * Created by Administrator on 2017/2/28.
 */

public class Habit extends BmobObject{
    //习惯图标
    private int habitImageId = 0;

    //习惯名称
    private String habitName;

    //习惯坚持时间
    private String habitTimeTxt;

    //今日是否坚持习惯
    private boolean isFinished;

    //选择该习惯的人
    private int selectedNum;

    //该习惯的开始时间
    private String habitBeginTime;

    //该习惯的结束时间
    private String habitOverTime;

    public Habit() {

    }

    public Habit(String name) {
        this.habitName = name;
    }

    public Habit(int id, String name, String timeTxt) {
        this.habitImageId = id;
        this.habitName = name;
        this.habitTimeTxt = timeTxt;
    }

    public Habit(int id,String name,int selectedNum) {
        this.habitImageId = id;
        this.habitName = name;
        this.selectedNum = selectedNum;
    }

    public int getHabitImageId() {
        return habitImageId;
    }

    public void setHabitImageId(int habitImageId) {
        this.habitImageId = habitImageId;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getHabitTimeTxt() {
        return habitTimeTxt;
    }

    public void setHabitTimeTxt(String habitTimeTxt) {
        this.habitTimeTxt = habitTimeTxt;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public int getSelectedNum() {
        return selectedNum;
    }

    public void setSelectedNum(int selectedNum) {
        this.selectedNum = selectedNum;
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
}
