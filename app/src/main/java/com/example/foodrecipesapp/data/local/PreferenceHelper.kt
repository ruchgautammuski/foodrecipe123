package com.example.foodrecipesapp.data.local

import splitties.preferences.Preferences

object PreferenceHelper : Preferences("recipe_data") {
    var ingredient by StringSetPref("ingredient", emptySet())
}