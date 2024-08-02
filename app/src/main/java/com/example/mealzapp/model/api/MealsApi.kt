package com.example.mealzapp.model.api

import com.example.mealzapp.model.response.CategoryResponse
import com.example.mealzapp.model.response.MealsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class MealsWebService {

    private var api: MealsApi

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        api = retrofit.create(MealsApi::class.java)
    }

    suspend fun getMeals(query: String = ""): MealsResponse {
        return api.getMeals(query)
    }

    suspend fun getCategories():CategoryResponse{
        return api.getCategories()
    }

    interface MealsApi {
        @GET("categories.php")
        suspend fun getCategories(): CategoryResponse

        @GET("search.php")
        suspend fun getMeals(
            @Query("s") query: String
        ): MealsResponse
    }
}