package com.example.simplegallery.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.simplegallery.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        Row(horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Gallery")
        }
    }) {
        MainContent(list = listOf("qqqq","wwwww"), navController= navController)
    }

}


@Composable
fun MainContent(list:List<String>,navController: NavController){
    androidx.compose.material.Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(all = 12.dp)) {

            LazyColumn {
                items(items = list){
                    MovieRow(item = it){ data ->
                        navController.navigate(AppScreens.DetailsScreen.name+"/$data")
                    }
                }

            }

        }
    }

}


@Composable
fun MovieRow(item:String,onItemClicked:(String) -> Unit = {}){
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(130.dp)
        .clickable {
            onItemClicked.invoke(item)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 5.dp
    ) {

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start)
        {

            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 5.dp
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "image")
            }

            Text(text = item)
        }


    }
}