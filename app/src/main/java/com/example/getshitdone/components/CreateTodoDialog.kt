package com.example.getshitdone.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.getshitdone.R
import com.example.getshitdone.ui.theme.bodyFontFamily

@Composable
fun CreateTodoDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var todoTitle by remember { mutableStateOf("") }
    var todoDescription by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismissRequest,
        DialogProperties(usePlatformDefaultWidth = false, dismissOnClickOutside = false)
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                text = "Create a TODO",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontFamily = bodyFontFamily,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.todo_title)) },
                    value = todoTitle,
                    onValueChange = { todoTitle = it },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.todo_desc)) },
                    value = todoDescription,
                    onValueChange = { todoDescription = it },
                    singleLine = false,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismissRequest) {
                        Text(
                            text = stringResource(R.string.dismiss_form),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    TextButton(onClick = { onConfirmation(todoTitle, todoDescription) }) {
                        Text(
                            text = stringResource(R.string.confirm_create_todo_form),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}