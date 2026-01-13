package com.example.portugueseverbs.model

data class Verb(
    val infinitive: String,
    val translation: String,
    val conjugationGroup: ConjugationGroup,
    val isIrregular: Boolean = false
)

enum class ConjugationGroup(val displayName: String, val ending: String) {
    FIRST("-ar", "ar"),
    SECOND("-er", "er"),
    THIRD("-ir", "ir")
}
