package com.sqchen.vhabit.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * 用于读取图片的位图文件，减少内存消耗
 * Created by Administrator on 2017/3/2.
 */

public class BitmapUtil{

    /**
     * 读取图片的位图文件
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitmap(Context context,int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        //获取图片资源
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is,null,opt);
    }
}
