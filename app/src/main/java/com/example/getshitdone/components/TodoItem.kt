package com.example.getshitdone.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.getshitdone.data.TodoItemUiState
import com.example.getshitdone.ui.theme.GetShitDoneTheme

@Composable
fun TodoItem(data: TodoItemUiState, modifier: Modifier = Modifier) {
    Button(onClick = {}, modifier = modifier.fillMaxWidth(), shape = RoundedCornerShape(2.dp)) {
        Text(
            text = data.title,
            textAlign = TextAlign.Center,
            textDecoration = if (data.isComplete) TextDecoration.Underline else TextDecoration.None
        )
    }
}

@Preview
@Composable
fun TodoItemPreview() {
    GetShitDoneTheme(darkTheme = true) {
        TodoItem(data = TodoItemUiState(id = "1", title = "Create a TODO"))

    }
}