package com.example.foodrecipesapp.data.model

data class RecipeResponse (
    val recipes: List<Recipe>
)

data class Recipe (
    val instructions: String,
    val sustainable: Boolean,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val glutenFree: Boolean,
    val veryPopular: Boolean,
    val healthScore: Long,
    val title: String,
    val diets: List<String>,
    val aggregateLikes: Long,
    val creditsText: String,
    val readyInMinutes: Long,
    val sourceURL: String,
    val dairyFree: Boolean,
    val servings: Long,
    val vegetarian: Boolean,
    val id: Long,
    val preparationMinutes: Long,
    val imageType: String,
    val summary: String,
    val cookingMinutes: Long,
    val image: String,
    val veryHealthy: Boolean,
    val vegan: Boolean,
    val cheap: Boolean,
    val extendedIngredients: List<ExtendedIngredient>,
    val dishTypes: List<String>,
    val gaps: String,
    val cuisines: List<Any?>,
    val lowFodmap: Boolean,
    val weightWatcherSmartPoints: Long,
    val occasions: List<String>,
    val pricePerServing: Double,
    val spoonacularScore: Double,
    val sourceName: String,
    val spoonacularSourceURL: String
)

data class AnalyzedInstruction (
    val name: String,
    val steps: List<Step>
)

data class Step (
    val number: Long,
    val ingredients: List<Ent>,
    val equipment: List<Ent>,
    val step: String,
    val length: Length? = null
)

data class Ent (
    val image: String,
    val localizedName: String,
    val name: String,
    val id: Long
)

data class Length (
    val number: Long,
    val unit: String
)

data class ExtendedIngredient (
    val originalName: String,
    val image: String,
    val amount: Double,
    val unit: String,
    val measures: Measures,
    val nameClean: String,
    val original: String,
    val meta: List<String>,
    val name: String,
    val id: Long,
    val aisle: String,
    val consistency: Consistency
)

enum class Consistency {
    Liquid,
    Solid
}

data class Measures (
    val metric: Metric,
    val us: Metric
)

data class Metric (
    val amount: Double,
    val unitShort: String,
    val unitLong: String
)
