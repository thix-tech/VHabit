package com.sqchen.vhabit.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.beans.Dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * 针对两列listview的适配器，参考链接：http://www.eoeandroid.com/forum.php?mod=viewthread&tid=280670&extra=page%3D1&page=1
 * Created by Administrator on 2017/3/12.
 */

public class HistoryListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private List<Dynamic> mDynamicList = new ArrayList<Dynamic>();

    private Context mContext;

    public HistoryListAdapter(Context context, List<Dynamic> list) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDynamicList = list;
    }

    @Override
    public int getCount() {
        //如果数据项为偶数，则item的ID为数据集合大小的一半，否则为一半加一
        return mDynamicList.size() % 2 == 0 ? mDynamicList.size() / 2 : mDynamicList.size() / 2 + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            convertView = mInflater.inflate(R.layout.cell_history,null);
            //左边item
            holder.linItem1 = (LinearLayout) convertView.findViewById(R.id.history_item_1);
            holder.txtDynamic1 = (TextView) convertView.findViewById(R.id.txt_dynamic_1);
            holder.txtLikeNum1 = (TextView) convertView.findViewById(R.id.txt_like_num_1);
            holder.txtCommentNum1 = (TextView) convertView.findViewById(R.id.txt_comment_num_1);

            //右边item
            holder.linItem2 = (LinearLayout) convertView.findViewById(R.id.history_item_2);
            holder.txtDynamic2 = (TextView) convertView.findViewById(R.id.txt_dynamic_2);
            holder.txtLikeNum2 = (TextView) convertView.findViewById(R.id.txt_like_num_2);
            holder.txtCommentNum2 = (TextView) convertView.findViewById(R.id.txt_comment_num_2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //用于存储左右两个item
        Dynamic dynamic1 = null;
        Dynamic dynamic2 = null;

        /* position * 2 + 1是一个临界值，当它小于数据集合大小时，说明第position * 2 + 1项还有后面数据
         * 当它等于数据集合大小时，说明后面没有数据了，最后一行只有一项，即左边那个item
         */
        if(position * 2 + 1 < mDynamicList.size()) {
            dynamic1 = mDynamicList.get(position * 2);
            dynamic2 = mDynamicList.get(position * 2 + 1);
        } else if(position * 2 + 1 == mDynamicList.size()){
            dynamic1 = mDynamicList.get(position * 2);
            dynamic2 = null;
        }

        if(dynamic1 != null) {
            holder.txtDynamic1.setText(dynamic1.getDynamicTxt());
            //点赞人数为空的判断
            if(dynamic1.getLikedUsers() != null ) {
                holder.txtLikeNum1.setText(String.valueOf(dynamic1.getLikedUsers().size()));
            } else {
                holder.txtLikeNum1.setText(String.valueOf(0));
            }
            holder.txtCommentNum1.setText(String.valueOf(dynamic1.getCommentNum()));
            holder.linItem1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"左边Item",Toast.LENGTH_SHORT).show();
                }
            });
        }

        if(dynamic2 != null) {
            holder.linItem2.setVisibility(View.VISIBLE);
            holder.linItem2.setClickable(true);
            holder.txtDynamic2.setText(dynamic2.getDynamicTxt());
            //点赞人数为空的判断
            if(dynamic2.getLikedUsers() != null) {
                holder.txtLikeNum2.setText(String.valueOf(dynamic2.getLikedUsers().size()));
            } else {
                holder.txtLikeNum2.setText(String.valueOf(0));
            }
            holder.txtCommentNum2.setText(String.valueOf(dynamic2.getCommentNum()));
            holder.linItem2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"右边Item",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            holder.linItem2.setVisibility(View.INVISIBLE);
            holder.linItem2.setClickable(false);
        }

        return convertView;
    }


    class ViewHolder {

        private LinearLayout linItem1,linItem2;

        //主题文字
        TextView txtDynamic1,txtDynamic2;

        //坚持天数
        TextView txtDayNum1,txtDayNum2;

        //点赞数
        TextView txtLikeNum1,txtLikeNum2;

        //评论数
        TextView txtCommentNum1,txtCommentNum2;
    }

}
