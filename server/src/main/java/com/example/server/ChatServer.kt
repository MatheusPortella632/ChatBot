package com.example.server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

fun main() {
    val port = 9999
    val serverSocket = ServerSocket(port)
    println("🤖 Chatbot Server iniciado na porta $port...")

    while (true) {
        val clientSocket = serverSocket.accept()
        println("Novo cliente conectado: ${clientSocket.inetAddress.hostAddress}")

        // Cria uma nova Thread para cada cliente conectado
        Thread {
            handleClient(clientSocket)
        }.start()
    }
}

fun handleClient(socket: Socket) {
    try {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val writer = PrintWriter(socket.getOutputStream(), true)

        // Envia mensagem de boas-vindas
        writer.println("🤖 Bot: Olá! Eu sou o seu assistente virtual de loja de camisetas. Digite algo ou 'sair' para encerrar.")

        var inputLine: String?
        while (reader.readLine().also { inputLine = it } != null) {
            val message = inputLine?.trim() ?: continue
            println("Mensagem recebida de ${socket.inetAddress.hostAddress}: $message")

            if (message.equals("sair", ignoreCase = true)) {
                writer.println("🤖 Bot: Até logo! Foi bom conversar com você.")
                break
            }

            // Processa a resposta do chatbot
            val botResponse = generateBotResponse(message)
            writer.println("🤖 Bot: $botResponse")
        }
    } catch (e: Exception) {
        println("Erro na conexão com o cliente: ${e.message}")
    } finally {
        socket.close()
        println("Cliente desconectado.")
    }
}

fun generateBotResponse(message: String): String {
    return when {
        message.contains("olá", ignoreCase = true) || message.contains("oi", ignoreCase = true) ->
            "Olá! Como posso ajudar você hoje?"
        message.contains("tudo bem", ignoreCase = true) ->
            "Comigo está tudo ótimo! E com você?"
        message.contains("ajuda", ignoreCase = true) ->
            "Posso responder perguntas simples. Tente dizer 'oi', perguntar 'tudo bem' ou sobre nossas camisetas, tamanhos, cores ou compra."
        message.contains("camisetas", ignoreCase = true) || message.contains("roupas", ignoreCase = true) || message.contains("camisas", ignoreCase = true)|| message.contains("itens", ignoreCase = true)->
            "Temos camisetas esportivas e casuais."
        message.contains("cor", ignoreCase = true) || message.contains("cores", ignoreCase = true) ->
            "Nossas camisetas são nas cores azul e preta."
        message.contains("tamanho", ignoreCase = true) ->
            "Nossas camisetas tem os tamanhos diponíveis: \nGG\nG\nP\nM"
        message.contains("compra", ignoreCase = true) || message.contains("locais", ignoreCase = true) || message.contains("local", ignoreCase = true) || message.contains("lugar", ignoreCase = true) || message.contains("região", ignoreCase = true) || message.contains("vende", ignoreCase = true)->
            "Nossa lojas estão presentes em todos os shoppings center do país"
        else ->
            "Desculpe, não entendi bem. Você disse: '$message'. Tente digitar 'ajuda'."
    }
}