package com.sqchen.vhabit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.beans.Habit;

import java.util.List;

/**
 * “添加习惯”界面的习惯列表框适配器
 * Created by Administrator on 2017/2/28.
 */

public class NewHabitListAdapter extends ArrayAdapter {
    //用于加载布局
    private LayoutInflater mInflater;

    //“习惯”数组
    private List<Habit> mDataList;

    public NewHabitListAdapter(Context context,List<Habit> list) {
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
            convertView = mInflater.inflate(R.layout.cell_new_habit,null);
            //获取控件实例
            holder.habitImg = (ImageView) convertView.findViewById(R.id.new_habit_icon);
            holder.habitName = (TextView) convertView.findViewById(R.id.new_habit_name);
            holder.selectedNum = (TextView) convertView.findViewById(R.id.new_habit_selected_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //填充数据
        holder.habitImg.setImageResource(mDataList.get(position).getHabitImageId());
        holder.habitName.setText(mDataList.get(position).getHabitName());
        holder.selectedNum.setText(String.valueOf((mDataList.get(position).getSelectedNum())));

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

        //坚持该习惯的人数
        TextView selectedNum;

    }

}
