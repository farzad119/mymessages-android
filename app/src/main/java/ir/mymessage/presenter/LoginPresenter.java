package ir.mymessage.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import ir.mymessage.model.response.LoginResponse;
import ir.mymessage.model.remote.User;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BasePresenter;
import ir.mymessage.view.interfaces.LoginInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter {

    private final LoginInterface loginInterface;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }

    public void onLoginClicked() {
        User login = new User();
        login.setUsername(loginInterface.getUserName());
        login.setPassword(loginInterface.getPassword());
        loginInterface.showProgress();
        apiService.login(login).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginInterface.hideProgress();
                if (response.isSuccessful()) {
                    if (response.body().getStatus()) {
                        User user = new User();
                        user.setUserId(response.body().getUser().getUserId());
                        user.setUsername(response.body().getUser().getUsername());
                        user.setNickname(response.body().getUser().getNickname());

                        new MySharedPrefrences(loginInterface.getContext()).saveUserInfo(user);
                        new MySharedPrefrences(loginInterface.getContext()).login();
                        loginInterface.startDialogsActivity();
                    } else {
                        loginInterface.showUsernamePasswordError();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.wtf("LoginPresenter", "onResponse Failure: " + t.toString());

            }
        });
    }
}
