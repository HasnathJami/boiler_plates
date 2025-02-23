package com.jsn.msnhope.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsn.msnhope.data.remote.model.Product
import com.jsn.msnhope.data.remote.utils.Outcome
import com.jsn.msnhope.data.repository.ProductsRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {
    // private val repository: AppDataSourceRepository

    private var _products: MutableLiveData<Outcome<List<Product>?>> = MutableLiveData()
    val products: LiveData<Outcome<List<Product>?>> get() = _products

    init {
//        val userDao = AppDatabase.getDatabase(application).userDao()
//        repository = AppDataSourceRepository(userDao, ApiClient.apiInterface)
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _products.value = Outcome.Loading // Show loading state
            _products.value = repository.fetchProducts()
        }

    }
}