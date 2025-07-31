package com.starsford.wabemuplay.core.disk

import android.content.Context
import android.os.Build
import android.os.Environment
import java.io.File

object FileManager {
    private const val GAMES_DIR = "WabEmuPlay/Games"
    const val DEMOGRAPHY_DEFAULT = "General"
    private const val BACKUP_ROOT_DIR = "WabEmuPlay/Backup"

    fun getGamesDirectory(context: Context): File {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            File(context.getExternalFilesDir(null), GAMES_DIR)
        } else {
            File(Environment.getExternalStorageDirectory(), GAMES_DIR)
        }.
        apply { mkdirs() }
    }

    fun listGameFiles(context: Context): List<GameFile> {
        val gamesDir = getGamesDirectory(context)
        return gamesDir.listFiles()?.mapNotNull { file ->
            when {
                file.isDirectory && file.resolve("game.exe").exists() ->
                    GameFile(file.name, file.path, EngineType.WOLF_RPG)
                file.extension.equals("exe", ignoreCase = true) ->
                    detectEngineType(file)
                else -> null
            }
        } ?: emptyList()
    }

    private fun detectEngineType(file: File): GameFile? {
        return when {
            //RENPY: procura por arquivos .rpa ou pasta "game"
            file.name.equals("renpy.exe", ignoreCase = true) ||
                    file.resolveSibling("game").isDirectory ->
                GameFile(file.name, file.path, EngineType.RENPY)

            //KiriKiri: Arquivos .xp3 ou pasta "data"
            file.extension.equals("xp3", ignoreCase = true) ||
                    file.resolveSibling("data").isDirectory ->
                GameFile(file.name, file.path, EngineType.KIRIKIRI)

            //Unity: procura por arquivos .data ou pasta "Data"
            file.extension.equals("data", ignoreCase = true) ||
                    file.resolveSibling("Data").isDirectory ->
                GameFile(file.name, file.path, EngineType.UNITY)

            //Godot: procura por arquivos .pck ou pasta "godot"
            file.extension.equals("pck", ignoreCase = true) ||
                    file.resolveSibling("godot").isDirectory ->
                GameFile(file.name, file.path, EngineType.GODOT)

            //Unreal: procura por arquivos .pak ou pasta "Content"
            file.extension.equals("pak", ignoreCase = true) ||
                    file.resolveSibling("Content").isDirectory ->
                GameFile(file.name, file.path, EngineType.UNREAL)

            //RPG Maker: procura por arquivos .rvdata2 ou pasta "Data"
            file.extension.equals("rvdata2", ignoreCase = true) ||
                    file.resolveSibling("Data").isDirectory ->
                GameFile(file.name, file.path, EngineType.RPG_MAKER)

            else -> GameFile(file.name, file.path, EngineType.UNKNOWN)
        }
    }
}

enum class EngineType {
    RENPY, KIRIKIRI, WOLF_RPG, UNITY, GODOT, UNREAL, RPG_MAKER, UNKNOWN
}

data class GameFile(
    val name: String,
    val path: String,
    val engineType: EngineType,
    val demography: String = FileManager.DEMOGRAPHY_DEFAULT
) {
    fun getDisplayName(): String {
        return name.replace("_", " ").replace(".exe", "")
    }

    fun getDemography(): String {
        return demography.ifEmpty { FileManager.DEMOGRAPHY_DEFAULT }
    }
}

