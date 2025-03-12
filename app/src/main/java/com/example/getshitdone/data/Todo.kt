package com.example.getshitdone.data

import java.util.UUID

data class Todo(
    val id: String = UUID.randomUUID().toString(),
    var title: String, var desc: String? = "", var isComplete: Boolean = false
)

class LocalTodosDataSource {
    private val todos = mutableMapOf<String, Todo>()

    fun fetchTodos() = todos

    fun addTodo(todo: Todo) {
        todos[todo.id] = todo
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
}