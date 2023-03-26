package com.example.simplegallery.utils

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import java.io.File


class ImageBucketUtils {
    companion object{
        @SuppressLint("Range")
        fun getImageBuckets(mContext: Context): List<Bucket> {
            val buckets: MutableList<Bucket> = mutableListOf()
            val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection =
                arrayOf(MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.DATA)
            val cursor: Cursor? = mContext.contentResolver.query(uri, projection, null, null, null)
            if (cursor != null) {
                var file: File
                while (cursor.moveToNext()) {
                    val bucketName: String = cursor.getString(cursor.getColumnIndex(projection[0]))
                    val firstImage: String = cursor.getString(cursor.getColumnIndex(projection[1]))
                    file = File(firstImage)
                    if (file.exists()) {
                        buckets.add(Bucket(bucketName, firstImage))
                    }
                }
                cursor.close()
            }
            return buckets
        }

        @SuppressLint("Range")
        fun getImagesByBucket(bucketPath: String, mContext: Context): List<String> {
            val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            val selection = MediaStore.Images.Media.BUCKET_DISPLAY_NAME + " =?"
            val orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC"
            val images: MutableList<String> = ArrayList()
            val cursor: Cursor? = mContext.contentResolver.query(uri, projection, selection, arrayOf<String>(bucketPath), orderBy)
            if (cursor != null) {
                var file: File
                while (cursor.moveToNext()) {
                    val path = cursor.getString(cursor.getColumnIndex(projection[0]))
                    file = File(path)
                    if (file.exists() && !images.contains(path)) {
                        images.add(path)
                    }
                }
                cursor.close()
            }
            return images
        }
    }

}

class Bucket(val name: String, val firstImageContainedPath: String)