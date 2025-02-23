package com.jsn.msnhope.ui.product

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsn.msnhope.R
import com.jsn.msnhope.data.local.entities.UserEntity
import com.jsn.msnhope.data.remote.model.Product
import com.jsn.msnhope.data.remote.utils.Outcome
import com.jsn.msnhope.databinding.ActivityProductsBinding
import com.jsn.msnhope.databinding.ActivityUserBinding
import com.jsn.msnhope.ui.user.UserListAdapter
import com.jsn.msnhope.ui.user.UserViewModel

class ProductsActivity : AppCompatActivity() {
    private val viewModel: ProductsViewModel by viewModels()
    private lateinit var adapter: ProductListAdapter
    private lateinit var binding: ActivityProductsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_user)
        setContentView(binding.root)

        subscribeUiToRecyclerView()

     //   handleClickEvents()

    }

    private fun subscribeUiToRecyclerView() {
       viewModel.products.observe(this) { outcome ->
           when(outcome) {
               is Outcome.Loading -> {
                   // Handle loading state
                   binding.pbProducts.visibility = View.VISIBLE
               }
               is Outcome.Success -> {
                   binding.pbProducts.visibility = View.GONE
                   val products = outcome.data
                   if (products != null) {
                       populateRecView(products)
                   }
               }
               is Outcome.Error -> {
                   binding.pbProducts.visibility = View.GONE
                   Toast.makeText(this@ProductsActivity, outcome.errorCode, Toast.LENGTH_LONG).show()
               }
           }
       }
    }

    private fun populateRecView(products: List<Product>) {
        adapter = ProductListAdapter(products)
//        binding.recViewUsers.layoutManager = LinearLayoutManager(this)
//        binding.recViewUsers.layoutManager = GridLayoutManager(this, 2)
        binding.recViewProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.recViewProducts.adapter = adapter
    }
}