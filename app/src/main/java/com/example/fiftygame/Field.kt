package com.example.fiftygame

import java.util.UUID

class Field {
    var id: UUID
    var number: Int = 0
    lateinit var question: String
    lateinit var answer: String
    lateinit var correctAnswer: String
    constructor(){
        id = UUID.randomUUID();
    }


}