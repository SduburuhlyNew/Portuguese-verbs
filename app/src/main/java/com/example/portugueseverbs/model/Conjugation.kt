package com.example.portugueseverbs.model

data class Conjugation(
    val tense: Tense,
    val forms: Map<Person, String>
)

data class VerbConjugationResult(
    val infinitive: String,
    val conjugation: Conjugation?,
    val errorMessage: String? = null
)
