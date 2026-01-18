package com.example.portugueseverbs.data

import com.example.portugueseverbs.model.*

class ConjugationEngine {

    fun conjugate(verb: Verb, tense: Tense): Conjugation {
        return if (verb.isIrregular) {
            getIrregularConjugation(verb, tense) ?: conjugateRegular(verb, tense)
        } else {
            conjugateRegular(verb, tense)
        }
    }

    fun getAllConjugations(verb: Verb): List<Conjugation> {
        return Tense.entries.map { tense -> conjugate(verb, tense) }
    }

    fun getConjugationsByCategory(verb: Verb, category: TenseCategory): List<Conjugation> {
        return Tense.entries
            .filter { it.category == category }
            .map { tense -> conjugate(verb, tense) }
    }

    private fun conjugateRegular(verb: Verb, tense: Tense): Conjugation {
        val stem = verb.infinitive.dropLast(2)
        val forms = when (tense) {
            // Indicativo
            Tense.PRESENTE -> conjugatePresente(stem, verb.conjugationGroup)
            Tense.PRETERITO_IMPERFEITO -> conjugatePreteritoImperfeito(stem, verb.conjugationGroup)
            Tense.PRETERITO_PERFEITO -> conjugatePreteritoPerfeito(stem, verb.conjugationGroup)
            Tense.PRETERITO_PERFEITO_COMPOSTO -> conjugateCompoundTense(verb, "tenho", "tens", "tem", "temos", "tendes", "têm")
            Tense.PRETERITO_MAIS_QUE_PERFEITO -> conjugatePreteritoMaisQuePerfeito(stem, verb.conjugationGroup)
            Tense.PRETERITO_MAIS_QUE_PERFEITO_COMPOSTO -> conjugateCompoundTense(verb, "tinha", "tinhas", "tinha", "tínhamos", "tínheis", "tinham")
            Tense.FUTURO_PRESENTE -> conjugateFuturoPresente(verb.infinitive)
            Tense.FUTURO_PRESENTE_COMPOSTO -> conjugateCompoundTense(verb, "terei", "terás", "terá", "teremos", "tereis", "terão")
            Tense.FUTURO_PRETERITO -> conjugateFuturoPriterito(verb.infinitive)
            Tense.FUTURO_PRETERITO_COMPOSTO -> conjugateCompoundTense(verb, "teria", "terias", "teria", "teríamos", "teríeis", "teriam")

            // Subjuntivo
            Tense.SUBJUNTIVO_PRESENTE -> conjugateSubjuntivoPresente(stem, verb.conjugationGroup)
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO -> conjugateSubjuntivoPreteritoImperfeito(stem, verb.conjugationGroup)
            Tense.SUBJUNTIVO_PRETERITO_PERFEITO -> conjugateCompoundTense(verb, "tenha", "tenhas", "tenha", "tenhamos", "tenhais", "tenham")
            Tense.SUBJUNTIVO_PRETERITO_MAIS_QUE_PERFEITO -> conjugateCompoundTense(verb, "tivesse", "tivesses", "tivesse", "tivéssemos", "tivésseis", "tivessem")
            Tense.SUBJUNTIVO_FUTURO -> conjugateSubjuntivoFuturo(stem, verb.conjugationGroup)
            Tense.SUBJUNTIVO_FUTURO_COMPOSTO -> conjugateCompoundTense(verb, "tiver", "tiveres", "tiver", "tivermos", "tiverdes", "tiverem")

            // Imperativo
            Tense.IMPERATIVO_AFIRMATIVO -> conjugateImperativoAfirmativo(stem, verb.conjugationGroup)
            Tense.IMPERATIVO_NEGATIVO -> conjugateImperativoNegativo(stem, verb.conjugationGroup)

            // Formas Nominais
            Tense.INFINITIVO_PESSOAL -> conjugateInfinitivoPessoal(verb.infinitive)
            Tense.INFINITIVO_IMPESSOAL -> mapOf(Person.EU to verb.infinitive)
            Tense.GERUNDIO -> mapOf(Person.EU to getGerundio(stem, verb.conjugationGroup))
            Tense.PARTICIPIO -> mapOf(Person.EU to getParticipio(stem, verb.conjugationGroup))
        }
        return Conjugation(tense, forms)
    }

