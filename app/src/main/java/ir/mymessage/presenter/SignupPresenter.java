package ir.mymessage.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import ir.mymessage.model.remote.User;
import ir.mymessage.model.response.LoginResponse;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BasePresenter;
import ir.mymessage.view.interfaces.LoginInterface;
import ir.mymessage.view.interfaces.SignupInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupPresenter extends BasePresenter {

    private final SignupInterface signupInterface;

    public SignupPresenter(SignupInterface signupInterface) {
        this.signupInterface = signupInterface;
    }

    public void signup() {
        User user = new User();
        user.setNickname(signupInterface.getNickname());
        user.setUsername(signupInterface.getUsername());
        user.setPassword(signupInterface.getPassword());
        user.setEmail(signupInterface.getEmail());
        signupInterface.showProgress();
        apiService.signup(user).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                signupInterface.hideProgress();
                if (response.isSuccessful()) {
                    Log.wtf("SignupPresenter", "onResponse: suc" );
                    if (response.body().getStatus()) {
                        Log.wtf("SignupPresenter", "onResponse: status true" );
                        User user = new User();
                        user.setUserId(response.body().getUser().getUserId());
                        user.setUsername(response.body().getUser().getUsername());
                        user.setNickname(response.body().getUser().getNickname());
                        new MySharedPrefrences(signupInterface.getContext()).saveUserInfo(user);
                        new MySharedPrefrences(signupInterface.getContext()).login();
                        signupInterface.startDialogsActivity();
                    } else {
                        Log.wtf("SignupPresenter", "onResponse: status false" );
                        signupInterface.showPasswordMatchError();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.wtf("SignupPresenter", "onFailure: because :"+t.toString() );
                signupInterface.hideProgress();
            }
        });
    }

    public boolean checkPassword() {
        return signupInterface.getPassword().equals(signupInterface.getRepeatPassword());
    }

    public boolean checkEmptyFields() {
        if (signupInterface.getNickname().isEmpty()
                || signupInterface.getUsername().isEmpty()
                || signupInterface.getPassword().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
