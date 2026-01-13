package com.example.portugueseverbs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.portugueseverbs.ui.screens.ConjugationScreen
import com.example.portugueseverbs.ui.screens.MainScreen
import com.example.portugueseverbs.ui.theme.PortugueseVerbsTheme
import com.example.portugueseverbs.viewmodel.VerbViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortugueseVerbsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PortugueseVerbsApp()
                }
            }
        }
    }
}

@Composable
fun PortugueseVerbsApp(
    viewModel: VerbViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.currentConjugation != null) {
        // Show conjugation result
        ConjugationScreen(
            uiState = uiState,
            onBackClick = { viewModel.clearConjugation() }
        )
    } else {
        // Show main screen with verb input and tense selection
        MainScreen(
            uiState = uiState,
            viewModel = viewModel,
            onVerbInputChange = { viewModel.onVerbInputChange(it) },
            onTenseClick = { viewModel.selectTense(it) }
        )
    }
}
