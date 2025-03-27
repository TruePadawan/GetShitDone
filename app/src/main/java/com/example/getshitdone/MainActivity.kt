package com.example.getshitdone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.getshitdone.components.CreateTodoDialog
import com.example.getshitdone.components.TodoItem
import com.example.getshitdone.ui.GetShitDoneViewModel
import com.example.getshitdone.ui.theme.GetShitDoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GetShitDoneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GetShitDoneApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/*
* Composable that displays a list of todos and a button for triggering a dialog for creating todos
*/
@Composable
fun GetShitDoneApp(
    modifier: Modifier = Modifier,
    getShitDoneViewModel: GetShitDoneViewModel = viewModel()
) {
    val todoListUiState = getShitDoneViewModel.uiState.collectAsState()
    var showCreateTodoDialog by remember { mutableStateOf(false) }
    val todos = todoListUiState.value

    Column(modifier = modifier.padding(8.dp)) {
        FilledTonalButton(
            onClick = { showCreateTodoDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.create_todo),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
        if (todos.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No Todos",
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
            }
        } else {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                thickness = 2.dp,
                color = Color.Gray
            )
            /* This column will hold the todos */
            LazyColumn {
                items(todos.values.toList()) {
                    TodoItem(data = it)
                }
            }
        }
    }

    if (showCreateTodoDialog) {
        CreateTodoDialog(
            closeDialogHandler = { showCreateTodoDialog = false },
            createTodoHandler = { title: String, desc: String? ->
                getShitDoneViewModel.addTodo(title, desc)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GetShitDonePreview() {
    GetShitDoneTheme {
        GetShitDoneApp()
    }
}

@Preview
@Composable
fun GetShitDoneDarkThemePreview() {
    GetShitDoneTheme(darkTheme = true) {
        GetShitDoneApp()
    }
}