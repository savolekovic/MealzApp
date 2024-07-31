package com.example.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.model.MealsRepository
import com.example.mealzapp.model.response.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealCategoriesViewModel(
    private val repository: MealsRepository = MealsRepository()
) : ViewModel() {

    private val mealsJob = Job()

    init {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            val meals = getMeals()
            mealsState.value = meals
        }
    }

    val mealsState: MutableState<List<Category>> = mutableStateOf(emptyList())

    private suspend fun getMeals(): List<Category> {
        return repository.getMeals().categories
    }

    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }

}