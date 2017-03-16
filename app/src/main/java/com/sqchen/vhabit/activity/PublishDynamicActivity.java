package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.bean.DynUserHabit;
import com.sqchen.vhabit.bean.Dynamic;
import com.sqchen.vhabit.fragment.habit.HabitFrag;
import com.sqchen.vhabit.util.DateStrUtil;
import com.sqchen.vhabit.util.SharePreferencesUtil;
import com.sqchen.vhabit.util.ToastUtil;
import com.sqchen.vhabit.widget.CustomTitleView;

import java.util.Date;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PublishDynamicActivity extends Activity {

    private CustomTitleView mTitleView;

    private LinearLayout linAddPic;

    private ImageView mImageView;

    private static final int CROP_PHOTO = 1;

    private EditText editDynamicTxt;

    private String dynamicTxtStr = "";

    private Intent mIntent;

    private String habitName;

    private String publishTimeStr;

    private String userAccountStr;

    private String userNameStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_dynamic);
        initData();
        initView();
    }

    private void initData() {
        mIntent = getIntent();
        habitName = mIntent.getStringExtra("HABIT_NAME");
    }

    private void initView() {

        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("记录一下");
        mTitleView.setTxtRight("发布");
        mTitleView.setTxtRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishDynamic();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editDynamicTxt = (EditText) findViewById(R.id.edit_dynamic_txt);

        mImageView = (ImageView) findViewById(R.id.add_dynamic_img);
        linAddPic = (LinearLayout) findViewById(R.id.lin_add_pic);
        linAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,CROP_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case CROP_PHOTO:
                if(resultCode == RESULT_OK) {

                    //参考链接：https://www.oschina.net/question/157182_53236
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    mImageView.setVisibility(View.VISIBLE);
                    linAddPic.setVisibility(View.GONE);
                    mImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                }
                break;
            default:
                break;
        }
    }

    private boolean getInput() {
        dynamicTxtStr = editDynamicTxt.getText().toString();
        return dynamicTxtStr.equals("")? true : false;
    }

    private void publishDynamic() {
        if(getInput()) {
            ToastUtil.toastShow(getApplicationContext(),"内容不能为空！");
            return;
        }
        publishTimeStr = DateStrUtil.changeDateToStr(DateStrUtil.MIN_FORMAT,new Date());
        userAccountStr = SharePreferencesUtil.getUserAccount(this);
        userNameStr = SharePreferencesUtil.getUserName(this);

        Dynamic dynamic = new Dynamic();
        dynamic.setUserAccount(userAccountStr);
        dynamic.setHabitName(habitName);
        dynamic.setUserName(userNameStr);
        dynamic.setDynamicTxt(dynamicTxtStr);
        dynamic.setCommentNum(0);
        dynamic.setPublishTimeStr(publishTimeStr);
        dynamic.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null) {
//                    insertIntoDUH(s);
                    ToastUtil.toastShow(getApplicationContext(),"动态发布成功！");
                } else {
                    Log.d("sqchen","插入Dynamic失败！");
                }
            }
        });
    }

//    private void insertIntoDUH(String dynamicIdStr) {
//        DynUserHabit duh = new DynUserHabit();
//        duh.setUserAccount(userAccountStr);
//        duh.setHabitName(habitName);
//        duh.setDynamicId(dynamicIdStr);
//        duh.setPublishTimeStr(publishTimeStr);
//        duh.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e == null) {
//                    ToastUtil.toastShow(getApplicationContext(),"动态发布成功！");
//                } else {
//                    ToastUtil.toastShow(getApplicationContext(),"动态发布失败！");
//                }
//            }
//        });
//    }
}
