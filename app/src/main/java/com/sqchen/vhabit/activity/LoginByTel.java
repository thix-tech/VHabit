package com.sqchen.vhabit.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sqchen.vhabit.R;
import com.sqchen.vhabit.bean.User;
import com.sqchen.vhabit.util.MyDatabaseHelper;
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

    private MyDatabaseHelper mDatabaseHelper;

    private SQLiteDatabase mDatabase;

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
                            //创建数据库和表User，将用户信息存入数据库
                            saveUserInfo(list.get(0));
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            mDlgLoading.dismiss();
                        } else {
                            ToastUtil.toastShow(getApplicationContext(),getString(R.string.str_login_failed));
                        }
                    } else if(list.size() == 0){
                        ToastUtil.toastShow(getApplicationContext(),getString(R.string.str_inexistent_tel));
                    }
                } else {
                    ToastUtil.toastShow(getApplicationContext(),getString(R.string.str_network_error) + e.getErrorCode());
                }
                mDlgLoading.dismiss();
            }
        });

    }

    private void getInput() {
        strAccount = editAccount.getText().toString();
        strPassword = editPassword.getText().toString();
    }

    /**
     * 保存用户信息，有两种存储方式
     * 一、保存到数据库中，是为了以后方便保存图片
     * 二、保存到SharedPreferences文件中，数据库取值需要键值
     * @param user
     */
    private void saveUserInfo(User user) {
        //创建数据库UserInfo.db和表User
        mDatabaseHelper = new MyDatabaseHelper(LoginByTel.this,"UserInfo.db",null,1);
        mDatabase = mDatabaseHelper.getWritableDatabase();
        if(!isUserExist(mDatabase,user.getUserTel())) {
            //组装用户信息数据
            ContentValues values = new ContentValues();
            values.put("userAccount",user.getUserTel());
            values.put("userName",user.getUserName());
            values.put("userPassword",user.getUserPassword());
            mDatabase.insert("User",null,values);
        }

        //将用户信息保存到SharedPreferences中
        SharedPreferences.Editor editor = getSharedPreferences("userInfo",MODE_PRIVATE).edit();
        editor.putString("userName",user.getUserName());
        editor.putString("userAccount",user.getUserTel());
        editor.putString("userPassword",user.getUserPassword());
        editor.commit();
    }

    /**
     * 判断本地数据库中是否已保存用户信息
     * @param database
     * @param strAccount
     * @return
     */
    private boolean isUserExist(SQLiteDatabase database,String strAccount) {
        Cursor cursor = database.query("User",null,"userAccount = ?",new String[]{strAccount},null,null,null);
        if(cursor.getCount() == 1)
            return true;
        else
            return false;
    }

}
