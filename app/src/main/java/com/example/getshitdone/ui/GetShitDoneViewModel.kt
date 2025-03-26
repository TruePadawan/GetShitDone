package com.example.getshitdone.ui

import androidx.lifecycle.ViewModel
import com.example.getshitdone.data.TodoItemUiState
import com.example.getshitdone.data.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TodoListUiState(val todos: List<TodoItemUiState> = listOf())

class GetShitDoneViewModel : ViewModel() {
    private val repository = TodoRepository()
    private var _uiState = MutableStateFlow(TodoListUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchTodos(isComplete: Boolean? = null): Map<String, TodoItemUiState> {
        return repository.getAllTodos(isComplete)
    }

    fun addTodo(title: String, description: String?): TodoItemUiState {
        return repository.addTodo(title, description)
    }
}