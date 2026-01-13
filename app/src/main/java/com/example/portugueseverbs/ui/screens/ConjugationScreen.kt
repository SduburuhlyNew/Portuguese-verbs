package com.example.portugueseverbs.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                title = {
                    Column {
                        Text(verb.infinitive)
                        Text(
                            text = conjugation.tense.displayName,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Verb info card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = verb.translation,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        if (verb.isIrregular) {
                            Text(
                                text = "Verbo irregular",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                    Text(
                        text = verb.conjugationGroup.displayName,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Conjugation card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Tense name
                    Text(
                        text = conjugation.tense.displayName,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    if (conjugation.tense.isCompound) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Surface(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                text = "Tempo composto",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Divider(color = MaterialTheme.colorScheme.outlineVariant)

                    Spacer(modifier = Modifier.height(20.dp))

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
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        else -> {
            ConjugationTable(conjugation)
        }
    }
}

@Composable
private fun ConjugationTable(conjugation: Conjugation) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Person.entries.forEach { person ->
            val form = conjugation.forms[person]
            if (form != null && form != "-") {
                ConjugationRow(person = person, form = form)
            } else if (form == "-") {
                ConjugationRow(person = person, form = "-", isDisabled = true)
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
        Text(
            text = person.displayName,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isDisabled)
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            else
                MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(0.4f)
        )
        Text(
            text = form,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = if (isDisabled)
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
            else
                MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(0.6f)
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
