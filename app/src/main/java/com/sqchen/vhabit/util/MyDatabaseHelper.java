package com.sqchen.vhabit.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/3/15.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    public static final String CREATE_USER = "create table User ("
            + "userAccount text primary key, "
            + "userName text, "
            + "userSex text, "
            + "userPassword text, "
            + "userBirthStr text, "
            + "signatureStr text)";

    public MyDatabaseHelper(Context context, String name,
                         SQLiteDatabase.CursorFactory factory,int version) {
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
//        ToastUtil.toastShow(mContext,"表User创建成功！");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {

    }
}
