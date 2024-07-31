package com.example.mealzapp.model

import com.example.mealzapp.model.api.MealsWebService
import com.example.mealzapp.model.response.CategoryResponseObject

class MealsRepository(
    private val mealsWebService: MealsWebService = MealsWebService()
) {

    suspend fun getMeals(): CategoryResponseObject{
        return mealsWebService.getMeals()
    }

}