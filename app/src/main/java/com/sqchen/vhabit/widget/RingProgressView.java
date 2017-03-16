package com.sqchen.vhabit.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.PublishDynamicActivity;

/**
 * 参考网站：http://blog.csdn.net/qp23401/article/details/50373660
 * Created by Administrator on 2017/3/6.
 */

public class RingProgressView extends View {
    //View默认最小宽度
    private static final int DEFAULT_MIN_WIDTH = 400;

    //圆环颜色
    private int[] ringColors = new int[]{Color.WHITE,getResources().getColor(R.color.green),getResources().getColor(R.color.green)};

    private int width;
    private int height;
    private float currentValue = 0f;
    private Paint mPaint = new Paint();

    public RingProgressView(Context context) {
        super(context);
    }

    public RingProgressView(Context context, AttributeSet attr) {
        super(context,attr);
    }

    public RingProgressView(Context context,AttributeSet attr,int defStyle) {
        super(context,attr,defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec),measure(heightMeasureSpec));
    }

    private void resetParams() {
        width = getWidth();
        height = getHeight();
    }

    private void initPaint() {
        mPaint.reset();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        resetParams();
        //画白色背景
        initPaint();
        float ringWidth = Math.min(width,height) / 2 * 0.1f;
        //设置圆环边缘线宽度
        mPaint.setStrokeWidth(ringWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        //设置画笔颜色
        mPaint.setColor(getResources().getColor(R.color.ring_progress_bg));
        mPaint.setAntiAlias(true);
        RectF rectF = new RectF((width > height ?  Math.abs(width - height) /2:0) + ringWidth / 2,
                (height > width ? Math.abs(height - width) / 2 : 0) + ringWidth / 2,
                width - (width > height ? Math.abs(width - height) / 2 : 0) - ringWidth / 2,
                height - (height > width ? Math.abs(height - width) / 2 : 0) - ringWidth / 2);
        canvas.drawArc(rectF,0,360,false,mPaint);

        //画彩色圆环
        initPaint();
        canvas.rotate(-90,width / 2,height / 2);
        mPaint.setStrokeWidth(ringWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        if(ringColors.length > 1) {
            mPaint.setShader(new SweepGradient(width / 2,height / 2,ringColors,null));
        } else {
            mPaint.setColor(ringColors[0]);
        }
        canvas.drawArc(rectF,0,currentValue,false,mPaint);
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if(specMode == MeasureSpec.AT_MOST) {
                result = Math.min(specSize,result);
            }
        }
        return result;
    }

    //开始动画，即签到
    public boolean startUpdate(final ImageView v1,final View v2,final View v3,final String habitName) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,360 * 1.0f);
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return 1- (1 - input) * (1 - input) * (1 - input);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue();
                if(currentValue == 360 * 1.0f) {
                    v1.setImageResource(R.drawable.check_in);
                    Animation anim = AnimationUtils.loadAnimation(getContext(),R.anim.anim_check_btn);
                    v2.startAnimation(anim);
                    //动画监听
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //发表动态按钮的显示
                            v3.setVisibility(View.VISIBLE);
                            //打开发表动态界面
                            Intent intent =  new Intent(getContext(), PublishDynamicActivity.class);
                            intent.putExtra("HABIT_NAME",habitName);
                            getContext().startActivity(intent);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
                invalidate();
            }
        });
        valueAnimator.start();
        return true;
    }


    //取消动画，即取消签到
    public boolean cancelUpdate(final ImageView v1,final View v2,final View v3) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(360 * 1.0f,0f);
        valueAnimator.setDuration(500);
        valueAnimator.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return 1- (1 - input) * (1 - input) * (1 - input);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue();
                if(currentValue == 0f) {
                    v1.setImageResource(R.drawable.check_out);
                    Animation anim = AnimationUtils.loadAnimation(getContext(),R.anim.anim_check_out);
                    v2.startAnimation(anim);
                    anim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //发表动态按钮的隐藏
                            v3.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
                invalidate();
            }
        });
        valueAnimator.start();
        return false;
    }

}
