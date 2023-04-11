package com.example.storeapp.core.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.R
import com.example.storeapp.core.data.Product
import com.squareup.picasso.Picasso

class ProductAdapter(private val products: List<Product>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productTitle: TextView = itemView.findViewById(R.id.productTitle)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if (view != null) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(products[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]

        Picasso.get()
            .load(product.image)
            .into(holder.productImage)

        holder.productTitle.text = product.title
        holder.productPrice.text = "$${product.price}"
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
