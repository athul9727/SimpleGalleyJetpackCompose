package com.example.simplegallery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.simplegallery.utils.FileUtil

@Composable
fun DetailScreen(navController: NavController,dataId:String?) {

    val data = FileUtil.getDataFromList(dataId!!)

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
        Surface(modifier = Modifier
            .padding(12.dp)
            .size(300.dp),
            shape = RectangleShape,
            elevation = 5.dp
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data?.path)
                        .transformations(RoundedCornersTransformation())
                        .crossfade(true)
                        .diskCacheKey("data_image_${data?.path}")
                        .networkCachePolicy(CachePolicy.ENABLED)
                        .diskCachePolicy(CachePolicy.DISABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.None,
                ),
                alignment = Alignment.BottomStart,
                contentDescription = "image"
            )
        }
    }


}