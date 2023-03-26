package com.example.simplegallery.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController,data:String?) {


    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.LightGray, elevation = 5.dp) {
            Row(horizontalArrangement = Arrangement.Start) {
                 Icon(imageVector = Icons.Default.ArrowBack,
                      contentDescription = "Back Button",
                      modifier = Modifier.clickable {
                     navController.popBackStack()
                 })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Gallery")
            }

        }
    }) {
        Text(text = "$data", style = MaterialTheme.typography.h5)
    }


}