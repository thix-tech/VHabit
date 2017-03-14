package com.sqchen.vhabit.bean;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * 用户实体类
 * Created by Administrator on 2017/3/2.
 */

public class User extends BmobObject implements Serializable{

    //用户头像id
    private int userIconId;

    //用户手机号
    private String userAccount;

    //用户密码
    private String userPassword;

    //用户名
    private String userName;

    //用户性别
    private String userSex;

    //用户生日
    private String userBirthStr;

    //用户个性签名
    private String signatureStr;

    //我关注的人
    private List<User> mFollowingList;

    //关注我的人
    private List<User> mFollowedList;

    //总的点赞量
    private int totalLikeNum;

    public User() {
    }

    public User(int id,String name,int likeNum){
        this.userIconId = id;
        this.userName = name;
        this.totalLikeNum = likeNum;
    }

    public User(String name) {
        this.userName = name;
    }

    public User(int iconId,String userName,String signatureStr) {
        this.userIconId = iconId;
        this.userName = userName;
        this.signatureStr = signatureStr;
    }

    public User(int iconId,String name,String sex,String birthStr,String signatureStr) {
        this.userIconId = iconId;
        this.userName = name;
        this.userSex = sex;
        this.userBirthStr = birthStr;
    }


    public int getUserIconId() {
        return userIconId;
    }

    public void setUserIconId(int userIconId) {
        this.userIconId = userIconId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public  String getUserSex() {
        return this.userSex;
    }

    public  void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserBirthStr() {
        return userBirthStr;
    }

    public void setUserBirthStr(String userBirthStr) {
        this.userBirthStr = userBirthStr;
    }

    public String getSignatureStr() {
        return signatureStr;
    }

    public void setSignatureStr(String signatureStr) {
        this.signatureStr = signatureStr;
    }

    public List<User> getFollowingList() {
        return mFollowingList;
    }

    public void setFollowingList(List<User> followingList) {
        mFollowingList = followingList;
    }

    public List<User> getFollowedList() {
        return mFollowedList;
    }

    public void setFollowedList(List<User> followedList) {
        mFollowedList = followedList;
    }

    public int getTotalLikeNum() {
        return totalLikeNum;
    }

    public void setTotalLikeNum(int totalLikeNum) {
        this.totalLikeNum = totalLikeNum;
    }

    public String getUserTel() {
        return userAccount;
    }

    public void setUserTel(String userTel) {
        this.userAccount = userTel;
    }


    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
