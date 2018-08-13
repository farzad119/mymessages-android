package ir.mymessage.view.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.mymessage.R;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import ir.mymessage.model.local.MessageLocal;
import ir.mymessage.model.local.UserLocal;
import ir.mymessage.model.remote.Message;
import ir.mymessage.model.remote.User;
import ir.mymessage.model.response.MessagesResponse;
import ir.mymessage.presenter.MessagesPresenter;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BaseActivity;
import ir.mymessage.view.interfaces.MessagesInterface;

public class MessagesActivity extends BaseActivity implements MessagesInterface {

    @BindView(R.id.messagesList)
    MessagesList messagesList;
    @BindView(R.id.input)
    MessageInput input;
    @BindView(R.id.tv_sending_message)
    TextView tvSendingMessage;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public static boolean isMessageActivityRunning = false;
    MessagesPresenter presenter;
    MessagesListAdapter<MessageLocal> adapter;
    BroadcastReceiver broadcastReceiver;
    String friendFcmToken;
    String friendId;
    String friendNickname;
    String friendUserId;

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_messages;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        friendId = getIntent().getStringExtra("friend_id");
        friendUserId = getIntent().getStringExtra("friend_user_id");
        friendNickname = getIntent().getStringExtra("friend_nickname");
        friendFcmToken = getIntent().getStringExtra("friend_fcm_token");
        presenter = new MessagesPresenter(this);
        setupMessagesActivity();
    }

    @Override
    public void addMessage(String content, String userId) {
        if (this.adapter != null) {
            this.adapter.addToStart(new MessageLocal("", new UserLocal(userId, null, null, false, ""), content), true);
        }
    }

    @Override
    public void setupMessagesActivity() {
        adapter = new MessagesListAdapter<>(new MySharedPrefrences(getContext()).getUserInfo().getUserId()
                , new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                //Glide.with(MessagesActivity.this).load(url).into(imageView);
            }
        });

        presenter.getMessages(friendId);

        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                MessagesActivity.this.addMessage(input.toString(), new MySharedPrefrences(MessagesActivity.this.getContext()).getUserInfo().getUserId());
                presenter.sendMessage(input.toString(),friendFcmToken,friendId,friendUserId);
                visibleSendingStatus();
                return true;
            }
        });

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.wtf("MessageActivity", "Broadcast onReceive: "+ intent.getStringExtra("pushMessage"));

                addMessage(intent.getStringExtra("pushMessage"), intent.getStringExtra("userId"));
            }
        };

        this.presenter.clearNotification(friendId);

        tvTitle.setText(friendNickname);

    }

    @Override
    public void displayMessages(ArrayList<MessageLocal> messageArrayList) {
        messagesList.setAdapter(adapter);
        Log.wtf("MessageActivity", "onResponse: "+messageArrayList.get(0).getText());

        adapter.addToEnd(messageArrayList, true);
    }

    @Override
    public void visibleSendingStatus() {
        tvSendingMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSendingStatus() {
        tvSendingMessage.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return MessagesActivity.this;
    }

    public void onStart() {
        super.onStart();
        isMessageActivityRunning = true;
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.broadcastReceiver, new IntentFilter("messages"));
    }

    public void onStop() {
        super.onStop();
        isMessageActivityRunning = false;
    }

}
