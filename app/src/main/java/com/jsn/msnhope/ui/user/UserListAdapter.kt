package com.jsn.msnhope.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jsn.msnhope.data.local.entities.UserEntity
import com.jsn.msnhope.databinding.SingleItemUserBinding

class UserListAdapter(val users: List<UserEntity>) :
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: SingleItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

//    inner class UserViewHolder2(val itemView: View): RecyclerView.ViewHolder(itemView) {
//        val userName = itemView.findViewById<TextView>(R.id.tv_user_name)
//        val userDescription = itemView.findViewById<TextView>(R.id.tv_user_description)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            SingleItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.tvUserName.text = user.userName
        holder.binding.tvUserDescription.text = user.description
    }

    override fun onViewRecycled(holder: UserViewHolder) {
        super.onViewRecycled(holder)
    }
}