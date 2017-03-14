package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.TextView;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.widget.CustomTitleView;

public class WalletActivity extends Activity {

    private CustomTitleView mTitleView;

    private TextView mRestMoneyTxt;

    private TextPaint mTextPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        initViews();
    }

    private void initViews() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("我的钱包");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTitleView.setTxtRight("明细");
        mTitleView.setTxtRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MoneyDetails.class);
                startActivity(intent);
            }
        });

        mRestMoneyTxt = (TextView) findViewById(R.id.txt_rest_money);
        mTextPaint = mRestMoneyTxt.getPaint();
        mTextPaint.setFakeBoldText(true);
    }
}
