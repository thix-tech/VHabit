package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.bean.User;
import com.sqchen.vhabit.util.ToastUtil;
import com.sqchen.vhabit.widget.CustomTitleView;
import com.sqchen.vhabit.widget.DlgLoading;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginByTel extends Activity {

    private CustomTitleView mTitleView;

    private EditText editAccount;

    private EditText editPassword;

    private Button btnLogin;

    private TextView mTxtRegister;

    private String strAccount;

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
                btnLogin.setClickable(true);
                btnLogin.setPressed(true);
            } else {
                btnLogin.setClickable(false);
                btnLogin.setPressed(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_tel);
        initView();
    }

    private void initView() {
        mTitleView = (CustomTitleView) findViewById(R.id.title_view);
        mTitleView.setImgLeft(R.drawable.ic_banner_back);
        mTitleView.setTxtCenter("手机登录");
        mTitleView.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDlgLoading = new DlgLoading(LoginByTel.this);
        editAccount = (EditText) findViewById(R.id.edit_login);
        editPassword = (EditText) findViewById(R.id.edit_password);
        btnLogin = (Button) findViewById(R.id.btn_login_by_tel);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

        }});

        editAccount.addTextChangedListener(txtWatcher);

        mTxtRegister = (TextView) findViewById(R.id.txt_register);
        mTxtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void userLogin() {
        mDlgLoading.show(getString(R.string.str_is_logining));
        getInput();
        BmobQuery<User> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("userAccount",strAccount);
        bmobQuery.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e == null) {
                    if(list.size() == 1) {
                        String userPassword = list.get(0).getUserPassword();
                        if(userPassword.equals(strPassword)) {
                            ToastUtil.toastShow(getApplicationContext(),getString(R.string.str_login_success));
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            mDlgLoading.dismiss();
                        } else {
                            ToastUtil.toastShow(getApplicationContext(),getString(R.string.str_login_failed));
                            mDlgLoading.dismiss();
                        }
                    } else if(list.size() == 0){
                        ToastUtil.toastShow(getApplicationContext(),getString(R.string.str_inexistent_tel));
                        mDlgLoading.dismiss();
                    }
                } else {
                    ToastUtil.toastShow(getApplicationContext(),getString(R.string.str_network_error) + e.getErrorCode());
                }
            }
        });

    }


    private void getInput() {
        strAccount = editAccount.getText().toString();
        strPassword = editPassword.getText().toString();
    }

}
