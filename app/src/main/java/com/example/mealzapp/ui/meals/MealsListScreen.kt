package com.example.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.Icon
import coil.compose.AsyncImage
import com.example.mealzapp.model.response.Category
import com.example.mealzapp.ui.MealDetails
import com.example.mealzapp.ui.composables.TopBar

@Composable
fun MealsListScreen(
    navController: NavController
) {
    val viewModel: MealsListViewModel = viewModel()
    val mealsState = viewModel.mealsState.value

    Scaffold(
        topBar = {
            TopBar(title = "List of meals", icon = null) {}
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(mealsState) { meal ->
                    MealCategory(meal) {
                        navController.navigate(
                            MealDetails(
                                id = meal.id,
                                name = meal.name
                            )
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MealCategory(meal: Category, clickAction: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        onClick = { clickAction.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier.animateContentSize()
        ) {
            AsyncImage(
                model = meal.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(85.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
                    .padding(16.dp)
            )
            {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = meal.description,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    maxLines = if (isExpanded) 10 else 4
                )
            }

            Icon(
                imageVector =
                if (isExpanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        if (isExpanded) Alignment.Bottom
                        else Alignment.CenterVertically
                    )
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}
