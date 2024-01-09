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
    fun getId(): UUID { return id }
    fun setNumber(n: Int) { number = n }
    fun getNumber(): Int { return number }
    fun setQuestion(q: String) { question = q }
    fun getQuestion(): String { return question }
    fun setAnswer(a: String) { answer = a }
    fun getAnswer(): String { return answer }
    fun setCorrectAnswer(ca: String) { correctAnswer = ca }
    fun getCorrectAnswer(): String { return correctAnswer }

}