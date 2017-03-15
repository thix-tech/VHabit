package com.sqchen.vhabit.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SharePreferencesUtil {

    public static SharedPreferences preferences  = null;

    public static String getUserAccount(Context context) {
        preferences= context.getSharedPreferences("userInfo",MODE_PRIVATE);
        return preferences.getString("userAccount","");
    }

    public static String getUserName(Context context) {
        preferences = context.getSharedPreferences("userInfo",MODE_PRIVATE);
        return preferences.getString("userName","");
    }
}
