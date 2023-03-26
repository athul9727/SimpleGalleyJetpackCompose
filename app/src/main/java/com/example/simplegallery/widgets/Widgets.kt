package com.example.simplegallery.widgets

import android.content.res.Resources.Theme
import android.view.Display.Mode
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.Transformation
import com.example.simplegallery.models.DataModel

@Composable
fun DataRow(item:DataModel,onItemClicked:(DataModel) -> Unit = {}){
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

        var expandState by remember {
            mutableStateOf(false)
        }

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start)
        {

            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 5.dp
            ) {
               // Icon(imageVector = Icons.Default.AccountBox, contentDescription = "image")
                Image(
                    painter = rememberAsyncImagePainter(
                         model = ImageRequest.Builder(LocalContext.current)
                            .data(item.path)
                            .transformations(CircleCropTransformation())
                            .crossfade(true)
                            .diskCacheKey("data_image_${item.id}${item.year}")
                            .networkCachePolicy(CachePolicy.ENABLED)
                            .diskCachePolicy(CachePolicy.DISABLED)
                            .memoryCachePolicy(CachePolicy.ENABLED)
                            .build(),
                        contentScale = ContentScale.Crop,
                        filterQuality = FilterQuality.None,
                    ),
                    alignment = Alignment.BottomStart,
                    contentDescription = "image")
            }

           Column(modifier = Modifier.padding(4.dp)) {
               Text(text = item.title, style = MaterialTheme.typography.h6)


               
               AnimatedVisibility(visible = expandState) {
                   Column(modifier = Modifier.padding(4.dp)) {
                       Text(
                           buildAnnotatedString {
                           withStyle(
                               style = SpanStyle(color = Color.Red,
                               fontSize = 13.sp,
                               fontWeight = FontWeight.Bold
                               ))
                           {
                                   append(item.year)
                           }

                           withStyle(
                               style = SpanStyle(color = Color.DarkGray,
                                   fontSize = 13.sp,
                                   fontWeight = FontWeight.Normal))
                           {
                               append(item.path)
                           }

                       })
                   }
               }

               Icon(imageVector = if(expandState) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                   contentDescription = "arrow up and down",
               modifier = Modifier
                   .size(25.dp)
                   .padding(bottom = 5.dp)
                   .clickable {
                       expandState = !expandState
                   },
               tint = Color.DarkGray
               )

               Divider()

           }
        }


    }
}

@Composable
fun HorizontalScrollImageView(imageList:List<DataModel>){
    LazyRow{
        items(imageList) {

              Card(modifier = Modifier.padding(12.dp).size(240.dp),
              elevation = 5.dp) {

                  Image(
                      painter = rememberAsyncImagePainter(
                          model = ImageRequest.Builder(LocalContext.current)
                              .data(it.path)
                              .transformations(CircleCropTransformation())
                              .crossfade(true)
                              .diskCacheKey("data_image_${it.id}${it.year}")
                              .networkCachePolicy(CachePolicy.ENABLED)
                              .diskCachePolicy(CachePolicy.DISABLED)
                              .memoryCachePolicy(CachePolicy.ENABLED)
                              .build(),
                          contentScale = ContentScale.Crop,
                          filterQuality = FilterQuality.None,
                      ),
                      alignment = Alignment.BottomStart,
                      contentDescription = "image")

              }

        }
    }

}