package com.example.mealzapp.ui.meals.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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

    var isExpanded by remember { mutableStateOf(false) }
    val imageSizeDp: Dp by animateDpAsState(
        targetValue = if (isExpanded) 200.dp else 100.dp,
        label = "Animate Image"
    )

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
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(16.dp),
                    ) {
                        AsyncImage(
                            model = meal.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(imageSizeDp)
                                .padding(16.dp)
                                .clip(CircleShape)
                                .background(Color.Blue)
                                .align(Alignment.CenterHorizontally)
                        )

                        Text(
                            text = meal.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, start = 12.dp, end = 12.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = meal.instructions,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, start = 12.dp, end = 12.dp),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleSmall
                        )

                        Button(
                            onClick = { isExpanded = !isExpanded },
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 8.dp)
                        ) {
                            Text(text = "Change state of meal profile picture")
                        }
                    }
                }
            }
        }

    }
}