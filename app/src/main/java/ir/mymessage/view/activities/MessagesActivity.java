package ir.mymessage.view.activities;

import android.content.Context;
import android.mymessage.R;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import ir.mymessage.model.local.MessageLocal;
import ir.mymessage.presenter.MessagesPresenter;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BaseActivity;
import ir.mymessage.view.interfaces.MessagesInterface;

public class MessagesActivity extends BaseActivity implements MessagesInterface {

    @BindView(R.id.messagesList)
    MessagesList messagesList;

    MessagesPresenter presenter;
    MessagesListAdapter<MessageLocal> adapter;

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_messages;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MessagesPresenter(this);
        setupMessagesActivity();
    }

    @Override
    public void setupMessagesActivity() {
        adapter = new MessagesListAdapter<>(new MySharedPrefrences(getContext()).getUserInfo().getUserId()
                , new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Glide.with(MessagesActivity.this).load(url).into(imageView);
            }
        });

        presenter.getMessages();

    }

    @Override
    public void displayMessages(ArrayList<MessageLocal> messageArrayList) {
        messagesList.setAdapter(adapter);
        adapter.addToEnd(messageArrayList, true);
    }

    @Override
    public Context getContext() {
        return MessagesActivity.this;
    }

}
