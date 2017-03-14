package com.sqchen.vhabit.fragment.personal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.adapter.DynamicListAdapter;
import com.sqchen.vhabit.bean.Dynamic;
import com.sqchen.vhabit.bean.User;
import com.sqchen.vhabit.widget.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ByTimeFrag extends Fragment {

    //适应ScrollView的ListView
    private ListViewForScrollView mListView;

    //动态列表框适配器
    private DynamicListAdapter mAdapter;

    //动态数组
    private List<Dynamic> mDynamicList = new ArrayList<Dynamic>();

    //点赞动态的用户数组
    private List<User> mLikedUsers = new ArrayList<>();


    public ByTimeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载布局
        View rootView = inflater.inflate(R.layout.fragment_by_time, container, false);

        initUsers();
        initDynamics();
        mListView = (ListViewForScrollView) rootView.findViewById(R.id.list_view_by_time);
        mAdapter = new DynamicListAdapter(getContext(),mDynamicList,false,false);
        mListView.setAdapter(mAdapter);

        return rootView;
    }

    //初始化动态数组
    private void initDynamics() {
        mDynamicList.add(new Dynamic(R.drawable.my_icon,"帅气陈","#戒烟：我能#","2月26日","1天",
                0,"3根",mLikedUsers,0));
        mDynamicList.add(new Dynamic(R.drawable.my_icon,"帅气陈","#对一天进行回顾#","2月24日","1天",
                0,"在游戏里发生不愉快，实在是一件很蠢的事。",null,0));
        mDynamicList.add(new Dynamic(R.drawable.my_icon,"帅气陈","#多喝水#","2月24日","2天",
                0,"今天咳嗽好一点了。现在应该说是昨天了。昨天姐姐生日。姐姐长成了大人三个孩子的母亲。生日快乐。",null,0));
        mDynamicList.add(new Dynamic(R.drawable.my_icon,"帅气陈","#每天想一下自己想要什么#","2月24日","3天",
                0,"想要让家人过上很好的生活，想要一个还算不错的自己。",null,0));
    }

    //初始化点赞动态的用户数组
    private void initUsers() {
        mLikedUsers.add(new User(R.drawable.user_2,"晴雨er","女","1995-9-12","冬天花败"));
    }

}
