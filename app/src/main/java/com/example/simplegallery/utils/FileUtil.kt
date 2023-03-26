package com.example.simplegallery.utils

import android.os.Environment
import android.util.Log
import com.example.simplegallery.models.DataModel
import java.io.File


class FileUtil {

    companion object{

        fun getDataList():List<DataModel>{
            val dataList = mutableListOf<DataModel>()
            var id = 0;
            val list = imageReader()
            list.forEach{
                id += 1;
                dataList.add(DataModel("$id",it.name, it.sizeInMb.twoDecimals() +" MB",it.path))
            }

            return dataList
        }

        fun getDataFromList(dataId:String): DataModel? {

            val dataList = getDataList()
            return dataList.find { it.id == dataId }

        }



        fun imageReader(): ArrayList<File> {
            val ROOT_DIR : String = Environment.getExternalStorageDirectory().absolutePath
            val FOLDER = "DCIM/Camera"
            val fullpath = ROOT_DIR  + File.separator + FOLDER
            Log.e("fullpath", fullpath)


            val file =  File(fullpath)

            val fileList: ArrayList<File> = arrayListOf()
            val listAllFiles = file.listFiles()

            if (listAllFiles != null && listAllFiles.isNotEmpty()) {
                for (currentFile in listAllFiles) {
                    if (currentFile.name.endsWith(".jpg")) {
                        // File absolute path
                        Log.e("downloadFilePath", currentFile.absolutePath)
                        // File Name
                        Log.e("downloadFileName", currentFile.name)
                        fileList.add(currentFile.absoluteFile)
                    }
                }
            }

            return fileList
        }
    }

}