package com.sqchen.vhabit.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/3/3.
 */

public class ViewPagerForScrollView extends ViewPager {

    public ViewPagerForScrollView(Context context) {
        super(context);
    }

    public ViewPagerForScrollView(Context context, AttributeSet attr) {
        super(context,attr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        //设置一个中间变量
        int height = 0;
        //循环获取子控件
        for(int i = 0;i < getChildCount();i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec,MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(h > height)
                height = h;
        }
        //得到子控件的精确高度
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        //回调宽高数据给父类进行绘制
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

}
