package com.example.mealzapp.model

import android.util.Log
import com.example.mealzapp.model.api.MealsWebService
import com.example.mealzapp.model.response.Category

class MealsRepository(
    private val mealsWebService: MealsWebService = MealsWebService()
) {

    private var cachedMeals = listOf<Category>()

    suspend fun getMeals(): List<Category> {
        val meals = mealsWebService.getMeals()
        cachedMeals = meals.categories
        Log.d("test123", "meals: ${cachedMeals.size}")
        return cachedMeals
    }

    fun getMealDetails(id: String): Category? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }

}