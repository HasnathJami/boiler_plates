package com.jsn.msnhope.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jsn.msnhope.R
import com.jsn.msnhope.data.remote.model.Product
import com.jsn.msnhope.databinding.SingleItemUserBinding

class ProductListAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: SingleItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)

//    inner class UserViewHolder2(val itemView: View): RecyclerView.ViewHolder(itemView) {
//        val userName = itemView.findViewById<TextView>(R.id.tv_user_name)
//        val userDescription = itemView.findViewById<TextView>(R.id.tv_user_description)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            SingleItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.tvUserName.text = product._title
        holder.binding.tvUserDescription.text = product.description

        Glide
            .with(holder.itemView.context)
            .load(product._image)
            .error(R.drawable.ic_launcher_background)
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
            .into(holder.binding.ivUser)

        //Adjust margins dynamically
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
        when(position) {
            0 -> {
                layoutParams.setMargins(20,0,10,0)
            }
            products.size - 1 -> {
                layoutParams.setMargins(10,0,20,0)
            }
            else -> {
                layoutParams.setMargins(10,0,10,0)
            }
        }

    }

    override fun onViewRecycled(holder: ProductViewHolder) {
        super.onViewRecycled(holder)
    }
}