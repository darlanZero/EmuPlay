package com.starsford.wabemuplay.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.starsford.wabemuplay.R

class RenpyActivity : AppCompatActivity() {
    fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        val gamePath = intent.getStringExtra("GAME_PATH")
        if (gamePath.isNullOrEmpty()) {
            Log.e("RenpyActivity", "No game path provided")
            finish()
        }
        window.decorView.systemUiVisibility = (
            android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                    or android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                )
        Log.d("RenpyActivity", "Starting Ren'Py game at path: $gamePath")
        // Aqui você iniciaria o motor Ren'Py com o caminho do jogo

        setContentView(R.layout.activity_renpy) // Certifique-se de ter um layout adequado
    }
}