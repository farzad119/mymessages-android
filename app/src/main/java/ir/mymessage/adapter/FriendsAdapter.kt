package ir.mymessage.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ir.mymessage.model.response.FriendsResponse
import ir.mymessage.utils.MySharedPrefrences
import ir.mymessage.view.interfaces.FriendsInterface
import kotlinx.android.synthetic.main.item_friend.view.*
import java.util.*

class FriendsAdapter(private val friendsInterface: FriendsInterface, private val friendsArrayList: ArrayList<FriendsResponse>, private val itemLayout: Int) : RecyclerView.Adapter<FriendsAdapter.ExampleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleHolder {
        val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
        return ExampleHolder(view)
    }

    override fun onBindViewHolder(holder: ExampleHolder, position: Int) {
        val friend = friendsArrayList[position]
        holder.tvTitle.text = friend.friendNickname
        holder.ivDelete.setOnClickListener {
            friendsInterface.clickOnDeleteFriend(
                    MySharedPrefrences(friendsInterface.getContext()).userId, friend.friendId?:"")
        }
    }

    override fun getItemCount(): Int {
        return friendsArrayList.size
    }

    inner class ExampleHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.tv_name
        val ivDelete: ImageView = itemView.iv_delete
    }
}
