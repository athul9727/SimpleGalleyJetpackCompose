package com.example.simplegallery.screens

import android.os.Build
import android.os.FileUtils
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
import com.example.simplegallery.models.DataModel
import com.example.simplegallery.navigation.AppScreens
import com.example.simplegallery.utils.FileUtil
import com.example.simplegallery.utils.sizeInMb
import com.example.simplegallery.utils.twoDecimals
import com.example.simplegallery.widgets.DataRow
import java.nio.file.FileSystems
import java.nio.file.attribute.BasicFileAttributes

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.LightGray, elevation = 5.dp) {
            Row(horizontalArrangement = Arrangement.Start) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Gallery")
            }

        }
    }) {



        MainContent(list = FileUtil.getDataList(), navController= navController)
    }

}


@Composable
fun MainContent(list:List<DataModel>,navController: NavController){
    androidx.compose.material.Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(all = 12.dp)) {

            LazyColumn {
                items(items = list){
                    DataRow(item = it){ data ->
                        navController.navigate(AppScreens.DetailsScreen.name+"/${data.id}")
                    }
                }

            }

        }
    }

}


