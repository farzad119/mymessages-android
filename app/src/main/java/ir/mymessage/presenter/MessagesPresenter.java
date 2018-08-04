package ir.mymessage.presenter;

import android.util.Log;

import java.util.ArrayList;

import ir.mymessage.model.local.MessageLocal;
import ir.mymessage.model.local.UserLocal;
import ir.mymessage.model.remote.Message;
import ir.mymessage.model.response.MessagesResponse;
import ir.mymessage.model.response.SendMessageResponse;
import ir.mymessage.utils.GeneralUtils;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BasePresenter;
import ir.mymessage.view.interfaces.DialogsInterface;
import ir.mymessage.view.interfaces.MessagesInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesPresenter extends BasePresenter {

    private final MessagesInterface messagesInterface;
    private GeneralUtils generalUtils;

    public MessagesPresenter(MessagesInterface messagesInterface) {
        this.messagesInterface = messagesInterface;
        generalUtils = new GeneralUtils(messagesInterface.getContext());
    }

    public void getMessages(final String friendId) {
        Log.wtf("MessagesPresenter", "friendId : " + friendId);

        apiService.messages(friendId).enqueue(new Callback<ArrayList<MessagesResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<MessagesResponse>> call, Response<ArrayList<MessagesResponse>> response) {
                if (response.isSuccessful() && response.body().size() > 0) {
                    ArrayList<MessageLocal> messageArrayList = new ArrayList<>();
                    for (MessagesResponse messagesResponse : response.body()) {
                        UserLocal user = new UserLocal(messagesResponse.getUserId(), "my name", null, false);
                        MessageLocal message = new MessageLocal(messagesResponse.getId()
                                , user
                                , messagesResponse.getContent()
                                ,generalUtils.dateParser(messagesResponse.getCreatedAt()));
                        messageArrayList.add(message);
                    }
                    messagesInterface.displayMessages(messageArrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MessagesResponse>> call, Throwable t) {
                Log.wtf("MessagePresenter", "onFailure");
            }
        });
    }

    public void sendMessage(String content, String friendId, String toUserId) {
        Message message = new Message();
        message.setUserId(new MySharedPrefrences(messagesInterface.getContext()).getUserInfo().getUserId());
        message.setToUserId(toUserId);
        message.setFriendId(friendId);
        message.setNickname(new MySharedPrefrences(messagesInterface.getContext()).getUserInfo().getNickname());
        message.setContent(content);

        apiService.sendMessage(message).enqueue(new Callback<SendMessageResponse>() {
            @Override
            public void onResponse(Call<SendMessageResponse> call, Response<SendMessageResponse> response) {
                if (response.isSuccessful()) {
                    messagesInterface.hideSendingStatus();
                }
            }

            @Override
            public void onFailure(Call<SendMessageResponse> call, Throwable t) {
                Log.wtf("MessagePresenter", "onFailure : " + t.toString());
            }
        });

    }
}
