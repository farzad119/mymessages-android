package ir.mymessage.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;

import ir.mymessage.model.local.DialogLocal;
import ir.mymessage.model.local.MessageLocal;
import ir.mymessage.model.local.UserLocal;
import ir.mymessage.model.response.DialogsResponse;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BasePresenter;
import ir.mymessage.view.interfaces.DialogsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogsPresenter extends BasePresenter {

    private final DialogsInterface dialogsInterface;
    private String avatar = "https://image.flaticon.com/icons/png/128/149/149071.png";

    public DialogsPresenter(DialogsInterface dialogsInterface) {
        this.dialogsInterface = dialogsInterface;
    }

    public void getDialogs() {
        apiService.dialogs(new MySharedPrefrences(dialogsInterface.getContext()).getUserInfo().getUserId())
                .enqueue(new Callback<ArrayList<DialogsResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<DialogsResponse>> call, Response<ArrayList<DialogsResponse>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<UserLocal> userArrayList = new ArrayList<>();
                            ArrayList<DialogLocal> dialogArrayList = new ArrayList<>();
                            for (DialogsResponse dialogsResponse : response.body()) {
                                Log.wtf("DialogsPresenter", dialogsResponse.getFriendId());
                                UserLocal user = new UserLocal(dialogsResponse.getFriendUserId(), dialogsResponse.getFriendNickname(), null, false, dialogsResponse.getFcmToken());
                                userArrayList.add(user);
                                dialogArrayList.add(new DialogLocal(dialogsResponse.getFriendId(), dialogsResponse.getFriendNickname(), avatar, userArrayList, new MessageLocal(dialogsResponse.getFriendUserId(), user, "Tap to see messages"), 0));
                            }
                            dialogsInterface.displayDialogs(dialogArrayList);
                        } else {
                            Log.wtf("DialogPresenter", "onResponse unSuccess: " + response.raw());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<DialogsResponse>> call, Throwable t) {
                        Log.wtf("DialogPresenter", "onFailure: " + t.toString());
                    }
                });
    }

    public void updateFcmToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {
                    apiService.updateFcmToken(new MySharedPrefrences(dialogsInterface.getContext()).getUserInfo().getUserId()
                            , task.getResult().getToken()).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful() && (response.body()).equals("true")) {
                                new MySharedPrefrences(dialogsInterface.getContext()).saveFcmToken();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    return;
                }
                Log.w("LoginActivity", "getInstanceId failed", task.getException());
            }
        });


    }

}
