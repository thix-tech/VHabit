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
public class FollowingFrag extends Fragment {

    private ListView mListView;

    private FriendsListAdapter mAdapter;

    private List<User> mUserList = null;

    private FrameLayout mFrameLayout;

    //没有关注任何人时的提示文字
    private TextView mFrameTips;

    public FollowingFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_following, container, false);

        initData();
        mListView = (ListView) rootView.findViewById(R.id.following_list_view);
        mFrameLayout = (FrameLayout) rootView.findViewById(R.id.frame_tips_layout);
        mFrameTips = (TextView) rootView.findViewById(R.id.frame_center_text);

        //如果没有关注的人，则显示提示界面
        if(mUserList == null || mUserList.size() == 0) {
            mFrameLayout.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.GONE);
            mFrameTips.setText("一个人要像一支队伍？\n扎心了老铁，点击四十五度右上角吧");
        } else {    //否则正常显示关注界面，隐藏提示界面
            mFrameLayout.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mAdapter = new FriendsListAdapter(getContext(),mUserList);
            mListView.setAdapter(mAdapter);
        }

        return rootView;
    }

    private void initData(){
        mUserList = new ArrayList<User>();
        mUserList.add(new User(R.drawable.daren_ranking01,"VHabit团队","专为广大V友补给成长途中所需的各种能量。"));
        mUserList.add(new User(R.drawable.user_1,"薛以猫","瘦身 成家 出国 吃天下"));
    }
}
