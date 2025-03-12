package com.example.getshitdone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.getshitdone.data.Todo
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
fun GetShitDoneApp(modifier: Modifier = Modifier) {
//    val createTodoDialogIsOpen by remember { mutableStateOf(false) }
    val todos = remember { mutableStateListOf<Todo>() }

    Column(modifier = modifier.padding(8.dp)) {
        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
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
            /* This column will hold the todos */
            Column(modifier = Modifier) {

            }
        }
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