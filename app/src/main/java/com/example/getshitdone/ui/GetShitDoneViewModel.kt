package com.example.getshitdone.ui

import androidx.lifecycle.ViewModel
import com.example.getshitdone.data.AddTodoPayload
import com.example.getshitdone.data.TodoRepository
import com.example.getshitdone.data.UpdateTodoPayload
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GetShitDoneViewModel : ViewModel() {
    private val repository = TodoRepository()
    private var _uiState = MutableStateFlow(repository.getAllTodos().values.toList())
    val uiState = _uiState.asStateFlow()

    private fun refreshTodos() {
        _uiState.update {
            repository.getAllTodos().values.toList()
        }
    }

    fun addTodo(payload: AddTodoPayload) {
        repository.addTodo(payload)
        refreshTodos()
    }

    fun updateTodo(id: String, payload: UpdateTodoPayload) {
        repository.updateTodo(id, payload)
        refreshTodos()
    }

    fun deleteTodo(id: String) {
        repository.deleteTodo(id)
        refreshTodos()
    }
}