    // Presente do Indicativo
    private fun conjugatePresente(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "${stem}o",
                Person.TU to "${stem}as",
                Person.ELE_ELA_VOCE to "${stem}a",
                Person.NOS to "${stem}amos",
                Person.VOS to "${stem}ais",
                Person.ELES_ELAS_VOCES to "${stem}am"
            )
            ConjugationGroup.SECOND -> mapOf(
                Person.EU to "${stem}o",
                Person.TU to "${stem}es",
                Person.ELE_ELA_VOCE to "${stem}e",
                Person.NOS to "${stem}emos",
                Person.VOS to "${stem}eis",
                Person.ELES_ELAS_VOCES to "${stem}em"
            )
            ConjugationGroup.THIRD -> mapOf(
                Person.EU to "${stem}o",
                Person.TU to "${stem}es",
                Person.ELE_ELA_VOCE to "${stem}e",
                Person.NOS to "${stem}imos",
                Person.VOS to "${stem}is",
                Person.ELES_ELAS_VOCES to "${stem}em"
            )
        }
    }

    // Pretérito Imperfeito do Indicativo
    private fun conjugatePreteritoImperfeito(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "${stem}ava",
                Person.TU to "${stem}avas",
                Person.ELE_ELA_VOCE to "${stem}ava",
                Person.NOS to "${stem}ávamos",
                Person.VOS to "${stem}áveis",
                Person.ELES_ELAS_VOCES to "${stem}avam"
            )
            ConjugationGroup.SECOND, ConjugationGroup.THIRD -> mapOf(
                Person.EU to "${stem}ia",
                Person.TU to "${stem}ias",
                Person.ELE_ELA_VOCE to "${stem}ia",
                Person.NOS to "${stem}íamos",
                Person.VOS to "${stem}íeis",
                Person.ELES_ELAS_VOCES to "${stem}iam"
            )
        }
    }

    // Pretérito Perfeito do Indicativo
    private fun conjugatePreteritoPerfeito(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "${stem}ei",
                Person.TU to "${stem}aste",
                Person.ELE_ELA_VOCE to "${stem}ou",
                Person.NOS to "${stem}ámos",
                Person.VOS to "${stem}astes",
                Person.ELES_ELAS_VOCES to "${stem}aram"
            )
            ConjugationGroup.SECOND -> mapOf(
                Person.EU to "${stem}i",
                Person.TU to "${stem}este",
                Person.ELE_ELA_VOCE to "${stem}eu",
                Person.NOS to "${stem}emos",
                Person.VOS to "${stem}estes",
                Person.ELES_ELAS_VOCES to "${stem}eram"
            )
            ConjugationGroup.THIRD -> mapOf(
                Person.EU to "${stem}i",
                Person.TU to "${stem}iste",
                Person.ELE_ELA_VOCE to "${stem}iu",
                Person.NOS to "${stem}imos",
                Person.VOS to "${stem}istes",
                Person.ELES_ELAS_VOCES to "${stem}iram"
            )
        }
    }

    // Pretérito Mais-que-perfeito do Indicativo
    private fun conjugatePreteritoMaisQuePerfeito(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "${stem}ara",
                Person.TU to "${stem}aras",
                Person.ELE_ELA_VOCE to "${stem}ara",
                Person.NOS to "${stem}áramos",
                Person.VOS to "${stem}áreis",
                Person.ELES_ELAS_VOCES to "${stem}aram"
            )
            ConjugationGroup.SECOND -> mapOf(
                Person.EU to "${stem}era",
                Person.TU to "${stem}eras",
                Person.ELE_ELA_VOCE to "${stem}era",
                Person.NOS to "${stem}êramos",
                Person.VOS to "${stem}êreis",
                Person.ELES_ELAS_VOCES to "${stem}eram"
            )
            ConjugationGroup.THIRD -> mapOf(
                Person.EU to "${stem}ira",
                Person.TU to "${stem}iras",
                Person.ELE_ELA_VOCE to "${stem}ira",
                Person.NOS to "${stem}íramos",
                Person.VOS to "${stem}íreis",
                Person.ELES_ELAS_VOCES to "${stem}iram"
            )
        }
    }

    // Futuro do Presente do Indicativo
    private fun conjugateFuturoPresente(infinitive: String): Map<Person, String> {
        return mapOf(
            Person.EU to "${infinitive}ei",
            Person.TU to "${infinitive}ás",
            Person.ELE_ELA_VOCE to "${infinitive}á",
            Person.NOS to "${infinitive}emos",
            Person.VOS to "${infinitive}eis",
            Person.ELES_ELAS_VOCES to "${infinitive}ão"
        )
    }

    // Futuro do Pretérito do Indicativo (Condicional)
    private fun conjugateFuturoPriterito(infinitive: String): Map<Person, String> {
        return mapOf(
            Person.EU to "${infinitive}ia",
            Person.TU to "${infinitive}ias",
            Person.ELE_ELA_VOCE to "${infinitive}ia",
            Person.NOS to "${infinitive}íamos",
            Person.VOS to "${infinitive}íeis",
            Person.ELES_ELAS_VOCES to "${infinitive}iam"
        )
    }

    // Presente do Subjuntivo
    private fun conjugateSubjuntivoPresente(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "${stem}e",
                Person.TU to "${stem}es",
                Person.ELE_ELA_VOCE to "${stem}e",
                Person.NOS to "${stem}emos",
                Person.VOS to "${stem}eis",
                Person.ELES_ELAS_VOCES to "${stem}em"
            )
            ConjugationGroup.SECOND, ConjugationGroup.THIRD -> mapOf(
                Person.EU to "${stem}a",
                Person.TU to "${stem}as",
                Person.ELE_ELA_VOCE to "${stem}a",
                Person.NOS to "${stem}amos",
                Person.VOS to "${stem}ais",
                Person.ELES_ELAS_VOCES to "${stem}am"
            )
        }
    }

    // Pretérito Imperfeito do Subjuntivo
    private fun conjugateSubjuntivoPreteritoImperfeito(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "${stem}asse",
                Person.TU to "${stem}asses",
                Person.ELE_ELA_VOCE to "${stem}asse",
                Person.NOS to "${stem}ássemos",
                Person.VOS to "${stem}ásseis",
                Person.ELES_ELAS_VOCES to "${stem}assem"
            )
            ConjugationGroup.SECOND -> mapOf(
                Person.EU to "${stem}esse",
                Person.TU to "${stem}esses",
                Person.ELE_ELA_VOCE to "${stem}esse",
                Person.NOS to "${stem}êssemos",
                Person.VOS to "${stem}êsseis",
                Person.ELES_ELAS_VOCES to "${stem}essem"
            )
            ConjugationGroup.THIRD -> mapOf(
                Person.EU to "${stem}isse",
                Person.TU to "${stem}isses",
                Person.ELE_ELA_VOCE to "${stem}isse",
                Person.NOS to "${stem}íssemos",
                Person.VOS to "${stem}ísseis",
                Person.ELES_ELAS_VOCES to "${stem}issem"
            )
        }
    }

    // Futuro do Subjuntivo
    private fun conjugateSubjuntivoFuturo(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "${stem}ar",
                Person.TU to "${stem}ares",
                Person.ELE_ELA_VOCE to "${stem}ar",
                Person.NOS to "${stem}armos",
                Person.VOS to "${stem}ardes",
                Person.ELES_ELAS_VOCES to "${stem}arem"
            )
            ConjugationGroup.SECOND -> mapOf(
                Person.EU to "${stem}er",
                Person.TU to "${stem}eres",
                Person.ELE_ELA_VOCE to "${stem}er",
                Person.NOS to "${stem}ermos",
                Person.VOS to "${stem}erdes",
                Person.ELES_ELAS_VOCES to "${stem}erem"
            )
            ConjugationGroup.THIRD -> mapOf(
                Person.EU to "${stem}ir",
                Person.TU to "${stem}ires",
                Person.ELE_ELA_VOCE to "${stem}ir",
                Person.NOS to "${stem}irmos",
                Person.VOS to "${stem}irdes",
                Person.ELES_ELAS_VOCES to "${stem}irem"
            )
        }
    }

    // Imperativo Afirmativo
    private fun conjugateImperativoAfirmativo(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "-",
                Person.TU to "${stem}a",
                Person.ELE_ELA_VOCE to "${stem}e",
                Person.NOS to "${stem}emos",
                Person.VOS to "${stem}ai",
                Person.ELES_ELAS_VOCES to "${stem}em"
            )
            ConjugationGroup.SECOND -> mapOf(
                Person.EU to "-",
                Person.TU to "${stem}e",
                Person.ELE_ELA_VOCE to "${stem}a",
                Person.NOS to "${stem}amos",
                Person.VOS to "${stem}ei",
                Person.ELES_ELAS_VOCES to "${stem}am"
            )
            ConjugationGroup.THIRD -> mapOf(
                Person.EU to "-",
                Person.TU to "${stem}e",
                Person.ELE_ELA_VOCE to "${stem}a",
                Person.NOS to "${stem}amos",
                Person.VOS to "${stem}i",
                Person.ELES_ELAS_VOCES to "${stem}am"
            )
        }
    }

    // Imperativo Negativo
    private fun conjugateImperativoNegativo(stem: String, group: ConjugationGroup): Map<Person, String> {
        return when (group) {
            ConjugationGroup.FIRST -> mapOf(
                Person.EU to "-",
                Person.TU to "não ${stem}es",
                Person.ELE_ELA_VOCE to "não ${stem}e",
                Person.NOS to "não ${stem}emos",
                Person.VOS to "não ${stem}eis",
                Person.ELES_ELAS_VOCES to "não ${stem}em"
            )
            ConjugationGroup.SECOND, ConjugationGroup.THIRD -> mapOf(
                Person.EU to "-",
                Person.TU to "não ${stem}as",
                Person.ELE_ELA_VOCE to "não ${stem}a",
                Person.NOS to "não ${stem}amos",
                Person.VOS to "não ${stem}ais",
                Person.ELES_ELAS_VOCES to "não ${stem}am"
            )
        }
    }

    // Infinitivo Pessoal
    private fun conjugateInfinitivoPessoal(infinitive: String): Map<Person, String> {
        return mapOf(
            Person.EU to infinitive,
            Person.TU to "${infinitive}es",
            Person.ELE_ELA_VOCE to infinitive,
            Person.NOS to "${infinitive}mos",
            Person.VOS to "${infinitive}des",
            Person.ELES_ELAS_VOCES to "${infinitive}em"
        )
    }

    // Compound tenses helper
    private fun conjugateCompoundTense(
        verb: Verb,
        eu: String, tu: String, ele: String, nos: String, vos: String, eles: String
    ): Map<Person, String> {
        val participle = getParticipio(verb.infinitive.dropLast(2), verb.conjugationGroup)
        return mapOf(
            Person.EU to "$eu $participle",
            Person.TU to "$tu $participle",
            Person.ELE_ELA_VOCE to "$ele $participle",
            Person.NOS to "$nos $participle",
            Person.VOS to "$vos $participle",
            Person.ELES_ELAS_VOCES to "$eles $participle"
        )
    }

    private fun getGerundio(stem: String, group: ConjugationGroup): String {
        return when (group) {
            ConjugationGroup.FIRST -> "${stem}ando"
            ConjugationGroup.SECOND -> "${stem}endo"
            ConjugationGroup.THIRD -> "${stem}indo"
        }
    }

    private fun getParticipio(stem: String, group: ConjugationGroup): String {
        return when (group) {
            ConjugationGroup.FIRST -> "${stem}ado"
            ConjugationGroup.SECOND -> "${stem}ido"
            ConjugationGroup.THIRD -> "${stem}ido"
        }
    }

    // Irregular verb conjugations
    private fun getIrregularConjugation(verb: Verb, tense: Tense): Conjugation? {
        return irregularConjugations[verb.infinitive]?.get(tense)
    }

    private val irregularConjugations: Map<String, Map<Tense, Conjugation>> = mapOf(
        "ser" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "sou",
                Person.TU to "és",
                Person.ELE_ELA_VOCE to "é",
                Person.NOS to "somos",
                Person.VOS to "sois",
                Person.ELES_ELAS_VOCES to "são"
            )),
            Tense.PRETERITO_IMPERFEITO to Conjugation(Tense.PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "era",
                Person.TU to "eras",
                Person.ELE_ELA_VOCE to "era",
                Person.NOS to "éramos",
                Person.VOS to "éreis",
                Person.ELES_ELAS_VOCES to "eram"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "fui",
                Person.TU to "foste",
                Person.ELE_ELA_VOCE to "foi",
                Person.NOS to "fomos",
                Person.VOS to "fostes",
                Person.ELES_ELAS_VOCES to "foram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "seja",
                Person.TU to "sejas",
                Person.ELE_ELA_VOCE to "seja",
                Person.NOS to "sejamos",
                Person.VOS to "sejais",
                Person.ELES_ELAS_VOCES to "sejam"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "fosse",
                Person.TU to "fosses",
                Person.ELE_ELA_VOCE to "fosse",
                Person.NOS to "fôssemos",
                Person.VOS to "fôsseis",
                Person.ELES_ELAS_VOCES to "fossem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "for",
                Person.TU to "fores",
                Person.ELE_ELA_VOCE to "for",
                Person.NOS to "formos",
                Person.VOS to "fordes",
                Person.ELES_ELAS_VOCES to "forem"
            ))
        ),
        "estar" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "estou",
                Person.TU to "estás",
                Person.ELE_ELA_VOCE to "está",
                Person.NOS to "estamos",
                Person.VOS to "estais",
                Person.ELES_ELAS_VOCES to "estão"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "estive",
                Person.TU to "estiveste",
                Person.ELE_ELA_VOCE to "esteve",
                Person.NOS to "estivemos",
                Person.VOS to "estivestes",
                Person.ELES_ELAS_VOCES to "estiveram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "esteja",
                Person.TU to "estejas",
                Person.ELE_ELA_VOCE to "esteja",
                Person.NOS to "estejamos",
                Person.VOS to "estejais",
                Person.ELES_ELAS_VOCES to "estejam"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "estivesse",
                Person.TU to "estivesses",
                Person.ELE_ELA_VOCE to "estivesse",
                Person.NOS to "estivéssemos",
                Person.VOS to "estivésseis",
                Person.ELES_ELAS_VOCES to "estivessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "estiver",
                Person.TU to "estiveres",
                Person.ELE_ELA_VOCE to "estiver",
                Person.NOS to "estivermos",
                Person.VOS to "estiverdes",
                Person.ELES_ELAS_VOCES to "estiverem"
            ))
        ),
        "ter" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "tenho",
                Person.TU to "tens",
                Person.ELE_ELA_VOCE to "tem",
                Person.NOS to "temos",
                Person.VOS to "tendes",
                Person.ELES_ELAS_VOCES to "têm"
            )),
            Tense.PRETERITO_IMPERFEITO to Conjugation(Tense.PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "tinha",
                Person.TU to "tinhas",
                Person.ELE_ELA_VOCE to "tinha",
                Person.NOS to "tínhamos",
                Person.VOS to "tínheis",
                Person.ELES_ELAS_VOCES to "tinham"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "tive",
                Person.TU to "tiveste",
                Person.ELE_ELA_VOCE to "teve",
                Person.NOS to "tivemos",
                Person.VOS to "tivestes",
                Person.ELES_ELAS_VOCES to "tiveram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "tenha",
                Person.TU to "tenhas",
                Person.ELE_ELA_VOCE to "tenha",
                Person.NOS to "tenhamos",
                Person.VOS to "tenhais",
                Person.ELES_ELAS_VOCES to "tenham"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "tivesse",
                Person.TU to "tivesses",
                Person.ELE_ELA_VOCE to "tivesse",
                Person.NOS to "tivéssemos",
                Person.VOS to "tivésseis",
                Person.ELES_ELAS_VOCES to "tivessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "tiver",
                Person.TU to "tiveres",
                Person.ELE_ELA_VOCE to "tiver",
                Person.NOS to "tivermos",
                Person.VOS to "tiverdes",
                Person.ELES_ELAS_VOCES to "tiverem"
            ))
        ),
        "ir" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "vou",
                Person.TU to "vais",
                Person.ELE_ELA_VOCE to "vai",
                Person.NOS to "vamos",
                Person.VOS to "ides",
                Person.ELES_ELAS_VOCES to "vão"
            )),
            Tense.PRETERITO_IMPERFEITO to Conjugation(Tense.PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "ia",
                Person.TU to "ias",
                Person.ELE_ELA_VOCE to "ia",
                Person.NOS to "íamos",
                Person.VOS to "íeis",
                Person.ELES_ELAS_VOCES to "iam"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "fui",
                Person.TU to "foste",
                Person.ELE_ELA_VOCE to "foi",
                Person.NOS to "fomos",
                Person.VOS to "fostes",
                Person.ELES_ELAS_VOCES to "foram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "vá",
                Person.TU to "vás",
                Person.ELE_ELA_VOCE to "vá",
                Person.NOS to "vamos",
                Person.VOS to "vades",
                Person.ELES_ELAS_VOCES to "vão"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "fosse",
                Person.TU to "fosses",
                Person.ELE_ELA_VOCE to "fosse",
                Person.NOS to "fôssemos",
                Person.VOS to "fôsseis",
                Person.ELES_ELAS_VOCES to "fossem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "for",
                Person.TU to "fores",
                Person.ELE_ELA_VOCE to "for",
                Person.NOS to "formos",
                Person.VOS to "fordes",
                Person.ELES_ELAS_VOCES to "forem"
            ))
        ),
        "vir" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "venho",
                Person.TU to "vens",
                Person.ELE_ELA_VOCE to "vem",
                Person.NOS to "vimos",
                Person.VOS to "vindes",
                Person.ELES_ELAS_VOCES to "vêm"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "vim",
                Person.TU to "vieste",
                Person.ELE_ELA_VOCE to "veio",
                Person.NOS to "viemos",
                Person.VOS to "viestes",
                Person.ELES_ELAS_VOCES to "vieram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "venha",
                Person.TU to "venhas",
                Person.ELE_ELA_VOCE to "venha",
                Person.NOS to "venhamos",
                Person.VOS to "venhais",
                Person.ELES_ELAS_VOCES to "venham"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "viesse",
                Person.TU to "viesses",
                Person.ELE_ELA_VOCE to "viesse",
                Person.NOS to "viéssemos",
                Person.VOS to "viésseis",
                Person.ELES_ELAS_VOCES to "viessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "vier",
                Person.TU to "vieres",
                Person.ELE_ELA_VOCE to "vier",
                Person.NOS to "viermos",
                Person.VOS to "vierdes",
                Person.ELES_ELAS_VOCES to "vierem"
            )),
            Tense.PRETERITO_IMPERFEITO to Conjugation(Tense.PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "vinha",
                Person.TU to "vinhas",
                Person.ELE_ELA_VOCE to "vinha",
                Person.NOS to "vínhamos",
                Person.VOS to "vínheis",
                Person.ELES_ELAS_VOCES to "vinham"
            )),
            Tense.PARTICIPIO to Conjugation(Tense.PARTICIPIO, mapOf(
                Person.EU to "vindo"
            )),
            Tense.GERUNDIO to Conjugation(Tense.GERUNDIO, mapOf(
                Person.EU to "vindo"
            ))
        ),
        "fazer" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "faço",
                Person.TU to "fazes",
                Person.ELE_ELA_VOCE to "faz",
                Person.NOS to "fazemos",
                Person.VOS to "fazeis",
                Person.ELES_ELAS_VOCES to "fazem"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "fiz",
                Person.TU to "fizeste",
                Person.ELE_ELA_VOCE to "fez",
                Person.NOS to "fizemos",
                Person.VOS to "fizestes",
                Person.ELES_ELAS_VOCES to "fizeram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "faça",
                Person.TU to "faças",
                Person.ELE_ELA_VOCE to "faça",
                Person.NOS to "façamos",
                Person.VOS to "façais",
                Person.ELES_ELAS_VOCES to "façam"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "fizesse",
                Person.TU to "fizesses",
                Person.ELE_ELA_VOCE to "fizesse",
                Person.NOS to "fizéssemos",
                Person.VOS to "fizésseis",
                Person.ELES_ELAS_VOCES to "fizessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "fizer",
                Person.TU to "fizeres",
                Person.ELE_ELA_VOCE to "fizer",
                Person.NOS to "fizermos",
                Person.VOS to "fizerdes",
                Person.ELES_ELAS_VOCES to "fizerem"
            )),
            Tense.PARTICIPIO to Conjugation(Tense.PARTICIPIO, mapOf(
                Person.EU to "feito"
            ))
        ),
        "dizer" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "digo",
                Person.TU to "dizes",
                Person.ELE_ELA_VOCE to "diz",
                Person.NOS to "dizemos",
                Person.VOS to "dizeis",
                Person.ELES_ELAS_VOCES to "dizem"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "disse",
                Person.TU to "disseste",
                Person.ELE_ELA_VOCE to "disse",
                Person.NOS to "dissemos",
                Person.VOS to "dissestes",
                Person.ELES_ELAS_VOCES to "disseram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "diga",
                Person.TU to "digas",
                Person.ELE_ELA_VOCE to "diga",
                Person.NOS to "digamos",
                Person.VOS to "digais",
                Person.ELES_ELAS_VOCES to "digam"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "dissesse",
                Person.TU to "dissesses",
                Person.ELE_ELA_VOCE to "dissesse",
                Person.NOS to "disséssemos",
                Person.VOS to "dissésseis",
                Person.ELES_ELAS_VOCES to "dissessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "disser",
                Person.TU to "disseres",
                Person.ELE_ELA_VOCE to "disser",
                Person.NOS to "dissermos",
                Person.VOS to "disserdes",
                Person.ELES_ELAS_VOCES to "disserem"
            )),
            Tense.PARTICIPIO to Conjugation(Tense.PARTICIPIO, mapOf(
                Person.EU to "dito"
            ))
        ),
        "poder" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "posso",
                Person.TU to "podes",
                Person.ELE_ELA_VOCE to "pode",
                Person.NOS to "podemos",
                Person.VOS to "podeis",
                Person.ELES_ELAS_VOCES to "podem"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "pude",
                Person.TU to "pudeste",
                Person.ELE_ELA_VOCE to "pôde",
                Person.NOS to "pudemos",
                Person.VOS to "pudestes",
                Person.ELES_ELAS_VOCES to "puderam"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "possa",
                Person.TU to "possas",
                Person.ELE_ELA_VOCE to "possa",
                Person.NOS to "possamos",
                Person.VOS to "possais",
                Person.ELES_ELAS_VOCES to "possam"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "pudesse",
                Person.TU to "pudesses",
                Person.ELE_ELA_VOCE to "pudesse",
                Person.NOS to "pudéssemos",
                Person.VOS to "pudésseis",
                Person.ELES_ELAS_VOCES to "pudessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "puder",
                Person.TU to "puderes",
                Person.ELE_ELA_VOCE to "puder",
                Person.NOS to "pudermos",
                Person.VOS to "puderdes",
                Person.ELES_ELAS_VOCES to "puderem"
            ))
        ),
        "querer" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "quero",
                Person.TU to "queres",
                Person.ELE_ELA_VOCE to "quer",
                Person.NOS to "queremos",
                Person.VOS to "quereis",
                Person.ELES_ELAS_VOCES to "querem"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "quis",
                Person.TU to "quiseste",
                Person.ELE_ELA_VOCE to "quis",
                Person.NOS to "quisemos",
                Person.VOS to "quisestes",
                Person.ELES_ELAS_VOCES to "quiseram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "queira",
                Person.TU to "queiras",
                Person.ELE_ELA_VOCE to "queira",
                Person.NOS to "queiramos",
                Person.VOS to "queirais",
                Person.ELES_ELAS_VOCES to "queiram"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "quisesse",
                Person.TU to "quisesses",
                Person.ELE_ELA_VOCE to "quisesse",
                Person.NOS to "quiséssemos",
                Person.VOS to "quisésseis",
                Person.ELES_ELAS_VOCES to "quisessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "quiser",
                Person.TU to "quiseres",
                Person.ELE_ELA_VOCE to "quiser",
                Person.NOS to "quisermos",
                Person.VOS to "quiserdes",
                Person.ELES_ELAS_VOCES to "quiserem"
            ))
        ),
        "saber" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "sei",
                Person.TU to "sabes",
                Person.ELE_ELA_VOCE to "sabe",
                Person.NOS to "sabemos",
                Person.VOS to "sabeis",
                Person.ELES_ELAS_VOCES to "sabem"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "soube",
                Person.TU to "soubeste",
                Person.ELE_ELA_VOCE to "soube",
                Person.NOS to "soubemos",
                Person.VOS to "soubestes",
                Person.ELES_ELAS_VOCES to "souberam"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "saiba",
                Person.TU to "saibas",
                Person.ELE_ELA_VOCE to "saiba",
                Person.NOS to "saibamos",
                Person.VOS to "saibais",
                Person.ELES_ELAS_VOCES to "saibam"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "soubesse",
                Person.TU to "soubesses",
                Person.ELE_ELA_VOCE to "soubesse",
                Person.NOS to "soubéssemos",
                Person.VOS to "soubésseis",
                Person.ELES_ELAS_VOCES to "soubessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "souber",
                Person.TU to "souberes",
                Person.ELE_ELA_VOCE to "souber",
                Person.NOS to "soubermos",
                Person.VOS to "souberdes",
                Person.ELES_ELAS_VOCES to "souberem"
            ))
        ),
        "ver" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "vejo",
                Person.TU to "vês",
                Person.ELE_ELA_VOCE to "vê",
                Person.NOS to "vemos",
                Person.VOS to "vedes",
                Person.ELES_ELAS_VOCES to "veem"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "vi",
                Person.TU to "viste",
                Person.ELE_ELA_VOCE to "viu",
                Person.NOS to "vimos",
                Person.VOS to "vistes",
                Person.ELES_ELAS_VOCES to "viram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "veja",
                Person.TU to "vejas",
                Person.ELE_ELA_VOCE to "veja",
                Person.NOS to "vejamos",
                Person.VOS to "vejais",
                Person.ELES_ELAS_VOCES to "vejam"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "visse",
                Person.TU to "visses",
                Person.ELE_ELA_VOCE to "visse",
                Person.NOS to "víssemos",
                Person.VOS to "vísseis",
                Person.ELES_ELAS_VOCES to "vissem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "vir",
                Person.TU to "vires",
                Person.ELE_ELA_VOCE to "vir",
                Person.NOS to "virmos",
                Person.VOS to "virdes",
                Person.ELES_ELAS_VOCES to "virem"
            )),
            Tense.PARTICIPIO to Conjugation(Tense.PARTICIPIO, mapOf(
                Person.EU to "visto"
            ))
        ),
        "dar" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "dou",
                Person.TU to "dás",
                Person.ELE_ELA_VOCE to "dá",
                Person.NOS to "damos",
                Person.VOS to "dais",
                Person.ELES_ELAS_VOCES to "dão"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "dei",
                Person.TU to "deste",
                Person.ELE_ELA_VOCE to "deu",
                Person.NOS to "demos",
                Person.VOS to "destes",
                Person.ELES_ELAS_VOCES to "deram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "dê",
                Person.TU to "dês",
                Person.ELE_ELA_VOCE to "dê",
                Person.NOS to "demos",
                Person.VOS to "deis",
                Person.ELES_ELAS_VOCES to "deem"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "desse",
                Person.TU to "desses",
                Person.ELE_ELA_VOCE to "desse",
                Person.NOS to "déssemos",
                Person.VOS to "désseis",
                Person.ELES_ELAS_VOCES to "dessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "der",
                Person.TU to "deres",
                Person.ELE_ELA_VOCE to "der",
                Person.NOS to "dermos",
                Person.VOS to "derdes",
                Person.ELES_ELAS_VOCES to "derem"
            ))
        ),
        "trazer" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "trago",
                Person.TU to "trazes",
                Person.ELE_ELA_VOCE to "traz",
                Person.NOS to "trazemos",
                Person.VOS to "trazeis",
                Person.ELES_ELAS_VOCES to "trazem"
            )),
            Tense.PRETERITO_PERFEITO to Conjugation(Tense.PRETERITO_PERFEITO, mapOf(
                Person.EU to "trouxe",
                Person.TU to "trouxeste",
                Person.ELE_ELA_VOCE to "trouxe",
                Person.NOS to "trouxemos",
                Person.VOS to "trouxestes",
                Person.ELES_ELAS_VOCES to "trouxeram"
            )),
            Tense.SUBJUNTIVO_PRESENTE to Conjugation(Tense.SUBJUNTIVO_PRESENTE, mapOf(
                Person.EU to "traga",
                Person.TU to "tragas",
                Person.ELE_ELA_VOCE to "traga",
                Person.NOS to "tragamos",
                Person.VOS to "tragais",
                Person.ELES_ELAS_VOCES to "tragam"
            )),
            Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO to Conjugation(Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO, mapOf(
                Person.EU to "trouxesse",
                Person.TU to "trouxesses",
                Person.ELE_ELA_VOCE to "trouxesse",
                Person.NOS to "trouxéssemos",
                Person.VOS to "trouxésseis",
                Person.ELES_ELAS_VOCES to "trouxessem"
            )),
            Tense.SUBJUNTIVO_FUTURO to Conjugation(Tense.SUBJUNTIVO_FUTURO, mapOf(
                Person.EU to "trouxer",
                Person.TU to "trouxeres",
                Person.ELE_ELA_VOCE to "trouxer",
                Person.NOS to "trouxermos",
                Person.VOS to "trouxerdes",
                Person.ELES_ELAS_VOCES to "trouxerem"
            ))
        ),
        "pedir" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "peço",
                Person.TU to "pedes",
                Person.ELE_ELA_VOCE to "pede",
                Person.NOS to "pedimos",
                Person.VOS to "pedis",
                Person.ELES_ELAS_VOCES to "pedem"
            ))
        ),
        "dormir" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "durmo",
                Person.TU to "dormes",
                Person.ELE_ELA_VOCE to "dorme",
                Person.NOS to "dormimos",
                Person.VOS to "dormis",
                Person.ELES_ELAS_VOCES to "dormem"
            ))
        ),
        "sentir" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "sinto",
                Person.TU to "sentes",
                Person.ELE_ELA_VOCE to "sente",
                Person.NOS to "sentimos",
                Person.VOS to "sentis",
                Person.ELES_ELAS_VOCES to "sentem"
            ))
        ),
        "ouvir" to mapOf(
            Tense.PRESENTE to Conjugation(Tense.PRESENTE, mapOf(
                Person.EU to "ouço",
                Person.TU to "ouves",
                Person.ELE_ELA_VOCE to "ouve",
                Person.NOS to "ouvimos",
                Person.VOS to "ouvis",
                Person.ELES_ELAS_VOCES to "ouvem"
            ))
        )
    )
}
