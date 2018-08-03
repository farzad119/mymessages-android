package ir.mymessage.view.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import ir.mymessage.service.ApiClient;
import ir.mymessage.service.ApiInterface;

public abstract class BaseActivity extends AppCompatActivity {

    public static int NO_RES = 0;

    @LayoutRes
    protected abstract int getContentViewRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewRes());
        ButterKnife.bind(this);
    }

}