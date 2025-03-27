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
    private var todoListMap = repository.getAllTodos().toMutableMap()
    private var _uiState = MutableStateFlow(todoListMap.values.toList())
    val uiState = _uiState.asStateFlow()

    fun addTodo(payload: AddTodoPayload) {
        val todoItemUiState = repository.addTodo(payload)
        todoListMap[todoItemUiState.id] = todoItemUiState
        _uiState.update {
            todoListMap.values.toList()
        }
    }

    fun updateTodo(id: String, payload: UpdateTodoPayload) {
        val updatedTodoItemUiState = repository.updateTodo(id, payload)
        todoListMap[updatedTodoItemUiState.id] = updatedTodoItemUiState
        _uiState.update {
            todoListMap.values.toList()
        }
    }
}