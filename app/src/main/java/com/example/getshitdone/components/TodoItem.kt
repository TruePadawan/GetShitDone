package com.example.getshitdone.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.getshitdone.data.TodoItemUiState
import com.example.getshitdone.data.UpdateTodoPayload
import com.example.getshitdone.ui.theme.GetShitDoneTheme

@Composable
fun TodoItem(
    data: TodoItemUiState,
    editTodoHandler: (payload: UpdateTodoPayload) -> Unit,
    modifier: Modifier = Modifier
) {
    var showEditTodoDialog by remember { mutableStateOf(false) }
    Button(
        onClick = { showEditTodoDialog = true },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(2.dp)
    ) {
        Text(
            text = data.title,
            textAlign = TextAlign.Center,
            textDecoration = if (data.isComplete) TextDecoration.LineThrough else TextDecoration.None
        )
    }

    if (showEditTodoDialog) {
        EditTodoDialog(
            todo = data,
            closeDialogHandler = { showEditTodoDialog = false },
            editTodoHandler = editTodoHandler
        )
    }
}

@Preview
@Composable
fun TodoItemPreview() {
    GetShitDoneTheme(darkTheme = true) {
        val editTodoHandler = { payload: UpdateTodoPayload -> }
        TodoItem(
            data = TodoItemUiState(
                id = "1",
                title = "Create a TODO",
            ),
            editTodoHandler = editTodoHandler,
        )

    }
}