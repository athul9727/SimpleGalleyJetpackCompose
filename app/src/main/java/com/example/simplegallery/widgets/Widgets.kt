package com.example.simplegallery.widgets

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                        placeholder = painterResource(id =0),
                        error = painterResource(id =0),
                        contentScale = ContentScale.Crop,
                        filterQuality = FilterQuality.None,
                    ),
                    alignment = Alignment.BottomStart,
                    contentDescription = "image")
            }

            Text(text = item.title)
        }


    }
}