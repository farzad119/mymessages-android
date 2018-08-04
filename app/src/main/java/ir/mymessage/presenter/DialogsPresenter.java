package ir.mymessage.presenter;

import android.util.Log;
import android.widget.ImageView;

import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

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
                            String userId = new MySharedPrefrences(dialogsInterface.getContext()).getUserInfo().getUserId();
                            ArrayList<UserLocal> userArrayList = new ArrayList<>();
                            ArrayList<DialogLocal> dialogArrayList = new ArrayList<>();

                            for (FriendsResponse friendsResponse : response.body()) {
                                UserLocal user = new UserLocal(
                                        friendsResponse.getFriendUserId()
                                        , friendsResponse.getFriendNickname()
                                        , null
                                        , false);
                                userArrayList.add(user);

                                MessageLocal message = new MessageLocal(friendsResponse.getFriendUserId(), user, "Tap to see messages");

                                DialogLocal dialog;
                                if (userId.equals(friendsResponse.getUserId())){
                                    dialog = new DialogLocal(
                                            friendsResponse.getFriendId()
                                            , friendsResponse.getFriendNickname()
                                            , null
                                            , userArrayList
                                            , message
                                            , 0);
                                }else {
                                    dialog = new DialogLocal(
                                            friendsResponse.getFriendId()
                                            , friendsResponse.getNickname()
                                            , null
                                            , userArrayList
                                            , message
                                            , 0);
                                }
                                dialogArrayList.add(dialog);
                            }

                            dialogsInterface.displayDialogs(dialogArrayList);

                        } else {
                            Log.wtf("DialogPresenter", "onResponse unSuccess: " + response.raw());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<FriendsResponse>> call, Throwable t) {
                        Log.wtf("DialogPresenter", "onFailure: " + t.toString());
                    }
                });

        /*ArrayList<DialogLocal> dialogArrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            UserLocal user = new UserLocal("852", "farzad", "test", false);
            ArrayList<UserLocal> userArrayList = new ArrayList<>();
            userArrayList.add(user);

            MessageLocal message = new MessageLocal("741", user, "Hi");

            DialogLocal dialog = new DialogLocal("852", "farzad", "", userArrayList, message, 2);
            dialogArrayList.add(dialog);
        }

        DialogsListAdapter<DialogLocal> dialogsListAdapter = new DialogsListAdapter<>(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Log.wtf("Dialogs", "loadImage: " + url);
            }
        });

        dialogsListAdapter.addItems(dialogArrayList);
        dialogsList.setAdapter(dialogsListAdapter);*/

    }

}
