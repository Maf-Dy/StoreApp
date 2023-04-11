package com.example.storeapp.core.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.core.data.Product
import com.example.storeapp.core.data.datasource.StoreApi
import com.example.storeapp.core.presentation.adapter.ProductAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var productAdapter: ProductAdapter
    private lateinit var productRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productRecyclerView = findViewById(R.id.productRecyclerView)
        productRecyclerView.layoutManager = LinearLayoutManager(this)

        val callback = object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    val products = response.body() ?: emptyList()

                    productAdapter = ProductAdapter(products, object : ProductAdapter.OnItemClickListener {
                        override fun onItemClick(product: Product) {
                            val intent = Intent(this@MainActivity, ProductDetailsActivity::class.java)
                            intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT, product)
                            startActivity(intent)
                        }
                    })

                    productRecyclerView.adapter = productAdapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load products", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load products", Toast.LENGTH_SHORT).show()
            }
        }

        StoreApi.getProducts(callback)
    }

}

