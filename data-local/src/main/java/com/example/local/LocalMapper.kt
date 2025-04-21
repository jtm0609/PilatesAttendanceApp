package com.example.local

interface LocalMapper<DataModel> {

    fun toData(): DataModel
}