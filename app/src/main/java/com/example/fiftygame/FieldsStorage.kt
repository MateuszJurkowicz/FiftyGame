package com.example.fiftygame

class FieldsStorage {
    private val fields: List<Field>? = null
    companion object {          //private static final FieldsStorage fieldsStorage = new FieldsStorage()
        private val fieldsStorage = FieldsStorage()
    }
    fun getInstance(): FieldsStorage { return fieldsStorage }
    constructor(){
        val fields = ArrayList<Field>()
        for(i in 0 until 50)
        {
            val field = Field()
            field.number = i
            fields.add(field)
        }
    }
}