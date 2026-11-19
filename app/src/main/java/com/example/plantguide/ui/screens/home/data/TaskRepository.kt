package com.example.plantguide.ui.screens.home.data

interface TaskRepository {
    suspend fun  getTodayTasks(): List<TodoTask>
    suspend fun setDone(id: Int, done: Boolean)
}