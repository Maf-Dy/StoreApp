package com.example.storeapp.core.presentation.ui


import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.storeapp.R
import com.example.storeapp.core.data.Product
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }

    private lateinit var productImage: ImageView
    private lateinit var productTitle: TextView
    private lateinit var productDescription: TextView
    private lateinit var productPrice: TextView
    private lateinit var productRating: RatingBar
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        productImage = findViewById(R.id.productImage)
        productTitle = findViewById(R.id.productTitle)
        productDescription = findViewById(R.id.productDescription)
        productPrice = findViewById(R.id.productPrice)
        productRating = findViewById(R.id.productRating)

        product = intent.getParcelableExtra(EXTRA_PRODUCT) ?: throw IllegalArgumentException("Product is null")

        Picasso.get()
            .load(product.image)
            .into(productImage)

        productTitle.text = product.title
        productDescription.text = product.description
        productPrice.text = "$${product.price}"
        productRating.rating = product.rating.rate.toFloat()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

