package ir.mymessage.view.activities;

import android.content.Intent;
import android.mymessage.R;
import ir.mymessage.view.base.BaseActivity;
import ir.mymessage.view.interfaces.MainActivityInterface;

import android.os.Bundle;

public class MainActivity extends BaseActivity implements MainActivityInterface {

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void startFriendsActivity() {
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }
}
