package com.example.getshitdone.data


data class AddTodoPayload(
    val title: String,
    val desc: String?
)

data class UpdateTodoPayload(
    val title: String,
    val desc: String? = null,
    val isComplete: Boolean
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
     * @param payload An object containing the TodoItem title and description
     *
     * @return the newly created TodoItem
     * */
    fun addTodo(payload: AddTodoPayload): TodoItemUiState {
        val todo = TodoItemUiState(title = payload.title, desc = payload.desc)
        todos[todo.id] = todo
        return todo
    }

    /**
     * Update a TodoItem
     *
     * @param id The ID of the TodoItem
     * @param payload The new data for the TodoItem
     *
     * @throws Exception if a TodoItem with the specified ID isn't found
     *
     * @return the updated TodoItem
     * */
    fun updateTodo(id: String, payload: UpdateTodoPayload): TodoItemUiState {
        if (!todos.contains(id)) {
            throw Exception("Couldn't find todo item with specified id")
        } else {
            todos[id] = todos[id]?.copy(
                title = payload.title,
                desc = payload.desc,
                isComplete = payload.isComplete
            )!!
            return todos[id]!!
        }

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
    fun addTodo(payload: AddTodoPayload): TodoItemUiState {
        return localTodosDataSource.addTodo(payload)
    }

    /**
     * Update a TodoItem
     *
     * @param id The ID of the TodoItem
     * @param payload The new data for the TodoItem
     *
     * @throws Exception if a TodoItem with the specified ID isn't found
     * */
    fun updateTodo(id: String, payload: UpdateTodoPayload): TodoItemUiState {
        return localTodosDataSource.updateTodo(id, payload)
    }
}