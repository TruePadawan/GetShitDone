package com.example.getshitdone.ui

import androidx.lifecycle.ViewModel
import com.example.getshitdone.data.AddTodoPayload
import com.example.getshitdone.data.TodoItemUiState
import com.example.getshitdone.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GetShitDoneViewModel : ViewModel() {
    private val repository = TodoRepository()
    private var _uiState = MutableStateFlow(repository.getAllTodos())
    val uiState = _uiState.asStateFlow()

    fun fetchTodos(isComplete: Boolean? = null): Map<String, TodoItemUiState> {
        return repository.getAllTodos(isComplete)
    }

    fun addTodo(title: String, description: String?): TodoItemUiState {
        val payload = AddTodoPayload(title, description)
        return repository.addTodo(payload)
    }
}