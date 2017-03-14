package com.sqchen.vhabit.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ToastUtil {

    public static void toastShow(Context context,String content) {
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }
}
