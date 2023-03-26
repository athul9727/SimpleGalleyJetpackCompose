package com.example.simplegallery.utils

import java.io.File
import java.math.RoundingMode
import java.text.DecimalFormat


val File.size get() = if (!exists()) 0.0 else length().toDouble()
    val File.sizeInKb get() = size / 1024
    val File.sizeInMb get() = sizeInKb / 1024
    val File.sizeInGb get() = sizeInMb / 1024
    val File.sizeInTb get() = sizeInGb / 1024

fun Double.twoDecimals(): String {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    return df.format(this)
}

