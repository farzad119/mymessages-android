package ir.mymessage.view.activities;

import android.content.Context;
import android.content.Intent;
import android.mymessage.R;
import ir.mymessage.presenter.LoginPresenter;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BaseActivity;
import ir.mymessage.view.interfaces.LoginInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginInterface {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private LoginPresenter presenter;


    @Override
    protected int getContentViewRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginPresenter(this);
        setupLoginActivity();
    }

    @Override
    public String getUserName() {
        return etUsername.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void setupLoginActivity() {
        if (new MySharedPrefrences(LoginActivity.this).isLoggedIn()){
            startDialogsActivity();
        }else {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLoginClicked();
                }
            });
        }
    }

    @Override
    public void onLoginClicked() {
        presenter.onLoginClicked();
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }

    @Override
    public void startDialogsActivity() {

        startActivity(new Intent(LoginActivity.this, DialogsActivity.class));
        finish();
    }

    @Override
    public void showUsernamePasswordError() {
        Toast.makeText(this, "نام کاربری یا کلمه عبور اشتباه است", Toast.LENGTH_SHORT).show();
    }
}
