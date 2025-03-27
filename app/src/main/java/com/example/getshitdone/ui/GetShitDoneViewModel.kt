package com.example.getshitdone.ui

import androidx.lifecycle.ViewModel
import com.example.getshitdone.data.AddTodoPayload
import com.example.getshitdone.data.TodoItemUiState
import com.example.getshitdone.data.TodoRepository
import com.example.getshitdone.data.UpdateTodoPayload
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GetShitDoneViewModel : ViewModel() {
    private val repository = TodoRepository()
    private var _uiState = MutableStateFlow(repository.getAllTodos())
    val uiState = _uiState.asStateFlow()

    fun fetchTodos(isComplete: Boolean? = null): Map<String, TodoItemUiState> {
        return repository.getAllTodos(isComplete)
    }

    fun addTodo(payload: AddTodoPayload): TodoItemUiState {
        return repository.addTodo(payload)
    }

    fun updateTodo(id: String, payload: UpdateTodoPayload): TodoItemUiState {
        return repository.updateTodo(id, payload)
    }
}