package com.example.foodrecipesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.foodrecipesapp.data.local.PreferenceHelper
import com.example.foodrecipesapp.data.model.Recipe
import com.example.foodrecipesapp.data.remote.IApiService
import com.example.foodrecipesapp.data.remote.RetrofitApiService
import com.example.foodrecipesapp.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val api: IApiService?
) : ViewModel() {

    private val _dataList: MutableStateFlow<List<Recipe>?> = MutableStateFlow(null)
    val dataList: StateFlow<List<Recipe>?> get() = _dataList.asStateFlow()

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val resp = api?.getRandomRecipes(Constants.API_KEY)
                if (resp != null && resp.isSuccessful && resp.body() != null) {
                    //handle success
                    val response = resp.body()?.recipes!!
                    for (recipe: Recipe in response) {
                        recipe.extendedIngredients.forEach {
                            PreferenceHelper.ingredient =
                                PreferenceHelper.ingredient + setOf(it.originalName)
                        }
                    }
                    _dataList.value = response
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                return HomeViewModel(
                    api = RetrofitApiService.getInstance()
                ) as T
            }
        }
    }
}