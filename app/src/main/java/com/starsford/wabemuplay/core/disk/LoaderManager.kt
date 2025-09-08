package com.starsford.wabemuplay.core.disk

import android.content.Context
import com.starsford.wabemuplay.core.engines.GodotLoader
import com.starsford.wabemuplay.core.engines.KiriKiriLoader
import com.starsford.wabemuplay.core.engines.RenpyLoader
import com.starsford.wabemuplay.core.engines.RPGMakerWrapper
import com.starsford.wabemuplay.core.engines.UnityLoader
import com.starsford.wabemuplay.core.engines.UnrealLoader
import com.starsford.wabemuplay.interfaces.GameLoader

object LoaderManager {
    private val loaders = listOf(
        RenpyLoader(),
        RPGMakerWrapper(),
        UnityLoader(),
        UnrealLoader(),
        KiriKiriLoader(),
        GodotLoader()
    )

    fun getLoaderForGame(gameFile: GameFile): GameLoader? {
        return loaders.find { it.isSupported(gameFile) }
    }

    fun loadGame(context: Context, gameFile: GameFile): Boolean {
        val loader = getLoaderForGame(gameFile)
        return loader?.loadGame(context, gameFile) ?: false
    }

    fun getSupportedEngines(): List<String> {
        return loaders.map { it.getEngineName() }
    }
}
}