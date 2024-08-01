package com.example.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mealzapp.ui.details.MealDetailsScreen
import com.example.mealzapp.ui.meals.MealsListScreen
import com.example.mealzapp.ui.theme.MealzAppTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealzAppTheme {
                MealsApplication()
            }
        }
    }
}

@Composable
fun MealsApplication() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MealList) {
        composable<MealList> {
            MealsListScreen(navController)
        }
        composable<MealDetails> {
            val args = it.toRoute<MealDetails>()
            MealDetailsScreen(
                id = args.id,
                name = args.name,
                navController = navController
            )
        }
    }

}

//Screens
@Serializable
object MealList

@Serializable
data class MealDetails(
    val id: String,
    val name: String
)

