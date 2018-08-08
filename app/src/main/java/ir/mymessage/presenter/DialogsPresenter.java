package ir.mymessage.presenter;

import android.util.Log;

import java.util.ArrayList;

import ir.mymessage.model.local.DialogLocal;
import ir.mymessage.model.local.MessageLocal;
import ir.mymessage.model.local.UserLocal;
import ir.mymessage.model.response.FriendsResponse;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BasePresenter;
import ir.mymessage.view.interfaces.DialogsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogsPresenter extends BasePresenter {

    private final DialogsInterface dialogsInterface;

    public DialogsPresenter(DialogsInterface dialogsInterface) {
        this.dialogsInterface = dialogsInterface;
    }

    public void getDialogs() {
        apiService.dialogs(new MySharedPrefrences(dialogsInterface.getContext()).getUserInfo().getUserId())
                .enqueue(new Callback<ArrayList<FriendsResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<FriendsResponse>> call, Response<ArrayList<FriendsResponse>> response) {
                        if (response.isSuccessful()) {
                            ArrayList<UserLocal> userArrayList = new ArrayList<>();
                            ArrayList<DialogLocal> dialogArrayList = new ArrayList<>();
                            for (FriendsResponse friendsResponse : response.body()) {
                                Log.wtf("DialogsPresenter", friendsResponse.getFriendId());
                                UserLocal user = new UserLocal(friendsResponse.getFriendUserId(), friendsResponse.getFriendNickname(), null, false, friendsResponse.getFcmToken());
                                userArrayList.add(user);
                                dialogArrayList.add(new DialogLocal(friendsResponse.getFriendId(), friendsResponse.getFriendNickname(), null, userArrayList, new MessageLocal(friendsResponse.getFriendUserId(), user, "Tap to see messages"), 0));
                            }
                            DialogsPresenter.this.dialogsInterface.displayDialogs(dialogArrayList);
                        } else {
                            Log.wtf("DialogPresenter", "onResponse unSuccess: " + response.raw());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<FriendsResponse>> call, Throwable t) {
                        Log.wtf("DialogPresenter", "onFailure: " + t.toString());
                    }
                });
    }

}
