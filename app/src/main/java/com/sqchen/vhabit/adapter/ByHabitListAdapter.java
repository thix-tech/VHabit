package com.sqchen.vhabit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.bean.Dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * “我的”界面根据习惯排序界面的列表框适配器
 * Created by Administrator on 2017/3/3.
 */

public class ByHabitListAdapter extends ArrayAdapter {

    //用于加载布局
    private LayoutInflater mInflater;

    //动态数组
    private List<Dynamic> mDynamicList = new ArrayList<Dynamic>();

    public ByHabitListAdapter(Context context, List<Dynamic> list) {
        super(context,0,list);
        mInflater = LayoutInflater.from(context);
        mDynamicList = list;
    }

    @Override
    public int getCount() {
        return mDynamicList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            //加载布局
            convertView = mInflater.inflate(R.layout.cell_personal_habit,null);
            //获取控件实例
            holder.habitTxt = (TextView) convertView.findViewById(R.id.personal_item_habit);
            holder.habitBeginTime = (TextView) convertView.findViewById(R.id.personal_time_begin);
            holder.habitOverTime = (TextView) convertView.findViewById(R.id.personal_time_over);
            holder.habitDuration = (TextView) convertView.findViewById(R.id.personal_time_num);
            holder.habitDynamicTxt = (TextView) convertView.findViewById(R.id.personal_dynamic_text);
            holder.habitDynamicTime = (TextView) convertView.findViewById(R.id.personal_dynamic_time);
            holder.habitDynamicCard = (LinearLayout) convertView.findViewById(R.id.personal_item_dynamic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //如果该动态没有主题文字，则隐藏主题文字卡片区域的布局
        if(mDynamicList.get(position).getDynamicTxt() == null) {
            holder.habitDynamicCard.setVisibility(View.GONE);
        } else {    //否则，正常显示，并且填充数据
            holder.habitDynamicCard.setVisibility(View.VISIBLE);
            holder.habitDynamicTxt.setText(mDynamicList.get(position).getDynamicTxt());
            holder.habitDynamicTime.setText(mDynamicList.get(position).getPublishTimeStr());
        }
        //填充各项数据
        holder.habitTxt.setText(mDynamicList.get(position).getUserHabit());
        holder.habitBeginTime.setText(mDynamicList.get(position).getHabitBeginTime());
        holder.habitOverTime.setText(mDynamicList.get(position).getHabitOverTime());
        holder.habitDuration.setText(mDynamicList.get(position).getDurationStr());

        return convertView;
    }

    /**
     * 用于提高ListView效率的一种视图缓存机制
     */
    public final class ViewHolder{
        //习惯名称
        TextView habitTxt;

        //习惯开始时间
        TextView habitBeginTime;

        //习惯结束时间
        TextView habitOverTime;

        //习惯坚持时间
        TextView habitDuration;

        //习惯相关动态的主题文字
        TextView habitDynamicTxt;

        //习惯相关动态的发表日期
        TextView habitDynamicTime;

        //动态主题文字卡片区域
        LinearLayout habitDynamicCard;
    }

}
