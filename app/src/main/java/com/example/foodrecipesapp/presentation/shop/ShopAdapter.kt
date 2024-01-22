package com.example.foodrecipesapp.presentation.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipesapp.R

class ShopAdapter(private val dataSet: ArrayList<String>) :
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopAdapter.ViewHolder, position: Int) {
        holder.setData(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemName: AppCompatTextView
        var addToCart: Button

        init {
            itemName = itemView.findViewById(R.id.shop_name)
            addToCart = itemView.findViewById(R.id.add_to_cart)
        }

        fun setData(name: String) {
            itemName.text = name

            addToCart.setOnClickListener {
                Toast.makeText(itemView.context, "$name added to cart", Toast.LENGTH_SHORT).show()
            }
        }
    }
}