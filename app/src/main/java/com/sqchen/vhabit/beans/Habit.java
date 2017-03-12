package com.sqchen.vhabit.beans;

/**
 * 用户习惯实体类
 * Created by Administrator on 2017/2/28.
 */

public class Habit {
    //习惯图标
    private int habitImageId;

    //习惯名称
    private String habitName;

    //习惯坚持时间
    private String habitTimeTxt;

    //今日是否坚持习惯
    private boolean isFinished;

    private int selectedNum;

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
}
