package com.example.wordnote.model

data class WordResult(
    val word: String,
    val meanings: List<Meaning>
)

data class Meaning(
    val definitions: List<Definition>
)

data class Definition(
    val definition: String
)
