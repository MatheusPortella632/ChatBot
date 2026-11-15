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

<img width="422" height="827" alt="image" src="https://github.com/user-attachments/assets/580cd52d-b73b-4572-8365-5987ab3a207d" />
<img width="491" height="680" alt="image" src="https://github.com/user-attachments/assets/f19ea04a-1cf5-4729-b6c1-b2e30a203fba" />
<img width="659" height="858" alt="image" src="https://github.com/user-attachments/assets/1b17f2c4-ad18-4560-ad2a-9784127260f9" />

