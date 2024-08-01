package com.example.mealzapp.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import com.example.mealzapp.ui.composables.TopBar

@Composable
fun MealDetailsScreen(
    name: String,
    id: String,
    navController: NavController
) {

    val viewModel: MealDetailsViewModel = viewModel()
    val mealDetailsState = viewModel.mealDetailsState.value

    LaunchedEffect(key1 = true) {
        viewModel.getMealDetails(id)
    }

    Scaffold(
        topBar = {
            TopBar(title = name, icon = Icons.AutoMirrored.Filled.ArrowBack) {
                navController.navigateUp()
            }
        },

        ) { innerPadding ->
        mealDetailsState?.let { meal ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(innerPadding),
                    ) {

                        AsyncImage(
                            model = meal.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(CircleShape)
                        )

                        Text(
                            text = meal.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(top = 8.dp, start = 12.dp, end = 12.dp)
                                .align(Alignment.Start),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = meal.description,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(top = 8.dp, start = 12.dp, end = 12.dp)
                                .align(Alignment.Start),
                            style = MaterialTheme.typography.titleSmall
                        )

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Change state of meal profile picture")
                        }
                    }
                }
            }
        }

    }
}