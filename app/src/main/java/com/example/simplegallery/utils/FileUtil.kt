package com.example.simplegallery.utils

import android.os.Environment
import android.util.Log
import java.io.File

fun imageReader() :ArrayList<File> {
    var gpath:String = Environment.getExternalStorageDirectory().absolutePath
    var spath = "testfolder"
    var fullpath = File(gpath + File.separator + spath)

    val fileList: ArrayList<File> = arrayListOf()
    val listAllFiles = fullpath.listFiles()

    if (listAllFiles != null && listAllFiles.size > 0) {
        for (currentFile in listAllFiles) {
            if (currentFile.name.endsWith(".jpeg")) {
                // File absolute path
                Log.e("downloadFilePath", currentFile.absolutePath)
                // File Name
                Log.e("downloadFileName", currentFile.name)
                fileList.add(currentFile.absoluteFile)
            }
        }
        Log.w("fileList", "" + fileList.size)
    }

    return fileList
}