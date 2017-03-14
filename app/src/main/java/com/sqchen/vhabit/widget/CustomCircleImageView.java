package com.sqchen.vhabit.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.sqchen.vhabit.R;

/**
 * Created by Administrator on 2017/3/14.
 */

public class CustomCircleImageView extends ImageView {

    private Paint mPaint;


    public CustomCircleImageView(Context context) {
        super(context);
        initView();
    }

    public CustomCircleImageView(Context context, AttributeSet attr) {
        super(context,attr);
        initView();
    }

    public CustomCircleImageView(Context context,AttributeSet attr,int defStyle) {
        super(context,attr,defStyle);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.green));
    }

    @Override
    public void onDraw(Canvas canvas) {


    }

}
