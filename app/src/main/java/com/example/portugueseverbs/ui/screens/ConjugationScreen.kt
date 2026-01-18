package com.example.portugueseverbs.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.portugueseverbs.model.*
import com.example.portugueseverbs.viewmodel.VerbUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConjugationScreen(
    uiState: VerbUiState,
    onBackClick: () -> Unit
) {
    val verb = uiState.currentVerb ?: return
    val conjugation = uiState.currentConjugation ?: return

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(72.dp)
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            // Verb info card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = verb.infinitive,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = verb.translation ?: "(verbo regular)",
                            style = MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic
                        )
                        if (verb.isIrregular) {
                            Text(
                                text = "verbo irregular",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        text = verb.conjugationGroup.displayName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Conjugation card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    // Tense name - bold header
                    Text(
                        text = conjugation.tense.fullDisplayName,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    if (conjugation.tense.isCompound) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "(Tempo composto)",
                            style = MaterialTheme.typography.bodyLarge,
                            fontStyle = FontStyle.Italic
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outline,
                        thickness = 2.dp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Conjugation content
                    if (isNominalForm(conjugation.tense)) {
                        NominalFormContent(conjugation)
                    } else {
                        ConjugationTable(conjugation)
                    }
                }
            }
        }
    }
}

@Composable
private fun NominalFormContent(conjugation: Conjugation) {
    val form = conjugation.forms[Person.EU] ?: ""

    when (conjugation.tense) {
        Tense.INFINITIVO_IMPESSOAL, Tense.GERUNDIO, Tense.PARTICIPIO -> {
            Text(
                text = form,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
        }
        else -> {
            ConjugationTable(conjugation)
        }
    }
}

@Composable
private fun ConjugationTable(conjugation: Conjugation) {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Person.entries.forEach { person ->
            val form = conjugation.forms[person]
            if (form != null && form != "-") {
                ConjugationRow(person = person, form = form)
            } else if (form == "-") {
                ConjugationRow(person = person, form = "—", isDisabled = true)
            }
        }
    }
}

@Composable
private fun ConjugationRow(
    person: Person,
    form: String,
    isDisabled: Boolean = false
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Person - italic for grammatical terms
        Text(
            text = person.displayName,
            style = MaterialTheme.typography.titleLarge,
            fontStyle = FontStyle.Italic,
            fontWeight = if (isDisabled) FontWeight.Light else FontWeight.Normal,
            modifier = Modifier.weight(0.35f)
        )
        // Conjugated form - bold for emphasis
        Text(
            text = form,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = if (isDisabled) FontWeight.Light else FontWeight.Bold,
            modifier = Modifier.weight(0.65f)
        )
    }
}

private fun isNominalForm(tense: Tense): Boolean {
    return tense in listOf(
        Tense.INFINITIVO_IMPESSOAL,
        Tense.GERUNDIO,
        Tense.PARTICIPIO
    )
}
