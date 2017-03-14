package com.sqchen.vhabit.fragment.find;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.bean.User;
import com.sqchen.vhabit.util.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchUserFrag extends Fragment {

    private LinearLayout mUserLin1;

    private LinearLayout mUserLin2;

    private ImageView mUserIcon1;

    private ImageView mUserIcon2;

    private TextView mUserName1;

    private TextView mUserName2;

    private TextView mUserLikedNum1;

    private TextView mUserLikedNum2;

    private List<User> mUserList;

    public SearchUserFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_user, container, false);
        // Inflate the layout for this fragment

        initData();

        mUserLin1 = (LinearLayout) rootView.findViewById(R.id.lin_hot_user_1);
        mUserLin2 = (LinearLayout) rootView.findViewById(R.id.lin_hot_user_2);
        mUserIcon1 = (ImageView) rootView.findViewById(R.id.user_icon_1);
        mUserIcon2 = (ImageView) rootView.findViewById(R.id.user_icon_2);
        mUserName1 = (TextView) rootView.findViewById(R.id.user_name_1);
        mUserName2 = (TextView) rootView.findViewById(R.id.user_name_2);
        mUserLikedNum1 = (TextView) rootView.findViewById(R.id.user_liked_num_1);
        mUserLikedNum2 = (TextView) rootView.findViewById(R.id.user_liked_num_2);

        mUserIcon1.setImageBitmap(BitmapUtil.readBitmap(getContext(),mUserList.get(0).getUserIconId()));
        mUserName1.setText(mUserList.get(0).getUserName());
        mUserLikedNum1.setText(mUserList.get(0).getTotalLikeNum() + getString(R.string.str_like_num));

        mUserIcon2.setImageBitmap(BitmapUtil.readBitmap(getContext(),mUserList.get(1).getUserIconId()));
        mUserName2.setText(mUserList.get(1).getUserName());
        mUserLikedNum2.setText(mUserList.get(1).getTotalLikeNum() + getString(R.string.str_like_num));

        return rootView;
    }

    private void initData() {
        mUserList = new ArrayList<User>();
        mUserList.add(new User(R.drawable.user_1,"Slowpoke",3268));
        mUserList.add(new User(R.drawable.user_2,"雨小月",1337));
    }

}
