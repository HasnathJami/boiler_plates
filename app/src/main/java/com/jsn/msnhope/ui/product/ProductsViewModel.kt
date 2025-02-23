package com.jsn.msnhope.ui.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsn.msnhope.data.local.database.AppDatabase
import com.jsn.msnhope.data.remote.model.Product
import com.jsn.msnhope.data.remote.model.network.ApiClient
import com.jsn.msnhope.data.remote.utils.Outcome
import com.jsn.msnhope.data.repository.AppDataSourceRepository
import kotlinx.coroutines.launch

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AppDataSourceRepository

    private var _products:MutableLiveData<Outcome<List<Product>?>> = MutableLiveData()
    val products: LiveData<Outcome<List<Product>?>> get() = _products

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = AppDataSourceRepository(userDao, ApiClient.apiInterface)
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _products.value = Outcome.Loading // Show loading state
            _products.value = repository.fetchProducts()
        }

    }
}