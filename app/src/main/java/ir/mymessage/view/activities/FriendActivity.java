package ir.mymessage.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.mymessage.R;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import ir.mymessage.adapter.FriendsAdapter;
import ir.mymessage.model.remote.Friend;
import ir.mymessage.model.remote.User;
import ir.mymessage.model.response.FriendsResponse;
import ir.mymessage.presenter.FriendsPresenter;
import ir.mymessage.view.base.BaseActivity;
import ir.mymessage.view.interfaces.FriendsInterface;

public class FriendActivity extends BaseActivity implements FriendsInterface {

    @BindView(R.id.rv_friends)
    RecyclerView rvFriends;
    @BindView(R.id.rtl_progress)
    RelativeLayout rtlProgress;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.btn_search)
    Button btnSearch;

    FriendsPresenter presenter;

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_friend;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FriendsPresenter(this);
        setupMessagesActivity();
    }

    @Override
    public void setupMessagesActivity() {
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
        presenter.getFriends();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnSearch();
            }
        });
    }

    @Override
    public void clickOnAddFriend(String userId, String friendUserId) {

    }

    @Override
    public void clickOnDeleteFriend(String userId, String friendId) {
        Friend friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        presenter.deleteFriend(friend);
    }

    @Override
    public void showDialogAddFriend(final User user) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Are you sure add " + user.getNickname()+ " to your friends?");
        dialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                presenter.addFriend(user);
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dialog.show();
    }

    @Override
    public void showProgress() {
        rtlProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        rtlProgress.setVisibility(View.GONE);
    }

    @Override
    public String getTextUsername() {
        return etSearch.getText().toString().trim();
    }

    @Override
    public void displayAllFriends(ArrayList<FriendsResponse> friendArrayList) {
        FriendsAdapter adapter = new FriendsAdapter(this, friendArrayList, R.layout.item_friend);
        rvFriends.setAdapter(adapter);
    }

    @Override
    public void clickOnSearch() {
        presenter.searchFriend(getTextUsername());
    }

    @Override
    public Context getContext() {
        return this;
    }
}
