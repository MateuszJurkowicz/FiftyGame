package com.example.fiftygame.data.models

class FieldStorage {
    companion object {
        private val exampleFields: List<Field> by lazy {
            val gameId = 1
            listOf(
                Field(0, 1, "Kubek", "Która klasa jest podstawową klasą do zarządzania bazą danych w Androidzie?", "RoomDatabase", gameId),
                Field(0, 2, "Plac zabaw", "Co to jest Intent w Androidzie?", "Obiekt używany do przekazywania danych", gameId),
                Field(0, 3, "Jetpack", "W którym roku powstał android?", "2007", gameId),
                Field(0, 4, "Compose", "Który kraj ma największą populację?", "Indie", gameId),
                Field(0, 5, "Laptop", "Jaki jest najwyższy szczyt na Ziemi?", "Mount Everest", gameId),
                Field(0, 6, "Biblioteka", "Ile planet jest w Układzie Słonecznym?", "Osiem", gameId),
                Field(0, 7, "Zespół", "Kto napisał \"Romeo i Julia\"?", "William Shakespeare", gameId),
                Field(0, 8, "Projekt", "Który pierwiastek chemiczny ma symbol \"O\"?", "Tlen", gameId),
                Field(0, 9, "Kurtka", "Jaka jest stolica Francji?", "Paryż", gameId),
                Field(0, 10, "Krzesło", "Jak nazywa się największa rzeka na świecie?", "Amazonka", gameId),
                Field(0, 11, "Angielski", "Które zwierzę jest największe na lądzie?", "Słoń afrykański", gameId),
                Field(0, 12, "Autobus", "Co jest stolicą Australii?", "Canberra", gameId),
                Field(0, 13, "PB", "Kto wynalazł telegraf?", "Samuel Morse", gameId),
                Field(0, 14, "Informatyka", "Ile kontynentów jest na Ziemi?", "Siedem", gameId),
                Field(0, 15, "Radość", "Jaki kolor ma krew nietoperzy?", "Czerwony", gameId),
                Field(0, 16, "Klawiatura", "Kto był pierwszym prezydentem Stanów Zjednoczonych?", "George Washington", gameId),
                Field(0, 17, "Blat", "Jak nazywa się najdłuższa góra na Ziemi?", "Andy", gameId),
                Field(0, 18, "Pufa", "Kto odkrył penicylinę?", "Alexander Fleming", gameId),
                Field(0, 19, "Słup", "Ile dni ma rok przestępny?", "366", gameId),
                Field(0, 20, "Bankomat", "Jaki pierwiastek chemiczny ma symbol \"H\"?", "Wodór", gameId),

            )

        }
        fun getExampleFields(gameId: Int): List<Field> {
            return exampleFields.map { it.copy(gameId = gameId) }
        }
    }
}