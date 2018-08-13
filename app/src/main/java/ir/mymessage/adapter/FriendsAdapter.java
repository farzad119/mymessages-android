package ir.mymessage.adapter;

import android.mymessage.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.mymessage.model.remote.Friend;
import ir.mymessage.model.response.FriendsResponse;
import ir.mymessage.utils.MySharedPrefrences;
import ir.mymessage.view.interfaces.FriendsInterface;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ExampleHolder> {

    private ArrayList<FriendsResponse> friendsArrayList;
    private int itemLayout;
    private FriendsInterface friendsInterface;

    public FriendsAdapter(FriendsInterface friendsInterface, ArrayList<FriendsResponse> friendsArrayList, int itemLayout) {
        this.friendsArrayList = friendsArrayList;
        this.itemLayout = itemLayout;
        this.friendsInterface = friendsInterface;
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExampleHolder holder, final int position) {
        final FriendsResponse friend = friendsArrayList.get(position);
        holder.tvTitle.setText(friend.getFriendNickname());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendsInterface.clickOnDeleteFriend(
                        new MySharedPrefrences(friendsInterface.getContext()).getUserId()
                        ,friend.getFriendId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return friendsArrayList.size();
    }

    public class ExampleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvTitle;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        ExampleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
