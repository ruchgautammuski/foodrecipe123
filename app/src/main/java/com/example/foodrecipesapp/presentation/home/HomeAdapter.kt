package com.example.foodrecipesapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodrecipesapp.R

class HomeAdapter(private val dataSet: ArrayList<RecipeModel>) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        holder.setData(
            dataSet[position].sourceImage,
            dataSet[position].name,
            dataSet[position].description
        )
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var recipeImage: AppCompatImageView
        var recipeLike: AppCompatImageView
        var recipeUnlike: AppCompatImageView
        var recipeName: AppCompatTextView
        var recipeDesc: AppCompatTextView

        init {
            recipeImage = itemView.findViewById(R.id.recipe_image)
            recipeLike = itemView.findViewById(R.id.recipe_like)
            recipeUnlike = itemView.findViewById(R.id.recipe_unlike)
            recipeName = itemView.findViewById(R.id.recipe_name)
            recipeDesc = itemView.findViewById(R.id.recipe_desc)
        }

        fun setData(url: String, name: String, desc: String) {
            Glide.with(itemView.context).load(url).into(recipeImage)
            recipeName.text = name
            recipeDesc.text = if (desc.length > 30) "${desc.substring(0, 30)}..." else desc

            recipeLike.setOnClickListener {
                if (recipeUnlike.isVisible) {
                    recipeUnlike.visibility = View.INVISIBLE
                    recipeLike.visibility = View.VISIBLE
                } else {
                    recipeLike.visibility = View.INVISIBLE
                    recipeUnlike.visibility = View.VISIBLE
                }
            }

            recipeUnlike.setOnClickListener {
                if (recipeLike.isVisible) {
                    recipeLike.visibility = View.INVISIBLE
                    recipeUnlike.visibility = View.VISIBLE
                } else {
                    recipeUnlike.visibility = View.INVISIBLE
                    recipeLike.visibility = View.VISIBLE
                }
            }
        }
    }
}

data class RecipeModel(
    val id: Long,
    val name: String,
    val sourceImage: String,
    val description: String,
    val isLiked: Boolean,
    val isVeg: Boolean
)