package com.jsn.msnhope.ui.multiple_view_types

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jsn.msnhope.R
import com.jsn.msnhope.data.local.entities.UserEntity
import com.jsn.msnhope.databinding.ItemUserListBinding
import com.jsn.msnhope.ui.user.UserListAdapter

class MultipleViewTypesAdapter(context: Context, val users: List<UserEntity>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val context: Context = context

    // Using Data Binding
    inner class ItemUserListViewHolder(binding: ItemUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val userListRv: RecyclerView = binding.userRecyclerView

        init {
            userListRv.setHasFixedSize(true)
            userListRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bind(userListAdapter: UserListAdapter) {
            userListRv.adapter = userListAdapter
        }

    }

    inner class ItemBannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bannerIv: ImageView = itemView.findViewById(R.id.banner_iv)
    }

    //Using Layout
//    inner class ItemUserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val userListRv: RecyclerView = itemView.findViewById(R.id.user_recycler_view)
//
//        init {
//            userListRv.setHasFixedSize(true)
//            userListRv.layoutManager = LinearLayoutManager(context)
//        }
//
//        fun bind(userListAdapter: UserListAdapter) {
//            userListRv.adapter = userListAdapter
//        }
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // OR
//        return when (viewType) {
//            R.layout.item_user_list -> {
////                val view = LayoutInflater.from(parent.context)
////                    .inflate(R.layout.item_user_list, parent, false)
////                return ItemUserListViewHolder(view)
//                val binding =
//                    ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                ItemUserListViewHolder(binding)
//            }
//
//            else -> {
//                throw IllegalArgumentException("Invalid view type")
//            }
//        }
        return when (viewType) {
            0 -> {
//                val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_user_list, parent, false)
//                return ItemUserListViewHolder(view)
                val binding =
                    ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemUserListViewHolder(binding)
            }

            1 -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
                return ItemBannerViewHolder(view)
            }

            else -> {
                throw IllegalArgumentException("Invalid view type")
            }
        }
    }

    override fun getItemCount(): Int {
        return 2;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemUserListViewHolder -> {
                val userListAdapter = UserListAdapter(users)
                holder.bind(userListAdapter)

//                holder.itemView.setOnClickListener{
//                    Log.d("checkBannerClick2", "List is clicked")
//                }
            }

            is ItemBannerViewHolder -> {
                Log.d("MultipleViewTypesAdap", "Binding banner at position $position")
                holder.bannerIv.setImageResource(R.drawable.ic_user)
                holder.bannerIv.setOnClickListener {
                    Log.d("checkBannerClick", "I am clicked")
                }
            }
        }
    }

    //OR => Using This 'getItemViewType' for Manipulating View Types
    // You have to use this method when want to use more than one view type.
    override fun getItemViewType(position: Int): Int {
//        return when (position) {
//            0 -> return R.layout.item_user_list
//            1 -> R.layout.item_banner
//            else -> {
//                throw IllegalArgumentException("Invalid view type")
//            }
//        }


        //OR
        return position;
    }


}