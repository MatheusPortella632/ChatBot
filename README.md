
# 🤖 Android TCP Chatbot (Cliente-Servidor)

Um projeto completo de chat em tempo real desenvolvido em **Kotlin**, utilizando **Jetpack Compose** no lado do cliente (Android) e um **Servidor TCP multi-cliente** rodando em Kotlin (JVM) no backend. O chatbot simula um assistente virtual para uma loja de camisetas.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Kotlin
* **Interface Gráfica:** Jetpack Compose & Material 3 (Android)
* **Arquitetura & Reatividade:** ViewModel, Kotlin Coroutines, StateFlow
* **Rede:** Sockets TCP/IP (`java.net.Socket`, `ServerSocket`, `BufferedReader`, `PrintWriter`)

---

<img width="376" height="521" alt="img chat2" src="https://github.com/user-attachments/assets/9b085e4d-bc7d-413a-bd35-5e20bc75e2f3" />
<img width="270" height="529" alt="img chat 3" src="https://github.com/user-attachments/assets/87a59e8a-fe39-456b-b9d3-9ff915382c2e" />
<img width="422" height="549" alt="imagaem chat" src="https://github.com/user-attachments/assets/52637671-5e8a-45e0-90ca-802b77fd2fb5" />

## 📂 Estrutura do Projeto

```text
├── android-app/                  # Código do Cliente Android
│   ├── ui/                       # Telas e Componentes Jetpack Compose (ChatScreen)
│   └── viewmodel/                # Gerenciamento de Estado e Conexão (ChatViewModel)
│
└── server/                       # Servidor TCP (JVM / Kotlin)
    └── Main.kt                   # Lógica do Servidor e Regras de Negócio do Bot





