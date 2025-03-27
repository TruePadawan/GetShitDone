package com.example.getshitdone.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.getshitdone.R
import com.example.getshitdone.data.TextFieldUiState
import com.example.getshitdone.data.TodoItemUiState
import com.example.getshitdone.ui.theme.bodyFontFamily

@Composable
fun EditTodoDialog(
    todo: TodoItemUiState,
    closeDialogHandler: () -> Unit,
    editTodoHandler: (title: String, description: String?, isComplete: Boolean?) -> Unit,
    modifier: Modifier = Modifier
) {
    var titleTextFieldState by remember { mutableStateOf(TextFieldUiState(value = todo.title)) }
    var todoDescription by remember { mutableStateOf(todo.desc ?: "") }
    var todoCompletionStatus by remember { mutableStateOf(todo.isComplete) }
    val allowSubmission = titleTextFieldState.value.trim().isNotEmpty()

    Dialog(onDismissRequest = closeDialogHandler) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                style = MaterialTheme.typography.headlineSmall,
                text = stringResource(R.string.edit_todo_dialog_headline),
                fontWeight = FontWeight.Bold,
                fontFamily = bodyFontFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.todo_title)) },
                    value = titleTextFieldState.value,
                    onValueChange = {
                        titleTextFieldState.hasBeenTouched = true
                        titleTextFieldState =
                            titleTextFieldState.copy(hasError = it.trim().isEmpty(), value = it)
                    },
                    isError = titleTextFieldState.hasBeenTouched && titleTextFieldState.hasError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.todo_desc)) },
                    value = todoDescription,
                    onValueChange = { todoDescription = it },
                    singleLine = false,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Switch(
                    checked = todoCompletionStatus,
                    onCheckedChange = { todoCompletionStatus = it })

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = {}) {
                        Text(
                            text = stringResource(R.string.edit_todo_dialog_delete_action),
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Row {
                        TextButton(onClick = closeDialogHandler) {
                            Text(
                                text = stringResource(R.string.dismiss_form),
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        TextButton(onClick = { }, enabled = allowSubmission) {
                            Text(
                                text = stringResource(R.string.create_todo_dialog_confirm_action),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}