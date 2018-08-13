package ir.mymessage.view.activities;

import android.content.Context;
import android.content.Intent;
import android.mymessage.R;

import butterknife.BindView;
import ir.mymessage.model.local.DialogLocal;
import ir.mymessage.presenter.DialogsPresenter;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BaseActivity;
import ir.mymessage.view.interfaces.DialogsInterface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import java.util.ArrayList;

public class DialogsActivity extends BaseActivity implements DialogsInterface {

    @BindView(R.id.dialogsList)
    DialogsList dialogsList;
    @BindView(R.id.btn_addfriend)
    Button btnAddFriend;
    DialogsPresenter presenter;
    DialogsListAdapter<DialogLocal> dialogsListAdapter;

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_dialogs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new DialogsPresenter(this);
        setupDialogsActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getDialogs();
    }

    @Override
    public void startFriendsActivity() {
        startActivity(new Intent(DialogsActivity.this, FriendActivity.class));
    }

    @Override
    public void startMessagesActivity(DialogLocal dialogLocal) {
        Intent intent = new Intent(this, MessagesActivity.class);
        intent.putExtra("friend_id", dialogLocal.getId());
        intent.putExtra("friend_nickname", dialogLocal.getDialogName());
        intent.putExtra("friend_user_id", (dialogLocal.getUsers().get(0)).getId());
        intent.putExtra("friend_fcm_token", (dialogLocal.getUsers().get(0)).getFcmToken());
        startActivity(intent);
    }

    @Override
    public void displayDialogs(ArrayList<DialogLocal> dialogArrayList) {
        dialogsListAdapter.clear();
        dialogsList.setAdapter(dialogsListAdapter);
        dialogsListAdapter.addItems(dialogArrayList);
    }

    @Override
    public Context getContext() {
        return DialogsActivity.this;
    }

    @Override
    public void setupDialogsActivity() {

        dialogsListAdapter = new DialogsListAdapter<>(new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                Glide.with(DialogsActivity.this).load(url).into(imageView);
            }
        });

        if (!new MySharedPrefrences(this).isSavedFcmToken()) {
            presenter.updateFcmToken();
        }

        presenter.getDialogs();

        dialogsListAdapter.setOnDialogClickListener(new DialogsListAdapter.OnDialogClickListener<DialogLocal>() {
            @Override
            public void onDialogClick(DialogLocal dialog) {
                startMessagesActivity(dialog);
            }
        });

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFriendsActivity();
            }
        });
    }
}
