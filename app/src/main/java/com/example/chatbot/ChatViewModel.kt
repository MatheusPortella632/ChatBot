package com.example.chatbot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

data class Message(val sender: String, val text: String)

class ChatViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _connectionStatus = MutableStateFlow("Iniciando...")
    val connectionStatus: StateFlow<String> = _connectionStatus.asStateFlow()

    private var socket: Socket? = null
    private var writer: PrintWriter? = null
    private var reader: BufferedReader? = null
    private var shouldReconnect = true

    // ATENÇÃO AO IP:
    // O IP usado é o do Emulador do Android Studio: "10.0.2.2"
    // Se usar um celular físico na mesma Wi-Fi usar o ip local
    private val serverIp = "10.0.2.2"
    private val serverPort = 9999

    init {
        startConnectionLoop()
    }

    private fun startConnectionLoop() {
        viewModelScope.launch(Dispatchers.IO) {
            while (shouldReconnect && isActive) {
                try {
                    _connectionStatus.value = "Conectando ao servidor..."

                    socket = Socket(serverIp, serverPort)
                    writer = PrintWriter(socket!!.getOutputStream(), true)
                    reader = BufferedReader(InputStreamReader(socket!!.getInputStream()))

                    _connectionStatus.value = "Conectado"
                    addSystemMessage("Conexão estabelecida com sucesso!")

                    var line: String?
                    while (reader!!.readLine().also { line = it } != null) {
                        val response = line ?: continue
                        _messages.update { it + Message("Bot", response) }
                    }

                } catch (e: Exception) {
                    _connectionStatus.value = "Desconectado. Tentando reconectar em 3s..."
                    closeResources()
                }

                if (shouldReconnect && isActive) {
                    delay(3000) // Aguarda 3 segundos antes da próxima tentativa
                }
            }
        }
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        _messages.update { it + Message("Você", text) }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (socket?.isConnected == true && writer != null) {
                    writer?.println(text)
                    // Se o usuário digitou "sair", encerramos a conexão e impedimos a reconexão automática
                    if (text.equals("sair", ignoreCase = true)) {
                        shouldReconnect = false
                        closeResources()
                        _connectionStatus.value = "Desconectado (Sessão encerrada)"
                    }

                } else {
                    _messages.update { it + Message("Sistema", "Não foi possível enviar: Sem conexão.") }
                }
            } catch (e: Exception) {
                _messages.update { it + Message("Sistema", "Erro ao enviar mensagem.") }
            }
        }
    }

    private fun addSystemMessage(text: String) {
        _messages.update { it + Message("Sistema", text) }
    }

    private fun closeResources() {
        try {
            reader?.close()
            writer?.close()
            socket?.close()
        } catch (_: Exception) {}
    }

    override fun onCleared() {
        super.onCleared()
        shouldReconnect = false
        closeResources()
    }
}