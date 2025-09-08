package com.starsford.wabemuplay.interfaces

import android.content.Context
import com.starsford.wabemuplay.core.disk.GameFile

interface GameLoader {
    fun loadGame(context: Context, gameFile: GameFile): Boolean
    fun isSupported(gameFile: GameFile): Boolean
    fun getEngineName(): String
}