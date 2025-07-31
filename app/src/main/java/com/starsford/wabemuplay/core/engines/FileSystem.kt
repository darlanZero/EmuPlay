package com.starsford.wabemuplay.core.engines

import android.content.Context
import java.io.File

object FileSystem {
    fun findGameFiles(context: Context): List<File> {
        val gamesDir = File(context.getExternalFilesDir(null), "games")
        if (!gamesDir.exists()) {
            gamesDir.mkdirs() // Create the directory if it doesn't exist
        }
        return gamesDir.listFiles()?.toList() ?: emptyList()
    }
}