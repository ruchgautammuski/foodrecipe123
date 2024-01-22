package com.example.foodrecipesapp.data.remote

import com.example.foodrecipesapp.data.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("recipes/random")
    suspend fun getRandomRecipes(@Query("apiKey") apiKey: String): Response<RecipeResponse>
}