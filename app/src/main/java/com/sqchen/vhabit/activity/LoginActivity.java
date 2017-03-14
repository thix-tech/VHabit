package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sqchen.vhabit.R;

import cn.bmob.v3.Bmob;

public class LoginActivity extends Activity {

    private Button btnLogByTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "233336c14a8442a8dce056a9021c7b4c");
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        btnLogByTel = (Button) findViewById(R.id.login_by_tel);
        btnLogByTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginByTel.class);
                startActivity(intent);
            }
        });

    }


}
