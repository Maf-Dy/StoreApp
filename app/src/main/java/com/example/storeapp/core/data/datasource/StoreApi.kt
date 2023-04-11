package com.example.storeapp.core.data.datasource


import com.example.storeapp.core.data.Product
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StoreApi {
    private const val BASE_URL = "https://fakestoreapi.com/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(StoreApiService::class.java)

    fun getProducts(callback: Callback<List<Product>>) {
        val call = service.getProducts()
        call.enqueue(callback)
    }
}
