package com.sqchen.vhabit.fragment.message;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sqchen.vhabit.R;

/**
 * “消息”中的“私信”界面
 * A simple {@link Fragment} subclass.
 */
public class MsgNoticeFrag extends Fragment {


    public MsgNoticeFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_msg_notice, container, false);
    }

}
