package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.bean.User;
import com.sqchen.vhabit.widget.CustomTitleView;
import com.sqchen.vhabit.widget.DlgLoading;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends Activity {

    private CustomTitleView mTitleView;

    private EditText editName;

    private EditText editTel;

    private EditText editPassword;

    private Button btnRegister;

    private String strName;

    private String strTel;

    private String strPassword;

    private DlgLoading mDlgLoading;

    private TextWatcher txtWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!s.toString().equals("")) {
                btnRegister.setClickable(true);
                btnRegister.setPressed(true);
            } else {
                btnRegister.setClickable(false);
                btnRegister.setPressed(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter(getString(R.string.str_register));
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDlgLoading = new DlgLoading(RegisterActivity.this);

        editName = (EditText) findViewById(R.id.edit_name);
        editTel = (EditText) findViewById(R.id.edit_tel);
        editPassword = (EditText) findViewById(R.id.edit_password);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });
        editTel.addTextChangedListener(txtWatcher);
    }

    private void userRegister() {
        mDlgLoading.show(getString(R.string.str_is_registering));
        getInput();
        User user = new User();
        user.setUserName(strName);
        user.setUserTel(strTel);
        user.setUserPassword(strPassword);
        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null) {
                    Toast.makeText(getApplicationContext(),
                            R.string.str_register_success,
                            Toast.LENGTH_SHORT).show();
                    mDlgLoading.dismiss();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.str_register_failed,
                            Toast.LENGTH_SHORT).show();
                    mDlgLoading.dismiss();
                }
            }
        });
    }

    private void getInput() {
        strName = editName.getText().toString();
        strTel = editTel.getText().toString();
        strPassword = editPassword.getText().toString();
    }



}
