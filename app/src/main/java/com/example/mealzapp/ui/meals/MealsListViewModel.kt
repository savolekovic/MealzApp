package com.example.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.model.MealzRepository
import com.example.mealzapp.model.response.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsListViewModel() : ViewModel() {

    private val repo: MealzRepository = MealzRepository.getInstance()

    val mealsState: MutableState<List<Meal>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    private suspend fun getMeals(): List<Meal> {
        return repo.getMeals()
    }

}