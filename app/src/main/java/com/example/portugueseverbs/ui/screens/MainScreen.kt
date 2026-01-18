package com.example.portugueseverbs.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
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
                title = {
                    Text(
                        text = "Verbos Portugueses",
                        fontWeight = FontWeight.Bold
                    )
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
                .imePadding()
        ) {
            // Fixed top section: Verb input field
            VerbInputField(
                value = uiState.verbInput,
                onValueChange = onVerbInputChange,
                isValid = uiState.verbFound,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )

            // Error message
            AnimatedVisibility(visible = uiState.errorMessage != null) {
                uiState.errorMessage?.let { error ->
                    Text(
                        text = error,
                        style = MaterialTheme.typography.bodyMedium,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }
            }

            // Fixed section: Verb info when found
            AnimatedVisibility(visible = uiState.verbFound && uiState.currentVerb != null) {
                uiState.currentVerb?.let { verb ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp),
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
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Instruction text
            Text(
                text = if (uiState.verbFound) "Selecione um tempo verbal:" else "Digite um verbo para começar",
                style = MaterialTheme.typography.titleMedium,
                fontStyle = if (!uiState.verbFound) FontStyle.Italic else FontStyle.Normal,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Scrollable tense groups list
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                viewModel.getAllTenseGroups().forEach { group ->
                    TenseGroupCard(
                        group = group,
                        tenses = viewModel.getTensesForGroup(group),
                        enabled = uiState.verbFound,
                        onTenseClick = onTenseClick
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun VerbInputField(
    value: String,
    onValueChange: (String) -> Unit,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = "Digite o verbo (infinitivo)",
                style = MaterialTheme.typography.bodyLarge
            )
        },
        placeholder = {
            Text(
                text = "ex: falar, comer, partir",
                fontStyle = FontStyle.Italic
            )
        },
        textStyle = MaterialTheme.typography.headlineSmall,
        singleLine = true,
        trailingIcon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isValid) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Verbo válido",
                        modifier = Modifier.size(32.dp)
                    )
                }
                if (value.isNotEmpty()) {
                    IconButton(
                        onClick = { onValueChange("") },
                        modifier = Modifier.size(56.dp)
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Limpar",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        },
        isError = value.isNotEmpty() && !isValid,
        supportingText = {
            if (value.isNotEmpty() && !isValid) {
                Text(
                    text = "Verbo não encontrado",
                    style = MaterialTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic
                )
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
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = if (enabled) 2.dp else 1.dp,
            color = if (enabled)
                MaterialTheme.colorScheme.outline
            else
                MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Group header - bold for emphasis
            Text(
                text = group.displayName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(color = MaterialTheme.colorScheme.outline, thickness = 1.dp)

            Spacer(modifier = Modifier.height(16.dp))

            // Tense buttons - larger touch targets
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                tenses.forEach { tense ->
                    TenseButton(
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

@Composable
private fun TenseButton(
    tense: Tense,
    enabled: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.height(56.dp),
        border = BorderStroke(
            width = if (enabled) 2.dp else 1.dp,
            color = if (enabled)
                MaterialTheme.colorScheme.outline
            else
                MaterialTheme.colorScheme.outlineVariant
        ),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
    ) {
        Text(
            text = tense.displayName,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = if (enabled) FontWeight.Medium else FontWeight.Normal
        )
    }
}
