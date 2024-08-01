package com.example.mealzapp.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(title: String, icon: ImageVector?, clickAction: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
        ),
        title = {
            Text(title)
        },
        navigationIcon = {
            icon?.let {
                Icon(
                    it,
                    contentDescription = "Content description",
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .clickable { clickAction.invoke() })
            }
        },
    )
}