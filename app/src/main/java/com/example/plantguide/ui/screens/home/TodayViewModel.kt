package com.example.plantguide.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantguide.ui.screens.home.data.FakeTaskRepository
import com.example.plantguide.ui.screens.home.data.TaskRepository
import com.example.plantguide.ui.screens.home.data.TodoTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodayViewModel(
    private val repo: TaskRepository = FakeTaskRepository()
) : ViewModel(){
    private val _uiState = MutableStateFlow<TodayUiState>(TodayUiState.Loading)
    val uiState : StateFlow<TodayUiState> = _uiState.asStateFlow()
    init { load() }

    fun load() {
        viewModelScope.launch {
            _uiState.value = TodayUiState.Loading
            try {
                _uiState.value = TodayUiState.Success(repo.getTodayTasks())
            } catch (e: Exception) {
                _uiState.value = TodayUiState.Error(e.message ?: "Bir hata oluştu")
            }
        }
    }

    fun toggle(task: TodoTask) {
        val current = _uiState.value as? TodayUiState.Success ?: return
        val newDone = !task.isDone

        // 1) Önce ekranı hemen güncelle (kullanıcı beklemesin)
        _uiState.value = TodayUiState.Success(
            current.tasks.map { if (it.id == task.id) it.copy(isDone = newDone) else it }
        )

        // 2) Sonra sunucuya kaydet, olmazsa geri al
        viewModelScope.launch {
            try {
                repo.setDone(task.id, newDone)
            } catch (e: Exception) {
                _uiState.value = current
            }
        }
    }
}