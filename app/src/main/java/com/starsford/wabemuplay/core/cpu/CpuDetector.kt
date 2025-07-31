package com.starsford.wabemuplay.core.cpu

import android.os.Build

object CpuDetector {
    fun isSnapdragon(): Boolean {
        return Build.HARDWARE.lowercase().contains("qcom") ||
                Build.BOARD.lowercase().run { contains("msm") || contains("sdm") } }

    fun supportedEngines(): List<String> {
        return if (isSnapdragon()) {
            listOf("RenPy", "KiriKiri", "WolfRPG", "Unity", "Godot", "Unreal", "RPG Maker MV", "RPG Maker MZ", "RPG Maker VX Ace", "RPG Maker VX", "RPG Maker XP")
        } else {
            listOf("RenPy", "KiriKiri", "RPG Maker MV", "RPG Maker MZ", "RPG Maker VX Ace", "RPG Maker VX", "RPG Maker XP", "WolfRPG")
        }
    }
}