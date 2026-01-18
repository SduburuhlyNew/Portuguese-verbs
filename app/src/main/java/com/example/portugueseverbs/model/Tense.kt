package com.example.portugueseverbs.model

enum class TenseCategory(val displayName: String) {
    INDICATIVO("Indicativo"),
    CONJUNTIVO("Conjuntivo"),
    IMPERATIVO("Imperativo"),
    FORMAS_NOMINAIS("Formas Nominais")
}

enum class Tense(
    val displayName: String,
    val category: TenseCategory,
    val isCompound: Boolean = false
) {
    // Indicativo
    PRESENTE("Presente", TenseCategory.INDICATIVO),
    PRETERITO_IMPERFEITO("Pretérito Imperfeito", TenseCategory.INDICATIVO),
    PRETERITO_PERFEITO("Pretérito Perfeito", TenseCategory.INDICATIVO),
    PRETERITO_PERFEITO_COMPOSTO("Pretérito Perfeito Composto", TenseCategory.INDICATIVO, true),
    PRETERITO_MAIS_QUE_PERFEITO("Pretérito Mais-que-perfeito", TenseCategory.INDICATIVO),
    PRETERITO_MAIS_QUE_PERFEITO_COMPOSTO("Pretérito Mais-que-perfeito Composto", TenseCategory.INDICATIVO, true),
    FUTURO_PRESENTE("Futuro do Presente", TenseCategory.INDICATIVO),
    FUTURO_PRESENTE_COMPOSTO("Futuro do Presente Composto", TenseCategory.INDICATIVO, true),
    FUTURO_PRETERITO("Futuro do Pretérito", TenseCategory.INDICATIVO),
    FUTURO_PRETERITO_COMPOSTO("Futuro do Pretérito Composto", TenseCategory.INDICATIVO, true),

    // Conjuntivo
    SUBJUNTIVO_PRESENTE("Presente", TenseCategory.CONJUNTIVO),
    SUBJUNTIVO_PRETERITO_IMPERFEITO("Pretérito Imperfeito", TenseCategory.CONJUNTIVO),
    SUBJUNTIVO_PRETERITO_PERFEITO("Pretérito Perfeito", TenseCategory.CONJUNTIVO, true),
    SUBJUNTIVO_PRETERITO_MAIS_QUE_PERFEITO("Pretérito Mais-que-perfeito", TenseCategory.CONJUNTIVO, true),
    SUBJUNTIVO_FUTURO("Futuro", TenseCategory.CONJUNTIVO),
    SUBJUNTIVO_FUTURO_COMPOSTO("Futuro Composto", TenseCategory.CONJUNTIVO, true),

    // Imperativo
    IMPERATIVO_AFIRMATIVO("Imperativo Afirmativo", TenseCategory.IMPERATIVO),
    IMPERATIVO_NEGATIVO("Imperativo Negativo", TenseCategory.IMPERATIVO),

    // Formas Nominais
    INFINITIVO_PESSOAL("Infinitivo Pessoal", TenseCategory.FORMAS_NOMINAIS),
    INFINITIVO_IMPESSOAL("Infinitivo Impessoal", TenseCategory.FORMAS_NOMINAIS),
    GERUNDIO("Gerúndio", TenseCategory.FORMAS_NOMINAIS),
    PARTICIPIO("Particípio", TenseCategory.FORMAS_NOMINAIS);

    val fullDisplayName: String
        get() = when (category) {
            TenseCategory.INDICATIVO -> "$displayName do Indicativo"
            TenseCategory.CONJUNTIVO -> "$displayName do Conjuntivo"
            TenseCategory.IMPERATIVO -> displayName
            TenseCategory.FORMAS_NOMINAIS -> displayName
        }
}

enum class Person(val displayName: String) {
    EU("eu"),
    TU("tu"),
    ELE_ELA_VOCE("ele/ela/você"),
    NOS("nós"),
    VOS("vós"),
    ELES_ELAS_VOCES("eles/elas/vocês")
}
