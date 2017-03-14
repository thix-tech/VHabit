package com.sqchen.vhabit.fragment.find;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.DynamicDetails;
import com.sqchen.vhabit.adapter.DynamicListAdapter;
import com.sqchen.vhabit.bean.Dynamic;
import com.sqchen.vhabit.bean.User;
import com.sqchen.vhabit.widget.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * “发现”中的“热门”页面
 * A simple {@link Fragment} subclass.
 */
public class FindHotFrag extends Fragment {

    //动态数组
    private List<Dynamic> mDynamicList = new ArrayList<>();

    //适应ScrollView的ListView
    private ListViewForScrollView mListView;

    //动态列表适配器
    private DynamicListAdapter mAdapter;

    //点赞动态的用户数组
    private List<User> mLikedUsers = new ArrayList<>();

    //滑动控件
    private ScrollView mScrollView;

    private ImageView mTopImg;
    public FindHotFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载布局
        View rootView = inflater.inflate(R.layout.fragment_find_hot, container, false);
        mScrollView = (ScrollView) rootView.findViewById(R.id.hot_scrollview);
        mListView = (ListViewForScrollView) rootView.findViewById(R.id.lv_hot);
        mTopImg = (ImageView) rootView.findViewById(R.id.hot_top_img);
        mTopImg.setBackground(getResources().getDrawable(R.drawable.hot_top));

        //初始化点赞动态用户数组的数据
        initUsers();
        //初始化动态数组数据
        initDynamics();
        mAdapter = new DynamicListAdapter(getContext(),mDynamicList,true,false);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DynamicDetails.class);
                intent.putExtra("selectedDynamic",mDynamicList.get(position));
                startActivity(intent);
            }
        });

        /**
         * scrollview嵌套listview，采用自定义listview来适应scrollview，
         * 但是页面的首项会默认显示listview，需要手动把scrollview滚动至最顶端
         */
        mScrollView.smoothScrollTo(0,0);

        return rootView;
    }

    //初始化动态数组的数据
    private void initDynamics() {
        mDynamicList.add(new Dynamic(R.drawable.user_1,"晴雨er","#坚持每天手写一句喜欢的话#","今天 12:53","11天",
                R.drawable.dynamic_1,"我想要你",mLikedUsers,3));
        mDynamicList.add(new Dynamic(R.drawable.user_5,"隐进盛夏的两脚兽","#每天拍张照片#","2月26日","18天",
                R.drawable.dynamic_2,"红菜心开花了，田野的气息",mLikedUsers,3));
        mDynamicList.add(new Dynamic(R.drawable.user_3,"南木","#记手账#","昨天 11:14","289天",
                R.drawable.dynamic_3,"大通道真的好美",mLikedUsers,3));
        mDynamicList.add(new Dynamic(R.drawable.user_4,"我是柠檬君","#艺术史#","昨天 20:14","291天",
                R.drawable.dynamic_4,"奥地利表现主义；毕加索与勃拉克之后的巴黎立体主义；意大利未来主义。各国艺术的线代主义化，" +
                "我觉得这些作品并不需要用来理解，只能用来感受。",mLikedUsers,3));
    }

    //初始化点赞动态的用户数组数据
    private void initUsers() {
        mLikedUsers.add(new User(R.drawable.user_2,"晴雨er","女","1995-9-12","冬天花败"));
        mLikedUsers.add(new User(R.drawable.user_3,"隐进盛夏的两脚兽","女","1995-9-12","冬天花败"));
        mLikedUsers.add(new User(R.drawable.user_4,"南木","女","1995-9-12","冬天花败"));
        mLikedUsers.add(new User(R.drawable.user_5,"当往事已成空","女","1995-9-12","冬天花败"));
        mLikedUsers.add(new User(R.drawable.user_6,"Jay8381","女","1995-9-12","冬天花败"));
        mLikedUsers.add(new User(R.drawable.user_7,"Deer-薛小谦","女","1995-9-12","冬天花败"));
        mLikedUsers.add(new User(R.drawable.user_8,"我是柠檬君","女","1995-9-12","冬天花败"));
    }

}
