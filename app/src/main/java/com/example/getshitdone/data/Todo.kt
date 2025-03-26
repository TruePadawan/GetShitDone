package com.example.getshitdone.data

import java.util.UUID
import kotlin.collections.contains

data class TodoItemUiState(
    val id: String = UUID.randomUUID().toString(),
    var title: String, var desc: String? = "", var isComplete: Boolean = false
)

data class UpdateTodoPayload(
    var title: String? = null,
    var desc: String? = null,
    var isComplete: Boolean? = null
)

class LocalTodosDataSource {
    private val todos = mutableMapOf<String, TodoItemUiState>()

    /**
     * Get all TodoItems
     * */
    fun fetchTodos() = todos

    /**
     * Add a TodoItem
     *
     * @param todo A Todo object which holds the TodoItem data
     *
     * @return the newly created TodoItem
     * */
    fun addTodo(todo: TodoItemUiState) {
        todos[todo.id] = todo
    }

    /**
     * Update a TodoItem
     *
     * @param id The ID of the TodoItem
     * @param payload The new data for the TodoItem
     *
     * @throws Exception if a TodoItem with the specified ID isn't found
     * */
    fun updateTodo(id: String, payload: UpdateTodoPayload) {
        if (!todos.contains(id)) {
            throw Exception("Couldn't find todo item with specified id")
        }

        val currentTodoData = todos[id]!!
        todos[id]!!.title = payload.title ?: currentTodoData.title
        todos[id]!!.desc = payload.desc ?: currentTodoData.desc
        todos[id]!!.isComplete = payload.isComplete ?: currentTodoData.isComplete
    }
}

class TodoRepository() {
    private val localTodosDataSource = LocalTodosDataSource()

    /**
     * Get all TodoItems and optionally filter by completion status
     *
     * @param isComplete The completion status
     * */
    fun getAllTodos(isComplete: Boolean? = null): Map<String, TodoItemUiState> {
        val todos = localTodosDataSource.fetchTodos()
        return if (isComplete != null) {
            todos.filter { it.value.isComplete == isComplete }
        } else {
            todos
        }
    }

    /**
     * Get a TodoItem by its ID
     *
     * @param id The ID of the TodoItem
     * */
    fun getTodoById(id: String): TodoItemUiState? {
        val todos = getAllTodos()
        return todos[id]
    }

    /**
     * Add a TodoItem
     *
     * @param title The title of the TodoItem
     * @param description The optional description of the TodoItem
     *
     * @return the newly created TodoItem
     * */
    fun addTodo(title: String, description: String? = null): TodoItemUiState {
        val todo = TodoItemUiState(title = title, desc = description)
        localTodosDataSource.addTodo(todo)
        return todo
    }

    /**
     * Update a TodoItem
     *
     * @param id The ID of the TodoItem
     * @param payload The new data for the TodoItem
     *
     * @throws Exception if a TodoItem with the specified ID isn't found
     * */
    fun updateTodo(id: String, payload: UpdateTodoPayload) {
        localTodosDataSource.updateTodo(id, payload)
    }
}