package com.example.mealzapp.ui.categories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.model.MealzRepository
import com.example.mealzapp.model.response.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryListViewModel() : ViewModel() {

    private val repo: MealzRepository = MealzRepository.getInstance()

    val categoriesState: MutableState<List<Category>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            val categories = getCategories()
            categoriesState.value = categories
        }
    }

    private suspend fun getCategories(): List<Category> {
        return repo.getCategories()
    }

}