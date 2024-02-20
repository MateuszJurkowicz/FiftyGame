package com.example.fiftygame.data.models

class FieldStorage {
    companion object {
        private val exampleFields: List<Field> by lazy {
            val fieldId = "0"
            listOf(
                Field(fieldId, 1, "Kubek", "Która klasa jest podstawową klasą do zarządzania bazą danych w Androidzie?", "RoomDatabase"),
                Field(fieldId, 2, "Plac zabaw", "Co to jest Intent w Androidzie?", "Obiekt używany do przekazywania danych"),
                Field(fieldId, 3, "Jetpack", "W którym roku powstał android?", "2007"),
                Field(fieldId, 4, "Compose", "Który kraj ma największą populację?", "Indie"),
                Field(fieldId, 5, "Laptop", "Jaki jest najwyższy szczyt na Ziemi?", "Mount Everest"),
                Field(fieldId, 6, "Biblioteka", "Ile planet jest w Układzie Słonecznym?", "Osiem"),
                Field(fieldId, 7, "Zespół", "Kto napisał \"Romeo i Julia\"?", "William Shakespeare"),
                Field(fieldId, 8, "Projekt", "Który pierwiastek chemiczny ma symbol \"O\"?", "Tlen"),
                Field(fieldId, 9, "Kurtka", "Jaka jest stolica Francji?", "Paryż"),
                Field(fieldId, 10, "Krzesło", "Jak nazywa się największa rzeka na świecie?", "Amazonka"),
                Field(fieldId, 11, "Angielski", "Które zwierzę jest największe na lądzie?", "Słoń afrykański"),
                Field(fieldId, 12, "Autobus", "Co jest stolicą Australii?", "Canberra"),
                Field(fieldId, 13, "PB", "Kto wynalazł telegraf?", "Samuel Morse"),
                Field(fieldId, 14, "Informatyka", "Ile kontynentów jest na Ziemi?", "Siedem"),
                Field(fieldId, 15, "Radość", "Jaki kolor ma krew nietoperzy?", "Czerwony"),
                Field(fieldId, 16, "Klawiatura", "Kto był pierwszym prezydentem Stanów Zjednoczonych?", "George Washington"),
                Field(fieldId, 17, "Blat", "Jak nazywa się najdłuższa góra na Ziemi?", "Andy"),
                Field(fieldId, 18, "Pufa", "Kto odkrył penicylinę?", "Alexander Fleming"),
                Field(fieldId, 19, "Słup", "Ile dni ma rok przestępny?", "366"),
                Field(fieldId, 20, "Bankomat", "Jaki pierwiastek chemiczny ma symbol \"H\"?", "Wodór"),

            )

        }
        fun getExampleFields(gameId: String): List<Field> {
            return exampleFields.map { it.copy(fieldId = gameId) }
        }
    }
}