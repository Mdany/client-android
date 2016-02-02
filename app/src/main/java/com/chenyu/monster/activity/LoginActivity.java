package com.chenyu.monster.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseActivity;

/**
 * Created by chenyu on 16/1/29.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected int getLayoutId() {
        return R.layout.a_login;
    }

    @Override
    protected void initView() {
        username = (EditText) findViewById(R.id.username_edt);
        password = (EditText) findViewById(R.id.password_edt);
        login = (Button) findViewById(R.id.login_btn);
        register = (Button) findViewById(R.id.register_btn);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_btn:
                break;
            case R.id.register_btn:
                break;
        }
    }
}
