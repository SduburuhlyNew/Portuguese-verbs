package com.example.portugueseverbs.model

data class Verb(
    val infinitive: String,
    val translation: String? = null,
    val conjugationGroup: ConjugationGroup,
    val isIrregular: Boolean = false
) {
    companion object {
        /**
         * Creates a Verb from an infinitive string.
         * Returns null if the infinitive doesn't end with -ar, -er, or -ir.
         */
        fun fromInfinitive(infinitive: String): Verb? {
            val normalized = infinitive.trim().lowercase()
            if (normalized.length < 3) return null

            val group = when {
                normalized.endsWith("ar") -> ConjugationGroup.FIRST
                normalized.endsWith("er") -> ConjugationGroup.SECOND
                normalized.endsWith("ir") -> ConjugationGroup.THIRD
                else -> return null
            }

            return Verb(
                infinitive = normalized,
                translation = null,
                conjugationGroup = group,
                isIrregular = false
            )
        }
    }
}

enum class ConjugationGroup(val displayName: String, val ending: String) {
    FIRST("-ar", "ar"),
    SECOND("-er", "er"),
    THIRD("-ir", "ir")
}
