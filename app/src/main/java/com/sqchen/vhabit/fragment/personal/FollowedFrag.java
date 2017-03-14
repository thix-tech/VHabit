package com.sqchen.vhabit.fragment.personal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.FriendsListAdapter;
import com.sqchen.vhabit.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowedFrag extends Fragment {

    private ListView mListView;

    private FriendsListAdapter mAdapter;

    private List<User> mUserList = null;

    private TextView mFrameTips;

    private FrameLayout mFrameLayout;

    public FollowedFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_followed, container, false);

        initData();
        mFrameLayout = (FrameLayout) rootView.findViewById(R.id.frame_tips_layout);
        mListView = (ListView) rootView.findViewById(R.id.followed_list_view);
        mFrameTips = (TextView) rootView.findViewById(R.id.frame_center_text);

        //如果没有粉丝，则显示提示界面
        if(mUserList == null || mUserList.size() == 0) {
            mFrameLayout.setVisibility(View.VISIBLE);
            mFrameTips.setText(R.string.str_no_fans);
            mListView.setVisibility(View.GONE);
        } else {    //否则正常显示粉丝列表界面,隐藏提示界面
            mListView.setVisibility(View.VISIBLE);
            mFrameLayout.setVisibility(View.GONE);
            mAdapter = new FriendsListAdapter(getContext(),mUserList);
            mListView.setAdapter(mAdapter);
        }

        return rootView;
    }

    private void initData(){
        mUserList = new ArrayList<User>();
//        mUserList.add(new User(R.drawable.daren_ranking01,"VHabit团队","专为广大V友补给成长途中所需的各种能量。"));
//        mUserList.add(new User(R.drawable.user_1,"薛以猫","瘦身 成家 出国 吃天下"));
    }

}
