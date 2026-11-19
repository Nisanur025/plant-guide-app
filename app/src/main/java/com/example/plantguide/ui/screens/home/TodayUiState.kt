package com.example.plantguide.ui.screens.home

import com.example.plantguide.ui.screens.home.data.TodoTask

sealed interface TodayUiState {
    data object Loading : TodayUiState
    data class Success(val tasks: List<TodoTask>) : TodayUiState
    data class Error(val message: String) : TodayUiState
}