package com.example.mealzapp.ui.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mealzapp.model.MealsRepository
import com.example.mealzapp.model.response.Category

class MealDetailsViewModel : ViewModel() {

    private val repo: MealsRepository = MealsRepository.getInstance()

    val mealDetailsState: MutableState<Category?> = mutableStateOf(null)

    fun getMealDetails(id: String) {
        val meal = repo.getMealDetails(id)
        mealDetailsState.value = meal
    }

}