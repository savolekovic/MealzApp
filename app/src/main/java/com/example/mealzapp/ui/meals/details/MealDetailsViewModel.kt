package com.example.mealzapp.ui.meals.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mealzapp.model.MealzRepository
import com.example.mealzapp.model.response.Meal

class MealDetailsViewModel : ViewModel() {

    private val repo: MealzRepository = MealzRepository.getInstance()

    val mealDetailsState: MutableState<Meal?> = mutableStateOf(null)

    fun getMealDetails(id: String) {
        val meal = repo.getMealDetails(id)
        mealDetailsState.value = meal
    }

}