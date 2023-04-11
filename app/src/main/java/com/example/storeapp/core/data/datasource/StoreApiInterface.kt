package com.example.storeapp.core.data.datasource

import com.example.storeapp.core.data.Product
import retrofit2.Call
import retrofit2.http.GET

interface StoreApiService {
    @GET("products")
    fun getProducts(): Call<List<Product>>
}
