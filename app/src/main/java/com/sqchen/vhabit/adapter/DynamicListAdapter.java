package com.sqchen.vhabit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.HabitDetailsActivity;
import com.sqchen.vhabit.activity.OthersActivity;
import com.sqchen.vhabit.activity.TreeActivity;
import com.sqchen.vhabit.bean.Dynamic;
import com.sqchen.vhabit.util.BitmapUtil;

import java.util.List;

/**
 * 动态列表的适配器，用于ListView适配数据，
 * 包括“发现”中的“热门”界面的动态列表，和“我的”中按照时间排序的动态列表
 * Created by Administrator on 2017/3/2.
 */

public class DynamicListAdapter extends ArrayAdapter {

    //用于加载布局
    private LayoutInflater mInflater;

    //动态数组
    private List<Dynamic> mDynamicList;

    //用于判断是否是热门动态那个布局
    private boolean isFromHot;

    //用于判断是否是社区动态的那个布局
    private boolean isFromCommuntity;

    //“我”是否点赞
    private boolean isLiked = false;

    public DynamicListAdapter(Context context, List<Dynamic> list,boolean isFragHot,boolean isFromCommuntity) {
        super(context,0,list);
        this.mDynamicList = list;
        mInflater = LayoutInflater.from(context);
        this.isFromHot = isFragHot;
        this.isFromCommuntity = isFromCommuntity;
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
            convertView = mInflater.inflate(R.layout.cell_dynamic,null);
            //获取控件实例
            holder.linFrom = (LinearLayout) convertView.findViewById(R.id.dynamic_lin_top);
            holder.txtFromSign = (TextView) convertView.findViewById(R.id.dynamic_is_first);
            holder.communitySign = (ImageView) convertView.findViewById(R.id.community_sign_img);
            holder.userIcon = (ImageView) convertView.findViewById(R.id.dynamic_user_icon);
            holder.userName = (TextView) convertView.findViewById(R.id.dynamic_user_name);
            holder.publishTime = (TextView) convertView.findViewById(R.id.dynamic_publish_time);
            holder.dynamicHabit = (TextView) convertView.findViewById(R.id.dynamic_habit);
            holder.habitDuration = (TextView) convertView.findViewById(R.id.dynamic_habit_duration);
            holder.dynamicImg = (ImageView) convertView.findViewById(R.id.dynamic_img);
            holder.dynamicTxt = (TextView) convertView.findViewById(R.id.dynamic_text);
            holder.likedNum = (TextView) convertView.findViewById(R.id.dynamic_liked_num);
            holder.commentNum = (TextView) convertView.findViewById(R.id.dynamic_comment_num);
            holder.dynamicLinLike = (LinearLayout) convertView.findViewById(R.id.dynamic_lin_click_like);
            holder.dynamicLinComment = (LinearLayout) convertView.findViewById(R.id.dynamic_lin_click_comment);
            holder.dynamicLinMore = (LinearLayout) convertView.findViewById(R.id.dynamic_lin_click_more);
            holder.dynamicLinTree = (LinearLayout) convertView.findViewById(R.id.dynamic_lin_click_tree);
            holder.mLikeImg = (ImageView) convertView.findViewById(R.id.dynamic_click_like);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //如果是列表框的第一条数据，并且是用于“热门”界面的那个动态列表框的，则正常显示和填充数据
        if(position == 0 && isFromHot) {
            holder.txtFromSign.setText("热门动态");
            holder.linFrom.setVisibility(View.VISIBLE);
            holder.communitySign.setVisibility(View.GONE);
        } else if(position == 0 && isFromCommuntity) {
            holder.txtFromSign.setText("社区内容");
            holder.linFrom.setVisibility(View.VISIBLE);
            holder.communitySign.setVisibility(View.VISIBLE);
        } else {    //否则，隐藏该布局
            holder.linFrom.setVisibility(View.GONE);
        }
        //填充数据
        if(mDynamicList.get(position).getUserIconId() != 0) {
            holder.userIcon.setImageBitmap(BitmapUtil.readBitmap(getContext(),mDynamicList.get(position).getUserIconId()));
        } else {
            holder.userIcon.setImageBitmap(BitmapUtil.readBitmap(getContext(),R.drawable.user_1));
        }
        holder.userName.setText(mDynamicList.get(position).getUserName());
        holder.publishTime.setText(mDynamicList.get(position).getPublishTimeStr());
        holder.dynamicHabit.setText("#" + mDynamicList.get(position).getHabitName() + "#");
        holder.habitDuration.setText(mDynamicList.get(position).getDurationStr());
        holder.dynamicTxt.setText(mDynamicList.get(position).getDynamicTxt());

        //如果有主题图片，则填充数据，否则设置主题图片控件为不可见
        if(mDynamicList.get(position).getDynamicImgId() != 0) {
            holder.dynamicImg.setVisibility(View.VISIBLE);
            holder.dynamicImg.setImageBitmap(BitmapUtil.readBitmap(getContext(),mDynamicList.get(position).getDynamicImgId()));
        } else {    //否则，隐藏该布局
            holder.dynamicImg.setVisibility(View.GONE);
        }

        holder.commentNum.setText(String.valueOf(mDynamicList.get(position).getCommentNum()));

        holder.dynamicHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HabitDetailsActivity.class);
                getContext().startActivity(intent);
            }
        });
        holder.userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OthersActivity.class);
                getContext().startActivity(intent);
            }
        });
        holder.dynamicLinLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLiked) {
                    ((ImageView)((LinearLayout) v).getChildAt(0)).setImageBitmap(BitmapUtil.readBitmap(getContext(),R.drawable.ic_mindfeed_liked));
                    int num = Integer.valueOf(((TextView)((LinearLayout) v).getChildAt(1)).getText().toString());
                    ((TextView)((LinearLayout) v).getChildAt(1)).setText(String.valueOf(num + 1));
                    isLiked = true;
                } else {
                    ((ImageView)((LinearLayout) v).getChildAt(0)).setImageBitmap(BitmapUtil.readBitmap(getContext(),R.drawable.ic_mindfeed_like));
                    int num = Integer.valueOf(((TextView)((LinearLayout) v).getChildAt(1)).getText().toString());
                    ((TextView)((LinearLayout) v).getChildAt(1)).setText(String.valueOf(num - 1));
                    isLiked = false;
                }
            }
        });

        holder.dynamicLinTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),TreeActivity.class);
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }


    /**
     * 用于提高ListView效率的一种视图缓存机制
     */
    public final class ViewHolder{
        //顶部来源标志
        LinearLayout linFrom;

        //是否是热门动态的第一条
        TextView txtFromSign;

        //社区标志图标
        ImageView communitySign;

        //动态用户头像
        ImageView userIcon;

        //动态主题图片
        ImageView dynamicImg;

        //动态用户名
        TextView userName;

        //动态主题习惯
        TextView dynamicHabit;

        //动态发表时间
        TextView publishTime;

        //主题习惯坚持时间
        TextView habitDuration;

        //动态主题文字
        TextView dynamicTxt;

        //动态点赞量
        TextView likedNum;

        //动态评论量
        TextView commentNum;

        private ImageView mLikeImg;

        //点赞行布局区域
        LinearLayout mLikeLin;

        //底部四个操作项
        private LinearLayout dynamicLinLike,dynamicLinComment,dynamicLinTree,dynamicLinMore;
    }

}
