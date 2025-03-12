package com.example.getshitdone.data

import java.util.UUID
import kotlin.collections.contains

data class Todo(
    val id: String = UUID.randomUUID().toString(),
    var title: String, var desc: String? = "", var isComplete: Boolean = false
)

data class UpdateTodoPayload(
    var title: String? = null,
    var desc: String? = null,
    var isComplete: Boolean? = null
)

class LocalTodosDataSource {
    private val todos = mutableMapOf<String, Todo>()

    fun fetchTodos() = todos

    fun addTodo(todo: Todo) {
        todos[todo.id] = todo
    }

    /**
     * Update a todo-item
     *
     * @param id The ID of the todo-item
     * @param payload The new data for the todo-item
     *
     * @throws Exception if a todo-item with the specified ID isn't found
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

    fun getAllTodos(): Map<String, Todo> {
        return localTodosDataSource.fetchTodos()
    }

    fun getTodoById(id: String): Todo? {
        val todos = getAllTodos()
        return todos[id]
    }

    fun getTodosByCompletionStatus(isComplete: Boolean): Map<String, Todo> {
        val todos = getAllTodos()
        return todos.filter { it.value.isComplete == isComplete }
    }

    fun addTodo(title: String, description: String? = null): Todo {
        val todo = Todo(title = title, desc = description)
        localTodosDataSource.addTodo(todo)
        return todo
    }

    /**
     * Update a todo-item
     *
     * @param id The ID of the todo-item
     * @param payload The new data for the todo-item
     *
     * @throws Exception if a todo-item with the specified ID isn't found
     * */
    fun updateTodo(id: String, payload: UpdateTodoPayload) {
        localTodosDataSource.updateTodo(id, payload)
    }
}