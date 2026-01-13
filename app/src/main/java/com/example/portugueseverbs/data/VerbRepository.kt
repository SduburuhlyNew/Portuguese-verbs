package com.example.portugueseverbs.data

import com.example.portugueseverbs.model.*

class VerbRepository {

    private val verbs = listOf(
        // First conjugation (-ar)
        Verb("falar", "to speak", ConjugationGroup.FIRST),
        Verb("amar", "to love", ConjugationGroup.FIRST),
        Verb("trabalhar", "to work", ConjugationGroup.FIRST),
        Verb("estudar", "to study", ConjugationGroup.FIRST),
        Verb("comprar", "to buy", ConjugationGroup.FIRST),
        Verb("andar", "to walk", ConjugationGroup.FIRST),
        Verb("cantar", "to sing", ConjugationGroup.FIRST),
        Verb("dançar", "to dance", ConjugationGroup.FIRST),
        Verb("morar", "to live (reside)", ConjugationGroup.FIRST),
        Verb("viajar", "to travel", ConjugationGroup.FIRST),
        Verb("dar", "to give", ConjugationGroup.FIRST, isIrregular = true),
        Verb("estar", "to be (temporary)", ConjugationGroup.FIRST, isIrregular = true),

        // Second conjugation (-er)
        Verb("comer", "to eat", ConjugationGroup.SECOND),
        Verb("beber", "to drink", ConjugationGroup.SECOND),
        Verb("vender", "to sell", ConjugationGroup.SECOND),
        Verb("aprender", "to learn", ConjugationGroup.SECOND),
        Verb("escrever", "to write", ConjugationGroup.SECOND),
        Verb("correr", "to run", ConjugationGroup.SECOND),
        Verb("viver", "to live", ConjugationGroup.SECOND),
        Verb("ter", "to have", ConjugationGroup.SECOND, isIrregular = true),
        Verb("ser", "to be (permanent)", ConjugationGroup.SECOND, isIrregular = true),
        Verb("fazer", "to do/make", ConjugationGroup.SECOND, isIrregular = true),
        Verb("poder", "to be able/can", ConjugationGroup.SECOND, isIrregular = true),
        Verb("querer", "to want", ConjugationGroup.SECOND, isIrregular = true),
        Verb("saber", "to know", ConjugationGroup.SECOND, isIrregular = true),
        Verb("ver", "to see", ConjugationGroup.SECOND, isIrregular = true),
        Verb("dizer", "to say", ConjugationGroup.SECOND, isIrregular = true),
        Verb("trazer", "to bring", ConjugationGroup.SECOND, isIrregular = true),

        // Third conjugation (-ir)
        Verb("partir", "to leave/depart", ConjugationGroup.THIRD),
        Verb("abrir", "to open", ConjugationGroup.THIRD),
        Verb("decidir", "to decide", ConjugationGroup.THIRD),
        Verb("dividir", "to divide", ConjugationGroup.THIRD),
        Verb("assistir", "to watch/attend", ConjugationGroup.THIRD),
        Verb("ir", "to go", ConjugationGroup.THIRD, isIrregular = true),
        Verb("vir", "to come", ConjugationGroup.THIRD, isIrregular = true),
        Verb("pedir", "to ask for", ConjugationGroup.THIRD, isIrregular = true),
        Verb("dormir", "to sleep", ConjugationGroup.THIRD, isIrregular = true),
        Verb("sentir", "to feel", ConjugationGroup.THIRD, isIrregular = true),
        Verb("ouvir", "to hear", ConjugationGroup.THIRD, isIrregular = true)
    )

    fun getAllVerbs(): List<Verb> = verbs.sortedBy { it.infinitive }

    fun getVerbsByGroup(group: ConjugationGroup): List<Verb> =
        verbs.filter { it.conjugationGroup == group }.sortedBy { it.infinitive }

    fun getIrregularVerbs(): List<Verb> =
        verbs.filter { it.isIrregular }.sortedBy { it.infinitive }

    fun getRegularVerbs(): List<Verb> =
        verbs.filter { !it.isIrregular }.sortedBy { it.infinitive }

    fun searchVerbs(query: String): List<Verb> =
        verbs.filter {
            it.infinitive.contains(query, ignoreCase = true) ||
            it.translation.contains(query, ignoreCase = true)
        }.sortedBy { it.infinitive }

    fun getVerbByInfinitive(infinitive: String): Verb? =
        verbs.find { it.infinitive.equals(infinitive, ignoreCase = true) }
}
