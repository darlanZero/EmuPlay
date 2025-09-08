package com.starsford.wabemuplay.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.starsford.wabemuplay.R
import com.starsford.wabemuplay.core.cpu.CpuDetector
import com.starsford.wabemuplay.core.disk.FileManager
import com.starsford.wabemuplay.core.disk.GameFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.text.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val supportedEngines = remember { CpuDetector.supportedEngines() }
    val games = remember { mutableStateListOf<GameFile>() }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            games.addAll(FileManager.listGameFiles(context))
        }
    }

    Column{
        TopAppBar(title = { Text("WabEmuPlay") })

        if (games.isEmpty()) {
            Text("Nenhum jogo encontrado", modifier = Modifier.padding(16.dp))
            Button(
                onClick = {/*Abrir explorador de arquivos */},
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Adicionar Jogo")
            }
        } else {
            LazyColumn {
                items(games.size) { index ->
                    val game = games[index]
                    GameItem(game, onClick = {
                        navController.navigate("emulator/${game.path}")
                    })
                }
            }
        }
    }

}

@Composable
fun GameItem(game: GameFile, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            IconDefaultGame()
            Column(modifier = Modifier.weight(1f)) {
                Text(text = game.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = game.engineType.name, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun IconDefaultGame() {
    Image(
        painter = painterResource(id = R.drawable.ic_default_game),
        contentDescription = "Default Game Icon",
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .background(Color.LightGray) // Placeholder background
            .padding(16.dp)
    )
}