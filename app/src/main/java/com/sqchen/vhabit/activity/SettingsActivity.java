package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.widgets.CustomTitleView;

public class SettingsActivity extends Activity implements View.OnClickListener{
    //标题栏
    private CustomTitleView mTitleView;

    //编辑个人资料
    private RelativeLayout relEditInfo;

    //我的收藏
    private LinearLayout linCollection;

    //我的钱包
    private LinearLayout linWallet;

    //我的订单
    private LinearLayout linOrder;

    //我是帮帮手
    private LinearLayout linBeHelper;

    //账号绑定
    private LinearLayout linBinding;

    //帮助中心
    private LinearLayout linHelp;

    //黑名单管理
    private LinearLayout linBlackList;

    //消息提醒设置
    private LinearLayout linMsgSettings;

    //关于种子习惯
    private LinearLayout linAbout;

    //退出账号
    private LinearLayout linExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initViews();
    }

    //获取控件实例,并且设置监听器
    private void initViews() {

        mTitleView = (CustomTitleView) findViewById(R.id.settings_title_view);
        mTitleView.setTxtCenter("设置");
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        relEditInfo = (RelativeLayout) findViewById(R.id.rel_edit_info);
        linCollection = (LinearLayout) findViewById(R.id.lin_collection);
        linWallet = (LinearLayout) findViewById(R.id.lin_wallet);
        linOrder = (LinearLayout) findViewById(R.id.lin_order);
        linBeHelper = (LinearLayout) findViewById(R.id.lin_be_helper);
        linBinding = (LinearLayout) findViewById(R.id.lin_binding);
        linHelp = (LinearLayout) findViewById(R.id.lin_help);
        linBlackList = (LinearLayout) findViewById(R.id.lin_black_list);
        linMsgSettings = (LinearLayout) findViewById(R.id.lin_msg_setting);
        linAbout = (LinearLayout) findViewById(R.id.lin_about_seed);
        linExit = (LinearLayout) findViewById(R.id.lin_exit);

        relEditInfo.setOnClickListener(this);
        linCollection.setOnClickListener(this);
        linWallet.setOnClickListener(this);
        linOrder.setOnClickListener(this);
        linBeHelper.setOnClickListener(this);
        linBinding.setOnClickListener(this);
        linHelp.setOnClickListener(this);
        linBlackList.setOnClickListener(this);
        linMsgSettings.setOnClickListener(this);
        linAbout.setOnClickListener(this);
        linExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch(view.getId()) {
            case R.id.rel_edit_info:
                intent = new Intent(this,BasicInfoActivity.class);
                break;
            case R.id.lin_collection:
                intent = new Intent(this,CollectionActivity.class);
                break;
            case R.id.lin_wallet:
                intent = new Intent(this,WalletActivity.class);
                break;
            case R.id.lin_order:
                intent = new Intent(this,OrderActivity.class);
                break;
            case R.id.lin_be_helper:
                intent = new Intent(this,BeHelperActivity.class);
                break;
            case R.id.lin_binding:
                intent = new Intent(this,BindingActivity.class);
                break;
            case R.id.lin_help:
                intent = new Intent(this,HelpActivity.class);
                break;
            case R.id.lin_black_list:
                intent = new Intent(this,BlackListActivity.class);
                break;
            case  R.id.lin_msg_setting:
                intent = new Intent(this,MsgSettingActivity.class);
                break;
            case R.id.lin_about_seed:
                intent = new Intent(this,AboutSeedActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }


}
