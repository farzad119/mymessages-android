package ir.mymessage.presenter;

import android.util.Log;

import java.util.ArrayList;

import ir.mymessage.model.local.DialogLocal;
import ir.mymessage.model.local.MessageLocal;
import ir.mymessage.model.local.UserLocal;
import ir.mymessage.model.remote.Friend;
import ir.mymessage.model.remote.User;
import ir.mymessage.model.response.DialogsResponse;
import ir.mymessage.model.response.FriendsResponse;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.base.BasePresenter;
import ir.mymessage.view.interfaces.DialogsInterface;
import ir.mymessage.view.interfaces.FriendsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendsPresenter extends BasePresenter {

    private final FriendsInterface friendsInterface;

    public FriendsPresenter(FriendsInterface friendsInterface) {
        this.friendsInterface = friendsInterface;
    }

    public void getFriends() {
        friendsInterface.showProgress();
        apiService.allFriends(new MySharedPrefrences(friendsInterface.getContext()).getUserInfo().getUserId())
                .enqueue(new Callback<ArrayList<FriendsResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<FriendsResponse>> call, Response<ArrayList<FriendsResponse>> response) {
                        friendsInterface.hideProgress();
                        if (response.isSuccessful()) {
                            friendsInterface.displayAllFriends(response.body());
                            Log.wtf("FriendPresenter", "onResponse: succ");
                        } else {
                            Log.wtf("FriendPresenter", "onResponse: not succ");
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<FriendsResponse>> call, Throwable t) {
                        friendsInterface.hideProgress();
                        Log.wtf("FriendPresenter", "onFailure: " + t.getMessage());
                    }
                });
    }

    public void addFriend(User user) {
        friendsInterface.showProgress();
        Friend friend = new Friend();
        friend.setUserId(new MySharedPrefrences(friendsInterface.getContext()).getUserId());
        friend.setNickname(new MySharedPrefrences(friendsInterface.getContext()).getUserInfo().getNickname());
        friend.setFriendUserId(user.getUserId());
        friend.setFriendNickname(user.getNickname());

        apiService.addFriend(friend).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                friendsInterface.hideProgress();
                if (response.isSuccessful()) {
                    Log.wtf("FriendPresenter", "addFriend onResponse: succ : " + response.body());
                    getFriends();
                } else {
                    Log.wtf("FriendPresenter", "addFriend onResponse: not suc because : " + response.raw());
                }
                friendsInterface.hideProgress();
                getFriends();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                friendsInterface.hideProgress();
            }
        });
    }

    public void deleteFriend(Friend friend) {
        friendsInterface.showProgress();
        apiService.deleteFriend(friend)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        friendsInterface.hideProgress();
                        if (response.isSuccessful()) {
                            getFriends();
                            Log.wtf("FriendPresenter", "clickOnDeleteFriend onResponse: succ : " + response.body());
                        } else {
                            Log.wtf("FriendPresenter", "clickOnDeleteFriend onResponse: not suc because : " + response.raw());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        friendsInterface.hideProgress();
                        Log.wtf("FriendPresenter", "clickOnDeleteFriend onFailure: " + t.getMessage());
                    }
                });
    }

    public void searchFriend(String username) {
        friendsInterface.showProgress();
        apiService.searchFriend(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                friendsInterface.hideProgress();
                if (response.isSuccessful()) {
                    friendsInterface.showDialogAddFriend(response.body());
                    Log.wtf("FriendPresenter", "onResponse: " + "suc");
                } else {
                    Log.wtf("FriendPresenter", "onResponse: " + "not suc, because :" + response.raw());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                friendsInterface.hideProgress();
                Log.wtf("FriendPresenter", "onResponse: " + "failure because : " + t.toString());

            }
        });
    }
}
