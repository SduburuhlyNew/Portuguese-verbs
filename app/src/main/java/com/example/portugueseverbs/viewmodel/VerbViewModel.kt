package com.example.portugueseverbs.viewmodel

import androidx.lifecycle.ViewModel
import com.example.portugueseverbs.data.ConjugationEngine
import com.example.portugueseverbs.data.VerbRepository
import com.example.portugueseverbs.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class TenseGroup(val displayName: String) {
    PRESENT("Presente"),
    PAST("Passado"),
    FUTURE("Futuro"),
    CONJUNCTIVE("Conjuntivo"),
    IMPERATIVE("Imperativo"),
    OTHER("Outras Formas")
}

data class VerbUiState(
    val verbInput: String = "",
    val selectedTense: Tense? = null,
    val currentConjugation: Conjugation? = null,
    val verbFound: Boolean = false,
    val currentVerb: Verb? = null,
    val errorMessage: String? = null
)

class VerbViewModel : ViewModel() {

    private val repository = VerbRepository()
    private val conjugationEngine = ConjugationEngine()

    private val _uiState = MutableStateFlow(VerbUiState())
    val uiState: StateFlow<VerbUiState> = _uiState.asStateFlow()

    fun onVerbInputChange(input: String) {
        val verb = repository.getVerbByInfinitive(input.trim())
        _uiState.value = _uiState.value.copy(
            verbInput = input,
            verbFound = verb != null,
            currentVerb = verb,
            errorMessage = null,
            selectedTense = null,
            currentConjugation = null
        )
    }

    fun selectTense(tense: Tense) {
        val verb = _uiState.value.currentVerb
        if (verb == null) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Por favor, digite um verbo válido primeiro"
            )
            return
        }

        val conjugation = conjugationEngine.conjugate(verb, tense)
        _uiState.value = _uiState.value.copy(
            selectedTense = tense,
            currentConjugation = conjugation,
            errorMessage = null
        )
    }

    fun clearConjugation() {
        _uiState.value = _uiState.value.copy(
            selectedTense = null,
            currentConjugation = null
        )
    }

    fun clearAll() {
        _uiState.value = VerbUiState()
    }

    fun getTensesForGroup(group: TenseGroup): List<Tense> {
        return when (group) {
            TenseGroup.PRESENT -> listOf(
                Tense.PRESENTE
            )
            TenseGroup.PAST -> listOf(
                Tense.PRETERITO_IMPERFEITO,
                Tense.PRETERITO_PERFEITO,
                Tense.PRETERITO_PERFEITO_COMPOSTO,
                Tense.PRETERITO_MAIS_QUE_PERFEITO,
                Tense.PRETERITO_MAIS_QUE_PERFEITO_COMPOSTO
            )
            TenseGroup.FUTURE -> listOf(
                Tense.FUTURO_PRESENTE,
                Tense.FUTURO_PRESENTE_COMPOSTO,
                Tense.FUTURO_PRETERITO,
                Tense.FUTURO_PRETERITO_COMPOSTO
            )
            TenseGroup.CONJUNCTIVE -> listOf(
                Tense.SUBJUNTIVO_PRESENTE,
                Tense.SUBJUNTIVO_PRETERITO_IMPERFEITO,
                Tense.SUBJUNTIVO_PRETERITO_PERFEITO,
                Tense.SUBJUNTIVO_PRETERITO_MAIS_QUE_PERFEITO,
                Tense.SUBJUNTIVO_FUTURO,
                Tense.SUBJUNTIVO_FUTURO_COMPOSTO
            )
            TenseGroup.IMPERATIVE -> listOf(
                Tense.IMPERATIVO_AFIRMATIVO,
                Tense.IMPERATIVO_NEGATIVO
            )
            TenseGroup.OTHER -> listOf(
                Tense.INFINITIVO_PESSOAL,
                Tense.INFINITIVO_IMPESSOAL,
                Tense.GERUNDIO,
                Tense.PARTICIPIO
            )
        }
    }

    fun getAllTenseGroups(): List<TenseGroup> = TenseGroup.entries
}
