package com.example.getshitdone.data

import java.util.UUID

data class TodoItemUiState(
    val id: String = UUID.randomUUID().toString(),
    val title: String, val description: String? = "", val isComplete: Boolean = false
)

data class TextFieldUiState(
    var value: String = "",
    var hasError: Boolean = false,
    var hasBeenTouched: Boolean = false
)