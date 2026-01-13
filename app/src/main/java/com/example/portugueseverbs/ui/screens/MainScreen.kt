package com.example.portugueseverbs.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.portugueseverbs.model.Tense
import com.example.portugueseverbs.viewmodel.TenseGroup
import com.example.portugueseverbs.viewmodel.VerbUiState
import com.example.portugueseverbs.viewmodel.VerbViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: VerbUiState,
    viewModel: VerbViewModel,
    onVerbInputChange: (String) -> Unit,
    onTenseClick: (Tense) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Verbos Portugueses") },
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
        ) {
            // Verb input field
            VerbInputField(
                value = uiState.verbInput,
                onValueChange = onVerbInputChange,
                isValid = uiState.verbFound,
                verb = uiState.currentVerb,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Error message
            AnimatedVisibility(visible = uiState.errorMessage != null) {
                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            // Verb info when found
            AnimatedVisibility(visible = uiState.verbFound && uiState.currentVerb != null) {
                uiState.currentVerb?.let { verb ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = verb.infinitive,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = verb.translation,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                                )
                            }
                            Text(
                                text = verb.conjugationGroup.displayName,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Instruction text
            Text(
                text = if (uiState.verbFound) "Selecione um tempo verbal:" else "Digite um verbo para começar",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tense groups list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(viewModel.getAllTenseGroups()) { group ->
                    TenseGroupCard(
                        group = group,
                        tenses = viewModel.getTensesForGroup(group),
                        enabled = uiState.verbFound,
                        onTenseClick = onTenseClick
                    )
                }
            }
        }
    }
}

@Composable
private fun VerbInputField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    verb: com.example.portugueseverbs.model.Verb?,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Digite o verbo (infinitivo)") },
        placeholder = { Text("ex: falar, comer, partir") },
        singleLine = true,
        trailingIcon = {
            Row {
                if (isValid) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Verbo válido",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onValueChange("") }) {
                        Icon(Icons.Default.Clear, contentDescription = "Limpar")
                    }
                }
            }
        },
        isError = value.isNotEmpty() && !isValid,
        supportingText = {
            if (value.isNotEmpty() && !isValid) {
                Text("Verbo não encontrado na base de dados")
            }
        },
        modifier = modifier
    )
}

@Composable
private fun TenseGroupCard(
    group: TenseGroup,
    tenses: List<Tense>,
    enabled: Boolean,
    onTenseClick: (Tense) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (enabled)
                MaterialTheme.colorScheme.surface
            else
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (enabled) 2.dp else 0.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Group header
            Text(
                text = group.displayName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = if (enabled)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Tense chips
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tenses.forEach { tense ->
                    TenseChip(
                        tense = tense,
                        enabled = enabled,
                        onClick = { onTenseClick(tense) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRow(
    horizontalArrangement: Arrangement.Horizontal,
    verticalArrangement: Arrangement.Vertical,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.FlowRow(
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = verticalArrangement,
        content = { content() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TenseChip(
    tense: Tense,
    enabled: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = false,
        onClick = onClick,
        enabled = enabled,
        label = {
            Text(
                text = tense.displayName,
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}
