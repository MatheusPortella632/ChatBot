# 🤖 Android TCP Chatbot (Cliente-Servidor)

Um projeto completo de chat em tempo real desenvolvido em **Kotlin**, utilizando **Jetpack Compose** no lado do cliente (Android) e um **Servidor TCP multi-cliente** rodando em Kotlin (JVM) no backend. O chatbot simula um assistente virtual para uma loja de camisetas.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Kotlin
* **Interface Gráfica:** Jetpack Compose & Material 3 (Android)
* **Arquitetura & Reatividade:** ViewModel, Kotlin Coroutines, StateFlow
* **Rede:** Sockets TCP/IP (`java.net.Socket`, `ServerSocket`, `BufferedReader`, `PrintWriter`)

---

## 📂 Estrutura do Projeto

```text
├── android-app/                  # Código do Cliente Android
│   ├── ui/                       # Telas e Componentes Jetpack Compose (ChatScreen)
│   └── viewmodel/                # Gerenciamento de Estado e Conexão (ChatViewModel)
│
└── server/                       # Servidor TCP (JVM / Kotlin)
    └── Main.kt                   # Lógica do Servidor e Regras de Negócio do Bot



