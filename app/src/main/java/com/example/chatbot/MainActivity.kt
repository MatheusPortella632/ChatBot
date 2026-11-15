package com.example.chatbot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                // Instancia o ViewModel
                val viewModel: ChatViewModel = viewModel()

                // Coleta os fluxos reativos
                val messages by viewModel.messages.collectAsState()
                val connectionStatus by viewModel.connectionStatus.collectAsState()

                // Exibe a tela de chat
                ChatScreen(
                    messages = messages,
                    connectionStatus = connectionStatus,
                    onSendMessage = { text -> viewModel.sendMessage(text) }
                )
            }
        }
        }
    }
}