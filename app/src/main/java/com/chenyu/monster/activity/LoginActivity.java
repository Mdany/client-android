package com.chenyu.monster.activity;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chenyu.monster.R;
import com.chenyu.monster.framework.BaseActivity;
import com.chenyu.monster.util.SnackUtil;

/**
 * Created by chenyu on 16/1/29.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextInputLayout nameLayout;
    private TextInputLayout passwordLayout;
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
        nameLayout = (TextInputLayout) findViewById(R.id.username_til);
        passwordLayout = (TextInputLayout) findViewById(R.id.password_til);
        username = (EditText) findViewById(R.id.username_edt);
        password = (EditText) findViewById(R.id.password_edt);
        login = (Button) findViewById(R.id.login_btn);
        register = (Button) findViewById(R.id.register_btn);

        nameLayout.setHint(mContext.getString(R.string.login_username));
        passwordLayout.setHint(mContext.getString(R.string.login_password));

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        final String nameError = mContext.getString(R.string.login_username_error);
        final String passwordError = mContext.getString(R.string.login_password_error);

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validName(s.toString())) {
                    nameLayout.setErrorEnabled(false);
                } else {
                    nameLayout.setErrorEnabled(true);
                    nameLayout.setError(nameError);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validPassword(s.toString())) {
                    passwordLayout.setErrorEnabled(false);
                } else {
                    passwordLayout.setErrorEnabled(true);
                    passwordLayout.setError(passwordError);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.login_btn:
                boolean isOk = validName(username.getText().toString().trim()) && validPassword(password.getText().toString().trim());
                if (isOk) {
                    //TODO request login API
                }
                break;
            case R.id.register_btn:
                break;
        }
    }

    private boolean validName(String name) {
        return !TextUtils.isEmpty(name) && Patterns.EMAIL_ADDRESS.matcher(name).matches();
    }

    private boolean validPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        } else {
            int length = password.length();
            return length >= 6 && length <= 10;
        }
    }
}
