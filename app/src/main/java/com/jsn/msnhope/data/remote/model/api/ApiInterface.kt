package com.jsn.msnhope.data.remote.model.api

import com.jsn.msnhope.data.remote.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    suspend fun getProducts():Response<List<Product>>
}