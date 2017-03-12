package com.sqchen.vhabit.fragment.find;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.activity.DynamicDetails;
import com.sqchen.vhabit.adapter.DynamicListAdapter;
import com.sqchen.vhabit.beans.Dynamic;
import com.sqchen.vhabit.beans.User;
import com.sqchen.vhabit.widgets.ListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * “发现”中的“关注”页面
 * A simple {@link Fragment} subclass.
 */
public class FindFocusFrag extends Fragment {

    //“关注”界面动态列表框
    private ListViewForScrollView mListView;

    private List<Dynamic> mDynamicList;

    private DynamicListAdapter mAdapter;

    public FindFocusFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_find_focus, container, false);

        mDynamicList = new ArrayList<Dynamic>();
        initDynamics();
        mListView = (ListViewForScrollView) rootView.findViewById(R.id.focus_listview);
        mAdapter = new DynamicListAdapter(getContext(),mDynamicList,false,false);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), DynamicDetails.class);
                intent.putExtra("selectedDynamic",mDynamicList.get(position));
                startActivity(intent);
            }
        });
        

        return rootView;
    }

    //初始化动态数组的数据
    private void initDynamics() {
        mDynamicList.add(new Dynamic(R.drawable.user_1,"南木","#坚持每天手写一句喜欢的话#","今天 12:53","11天",
                R.drawable.dynamic_1,"我想要你",null,3));
        mDynamicList.add(new Dynamic(R.drawable.user_5,"隐进盛夏的两脚兽","#每天拍张照片#","2月26日","18天",
                R.drawable.dynamic_2,"红菜心开花了，田野的气息",null,3));
        mDynamicList.add(new Dynamic(R.drawable.user_3,"南木","#记手账#","昨天 11:14","289天",
                R.drawable.dynamic_3,"大通道真的好美",null,3));
        mDynamicList.add(new Dynamic(R.drawable.user_1,"晴雨er","#艺术史#","昨天 20:14","291天",
                R.drawable.dynamic_4,"奥地利表现主义；毕加索与勃拉克之后的巴黎立体主义；意大利未来主义。各国艺术的线代主义化，" +
                "我觉得这些作品并不需要用来理解，只能用来感受。",null,3));
    }


}
