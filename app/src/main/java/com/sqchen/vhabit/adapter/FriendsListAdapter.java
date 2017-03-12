package com.sqchen.vhabit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.beans.User;
import com.sqchen.vhabit.utils.BitmapUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public class FriendsListAdapter extends ArrayAdapter {

    private LayoutInflater mInflater;

    private List<User> mUserList;

    private boolean isFollowing = true;


    public FriendsListAdapter(Context context, List<User> list) {
        super(context,0,list);
        mUserList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.cell_friends,null);
            holder.mUserIcon = (ImageView) convertView.findViewById(R.id.friends_icon);
            holder.mUserName = (TextView) convertView.findViewById(R.id.friends_name);
            holder.mUserSignature = (TextView) convertView.findViewById(R.id.friends_signature);
            holder.btnDelete = (Button) convertView.findViewById(R.id.friends_btn_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mUserIcon.setImageBitmap(BitmapUtil.readBitmap(getContext(),mUserList.get(position).getUserIconId()));
        holder.mUserName.setText(mUserList.get(position).getUserName());
        holder.mUserSignature.setText(mUserList.get(position).getSignatureStr());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFollowing) {
                    Toast.makeText(getContext(),
                            "如果你认识从前的我，你就会原谅现在的我，再见。",
                            Toast.LENGTH_SHORT).show();
                    v.setBackground(getContext().getResources().getDrawable(R.drawable.follow));
                    ((Button) v).setText("");
                    isFollowing = false;
                } else {
                    Toast.makeText(getContext(),
                            "很高兴认识你，去你家玩好吗？",
                            Toast.LENGTH_SHORT).show();
                    v.setBackground(getContext().getResources().getDrawable(R.drawable.btn_delete_bg));
                    ((Button)v).setText("取消关注");
                    isFollowing = true;
                }
            }
        });

        return convertView;
    }

    final private class ViewHolder{

        ImageView mUserIcon;

        TextView mUserName;

        TextView mUserSignature;

        Button btnDelete;
    }

}
