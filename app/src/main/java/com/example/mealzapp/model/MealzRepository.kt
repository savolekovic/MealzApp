package com.example.mealzapp.model

import com.example.mealzapp.model.api.MealsWebService
import com.example.mealzapp.model.response.Category
import com.example.mealzapp.model.response.Meal

class MealzRepository(
    private val mealzWebService: MealsWebService = MealsWebService()
) {

    private var cachedMeals = listOf<Meal>()
    private var cachedCategories = listOf<Category>()

    suspend fun getMeals(): List<Meal> {
        val newMeals = mealzWebService.getMeals()
        cachedMeals = newMeals.meals
        return cachedMeals
    }

    fun getMealDetails(id: String): Meal? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }

    suspend fun getCategories(): List<Category> {
        val newCategories = mealzWebService.getCategories()
        cachedCategories = newCategories.categories
        return cachedCategories
    }

    companion object {
        @Volatile
        private var instance: MealzRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealzRepository().also { instance = it }
        }
    }

}