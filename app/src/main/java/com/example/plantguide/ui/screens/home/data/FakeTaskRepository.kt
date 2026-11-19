package com.example.plantguide.ui.screens.home.data

import kotlinx.coroutines.delay

class FakeTaskRepository : TaskRepository {
    private val tasks = mutableListOf(
        TodoTask(1, "Gülleri kontrol et", "Yapraklarında sararma var", TodoType.ACIL),
        TodoTask(2, "Limon ağacını sula", "Toprak nemi düşük", TodoType.HATIRLATMA),
        TodoTask(3, "Zeytin ağacını buda", "Budama zamanı yaklaşıyor", TodoType.YAKLASAN),
        TodoTask(4, "Limon ağacını sula", "Toprak nemi düşük", TodoType.SULAMA),
        TodoTask(5, "Limon ağacını sula", "Toprak nemi düşük", TodoType.GUBRE),
        TodoTask(6, "Limon ağacını sula", "Toprak nemi düşük", TodoType.BUDAMA)

    )

    override suspend fun getTodayTasks(): List<TodoTask> {
        delay(800) // internet gecikmesini taklit et
        return tasks.toList()
    }

    override suspend fun setDone(id: Int, done: Boolean) {
        val i = tasks.indexOfFirst { it.id == id }
        if (i >= 0) tasks[i] = tasks[i].copy(isDone = done)
    }
}