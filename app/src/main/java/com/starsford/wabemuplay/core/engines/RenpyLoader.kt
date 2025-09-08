package com.starsford.wabemuplay.core.engines

import android.content.Context
import android.content.Intent
import android.util.Log
import com.starsford.wabemuplay.core.disk.EngineType
import com.starsford.wabemuplay.core.disk.GameFile
import com.starsford.wabemuplay.interfaces.GameLoader

class RenpyLoader : GameLoader {
    override fun loadGame(context: Context, gameFile: GameFile): Boolean {
        return try {
            val gamePath = gameFile.path
            // Você precisa criar a RenpyActivity ou usar uma activity existente
            val intent = Intent().apply {
                setClassName(context, "com.starsford.wabemuplay.activities.RenpyActivity")
                putExtra("GAME_PATH", gamePath)
            }
            context.startActivity(intent)
            true
        } catch (e: Exception) {
            Log.e("RenpyLoader", "Failed to load game: ${e.message}")
            false
        }
    }

    override fun isSupported(gameFile: GameFile): Boolean {
        return gameFile.engineType == EngineType.RENPY
    }

    override fun getEngineName(): String {
        return "RenPy"
    }
}