package com.example.getshitdone

import com.example.getshitdone.data.TodoRepository
import org.junit.Test

import org.junit.Assert.*

class GetShitDoneUnitTests {
    @Test
    fun noTodosByDefault() {
        val todoRepo = TodoRepository()

        val todos = todoRepo.getAllTodos()
        assertTrue("There should be no todos by default", todos.isEmpty())
    }

    @Test
    fun canGetTodosFromTodoRepository() {
        val todoRepo = TodoRepository()

        val savedTodo = todoRepo.addTodo(title = "Test App")
        assertEquals(todoRepo.getTodoById(savedTodo.id), savedTodo)

        val todos = todoRepo.getAllTodos()
        assertTrue("Expected todolist of size 1, actual size - ${todos.size}", todos.size == 1)
    }
}