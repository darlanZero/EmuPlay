package com.starsford.wabemuplay.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.starsford.wabemuplay.core.disk.FileManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EmulatorScreen(gamePath: String) {
   val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Text("Emulador: $gamePath", modifier = Modifier.align(Alignment.Center))

        EmulatorControls(
            onBack = {/*Voltar para a lista de jogos */},
            onSettingsPopup = {/*Abrir configurações do emulador para o jogo específico */}
        )
    }


}

@Composable
fun EmulatorControls(onBack: () -> Unit, onSettingsPopup: () -> Unit) {
    TODO("Not yet implemented")
}