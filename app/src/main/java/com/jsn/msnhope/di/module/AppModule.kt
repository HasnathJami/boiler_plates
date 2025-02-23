package com.jsn.msnhope.di.module

import com.jsn.msnhope.data.remote.model.api.ApiInterface
import com.jsn.msnhope.di.module.annotations.RetrofitWithLogging
import com.jsn.msnhope.di.module.annotations.RetrofitWithoutLogging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://fakestoreapi.com/"
    @Provides
    @Singleton
    fun providesRetrofit():Retrofit {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(provideLoggingInterceptor()).addInterceptor(
            provideCommonHeadersInterceptor()
        ).build()

        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    @RetrofitWithoutLogging
    fun providesRetrofitWithoutLogging():Retrofit {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(
            provideCommonHeadersInterceptor()
        ).build()

        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    @RetrofitWithLogging
    fun providesRetrofitWithLogging():Retrofit {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(
            provideCommonHeadersInterceptor()
        ).build()

        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    private fun provideLoggingInterceptor(): Interceptor {
       // return if (BuildConfig.DEBUG) {
          return   HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
      //  } else {
//            HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.NONE
//            }
       // }
    }

    private fun provideCommonHeadersInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest =
                originalRequest.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    //.addHeader("Authorization", "Bearer ${myToken}")
                    .build()
            chain.proceed(modifiedRequest)
        }
    }

    @Provides
    @Singleton
    fun provideApiService(@RetrofitWithLogging retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}