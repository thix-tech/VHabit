package com.sqchen.vhabit.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.bean.Habit;

import java.util.List;

/**
 * “习惯”界面的习惯列表框适配器
 * Created by Administrator on 2017/2/28.
 */

public class HabitListAdapter extends ArrayAdapter {
    //用于加载布局
    private LayoutInflater mInflater;

    //“习惯”数组
    private List<Habit> mDataList;

    public HabitListAdapter(Context context,List<Habit> list) {
        super(context,0,list);
        mInflater = LayoutInflater.from(context);
        mDataList = list;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            //加载布局
            convertView = mInflater.inflate(R.layout.cell_habit,null);
            //获取控件实例
            holder.habitCell = (LinearLayout) convertView.findViewById(R.id.lin_habit_cell);
            holder.habitImg = (ImageView) convertView.findViewById(R.id.habit_img);
            holder.habitName = (TextView) convertView.findViewById(R.id.habit_name);
            holder.habitTimeTxt = (TextView) convertView.findViewById(R.id.habit_time_txt);
            holder.habitFinish = (ImageView) convertView.findViewById(R.id.habit_is_finish);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //填充数据
        if(mDataList.get(position).getHabitImageId() == 0) {
            holder.habitImg.setImageResource(R.drawable.tree_seed);
        } else {
            holder.habitImg.setImageResource(mDataList.get(position).getHabitImageId());
        }
        holder.habitName.setText(mDataList.get(position).getHabitName());
        if(mDataList.get(position).getHabitTimeTxt() == null) {
            holder.habitTimeTxt.setText("已坚持0天");
        } else {
            holder.habitTimeTxt.setText((mDataList.get(position).getHabitTimeTxt()));
        }
//        //设置触摸事件，添加触摸动画
//        holder.habitCell.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                //布局放大动画
//                ObjectAnimator scaleX = ObjectAnimator.ofFloat(view,"scaleX",1,(float)1.1);
//                ObjectAnimator scaleY = ObjectAnimator.ofFloat(view,"scaleY",1,(float)1.1);
//                scaleX.setDuration(500);
//                scaleY.setDuration(500);
//                scaleX.start();
//                scaleY.start();
//
//                //布局缩小动画
//                ObjectAnimator scaleToSmallX = ObjectAnimator.ofFloat(view,"scaleX",(float)1.1,1);
//                ObjectAnimator scaleToSmallY = ObjectAnimator.ofFloat(view,"scaleY",(float)1.1,1);
//                scaleToSmallX.setDuration(500);
//                scaleToSmallY.setDuration(500);
//                scaleToSmallX.start();
//                scaleToSmallY.start();
//                return false;
//            }
//        });

        return convertView;
    }

    /**
     * 用于提高ListView效率的一种视图缓存机制
     */
    public final class ViewHolder {
        //习惯图标
        ImageView habitImg;

        //习惯名称
        TextView habitName;

        //习惯坚持时间
        TextView habitTimeTxt;

        //习惯今天是否坚持的标志图标
        ImageView habitFinish;

        //习惯列表的一个item
        LinearLayout habitCell;
    }

}
