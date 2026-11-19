package com.example.plantguide.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.plantguide.ui.home.TodoItem
import com.example.plantguide.ui.screens.home.TodayUiState
import com.example.plantguide.ui.screens.home.data.TodoTask

@Composable
fun TodaySection(
    state: TodayUiState,
    onToggle: (TodoTask) -> Unit,
    onRetry: () -> Unit,
    onSeeAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        TodayHeader(onSeeAll)
        Spacer(Modifier.height(12.dp))

        when (state) {
            is TodayUiState.Loading -> {
                Box(Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is TodayUiState.Error -> {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(state.message)
                    TextButton(onClick = onRetry) { Text("Tekrar dene") }
                }
            }
            is TodayUiState.Success -> {
                if (state.tasks.isEmpty()) {
                    Text("Bugün için görev yok.", modifier = Modifier.padding(16.dp))
                } else {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        state.tasks.forEach { task ->
                            TodoItem(task = task, onToggle = { onToggle(task) })
                        }
                    }
                }
            }
        }
    }
}