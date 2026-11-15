plugins {
    // Deixe apenas o ID, sem a tag "version"
    id("org.jetbrains.kotlin.jvm")
}

group = "com.example.server"
version = "1.0"

kotlin {
    jvmToolchain(21) // Ou 17, ou 25 (certifique-se de que a versão escolhida está instalada no seu PC)
}

dependencies {
    // Vazio por enquanto
}