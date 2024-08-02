package com.example.mealzapp.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strMealThumb")
    val imageUrl: String,
    @SerializedName("strArea")
    val area: String,
    @SerializedName("strInstructions")
    val instructions: String
)