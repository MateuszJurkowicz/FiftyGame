package com.example.fiftygame.data.models

class FieldStorage {
    companion object {
        private val exampleFields: List<Field> by lazy {
            // Tutaj inicjujemy listę przykładowych pól
            listOf(
                Field(0, 1, "Kubek", "Która klasa jest podstawową klasą do zarządzania bazą danych w Androidzie?", "RoomDatabase", 1),
                Field(0, 2, "Plac zabaw", "Co to jest Intent w Androidzie?", "Obiekt używany do przekazywania danych", 1),
                Field(0, 3, "Jetpack", "W którym roku powstał android?", "2007", 1)
                // Dodaj więcej pól według potrzeb
            )
        }
    }
}