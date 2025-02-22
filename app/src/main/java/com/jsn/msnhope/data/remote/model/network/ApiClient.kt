package com.jsn.msnhope.data.remote.model.network

import com.jsn.msnhope.data.remote.model.api.ApiInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // Create an Interceptor to add common headers
    private val headersInterceptor = Interceptor { chain ->
        val originalRequest: Request = chain.request()

        // Add common headers like Content-Type, Authorization, etc.
        val newRequest = originalRequest.newBuilder()
            .addHeader("Content-Type", "application/json")  // You can set the Content-Type here
//            .addHeader("Authorization", "Bearer your_token") // Add Authorization header if needed
            .build()

        chain.proceed(newRequest) // Proceed with the new request
    }


    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log the full body of requests and responses
    }
    private val client = OkHttpClient.Builder().addInterceptor(headersInterceptor).addInterceptor(
        loggingInterceptor
    ).build()


    private val retrofit = Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create()).client(client).build()

    val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)
}