package ir.mymessage.view.activities;

import android.content.Context;
import android.content.Intent;
import android.mymessage.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import ir.mymessage.presenter.SignupPresenter;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BaseActivity;
import ir.mymessage.view.interfaces.SignupInterface;

public class SignupActivity extends BaseActivity implements SignupInterface {

    @BindView(R.id.rtl_progress)
    RelativeLayout rtlProgress;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repeat_password)
    EditText etRepeatPassword;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    SignupPresenter presenter;

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_signup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SignupPresenter(this);
        setupMessagesActivity();
    }

    @Override
    public void setupMessagesActivity() {
        if (new MySharedPrefrences(SignupActivity.this).isLoggedIn()) {
            startDialogsActivity();
        } else {
            btnSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (presenter.checkEmptyFields()) {
                        if (presenter.checkPassword()) {
                            onSignupClicked();
                        } else {
                            showPasswordMatchError();
                        }
                    } else {
                        showEmptyFieldsError();
                    }
                }
            });
        }
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginActivity();
            }
        });
    }

    @Override
    public void startDialogsActivity() {
        startActivity(new Intent(SignupActivity.this, DialogsActivity.class));
        finish();
    }

    @Override
    public void startLoginActivity() {
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public String getNickname() {
        return etNickname.getText().toString().trim();
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return etEmail.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public String getRepeatPassword() {
        return etRepeatPassword.getText().toString().trim();
    }

    @Override
    public void onSignupClicked() {
        presenter.signup();
    }

    @Override
    public void showProgress() {
        rtlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        rtlProgress.setVisibility(View.GONE);
    }

    @Override
    public void showPasswordMatchError() {
        Toast.makeText(this, "password is not match", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyFieldsError() {
        Toast.makeText(this, "Fields is Empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
