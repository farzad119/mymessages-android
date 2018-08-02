package ir.mymessage.view.activities;

import android.content.Intent;
import android.mymessage.R;
import ir.mymessage.presenter.LoginPresenter;
import ir.mymessage.view.base.BaseActivity;
import ir.mymessage.view.interfaces.LoginInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClicked();
            }
        });
    }

    @Override
    public void onLoginClicked() {
        presenter.onLoginClicked();
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void showUsernamePassword() {
        Toast.makeText(this, getUserName()+"-"+getPassword(), Toast.LENGTH_SHORT).show();
    }
}
