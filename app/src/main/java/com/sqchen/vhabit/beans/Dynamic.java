package com.sqchen.vhabit.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 动态实体类，本项目所有注释文档里提到的“动态”，均指用户动态。
 * Created by Administrator on 2017/3/2.
 */

public class Dynamic implements Serializable{

    //用户ID
    private int userId;

    //用户名
    private String userName;

    //用户头像id
    private int userIconId;

    //用户坚持的习惯
    private String userHabit;

    //动态发表时间
    private String publishTimeStr;

    //习惯坚持时间
    private String durationStr;

    //动态主题图片
    private int dynamicImgId;

    //动态主题文字
    private String dynamicTxt;

    //点赞该动态的用户数组
    private List<User> likedUsers = null;

    //该动态的评论数量
    private int commentNum;

    //该习惯的开始时间
    private String habitBeginTime;

    //该习惯的结束时间
    private String habitOverTime;

    /**
     * 用于“热门”界面中热门动态里的习惯数据
     * @param iconId
     * @param name
     * @param habit
     * @param timeStr
     * @param durationStr
     * @param dynamicImgId
     * @param dynamicTxt
     * @param likedList
     * @param commentNum
     */
    public Dynamic(int iconId,String name,String habit,
                   String timeStr,String durationStr,
                   int dynamicImgId,String dynamicTxt,
                   List<User> likedList,int commentNum) {
        this.userIconId = iconId;
        this.userName = name;
        this.userHabit = habit;
        this.publishTimeStr = timeStr;
        this.durationStr = durationStr;
        this.dynamicImgId = dynamicImgId;
        this.dynamicTxt = dynamicTxt;
        this.likedUsers = likedList;
//        this.likeNum = this.likedUsers.size();
        this.commentNum = commentNum;
    }

    /**
     * 用于“我的”界面中我的无主题文字的动态的习惯数据
     * @param habit
     * @param beginTime
     * @param overTime
     * @param dayNum
     */
    public Dynamic(String habit,String beginTime,String overTime,String dayNum) {
        this.userHabit = habit;
        this.habitBeginTime = beginTime;
        this.habitOverTime = overTime;
        this.durationStr = dayNum;
    }

    /**
     * 用于“我的”界面中我的有主题文字的动态的习惯数据
     * @param habit
     * @param beginTime
     * @param overTime
     * @param dayNum
     */
    public Dynamic(String habit,String beginTime,String overTime,String dayNum,String dynamicText,String publishTimeStr) {
        this.userHabit = habit;
        this.habitBeginTime = beginTime;
        this.habitOverTime = overTime;
        this.durationStr = dayNum;
        this.dynamicTxt = dynamicText;
        this.publishTimeStr = publishTimeStr;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserIconId() {
        return userIconId;
    }

    public void setUserIconId(int userIconId) {
        this.userIconId = userIconId;
    }

    public String getUserHabit() {
        return userHabit;
    }

    public void setUserHabit(String userHabit) {
        this.userHabit = userHabit;
    }

    public String getPublishTimeStr() {
        return publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    public int getDynamicImgId() {
        return dynamicImgId;
    }

    public void setDynamicImgId(int dynamicImgId) {
        this.dynamicImgId = dynamicImgId;
    }

    public String getDynamicTxt() {
        return dynamicTxt;
    }

    public void setDynamicTxt(String dynamicTxt) {
        this.dynamicTxt = dynamicTxt;
    }

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<User> likedUsers) {
        this.likedUsers = likedUsers;
    }


    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
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
