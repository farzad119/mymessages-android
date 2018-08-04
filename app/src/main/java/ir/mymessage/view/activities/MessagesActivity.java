package ir.mymessage.view.activities;

import android.content.Context;
import android.mymessage.R;
import android.os.Bundle;
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

    MessagesPresenter presenter;
    MessagesListAdapter<MessageLocal> adapter;
    String friendId;
    String friendUserId;
    String friendNickname;

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

        presenter = new MessagesPresenter(this);
        setupMessagesActivity();
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
                UserLocal user = new UserLocal(new MySharedPrefrences(getContext()).getUserInfo().getUserId()
                        , null
                        , null
                        , false);

                MessageLocal message = new MessageLocal("", user, input.toString());
                adapter.addToStart(message, true);
                presenter.sendMessage(input.toString(),friendId,friendUserId);
                visibleSendingStatus();
                return true;
            }
        });

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

}
