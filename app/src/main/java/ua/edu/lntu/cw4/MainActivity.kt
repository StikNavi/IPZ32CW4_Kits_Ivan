package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.lntu.cw4.ui.theme.IPZ32CW4_Kits_IvanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ32CW4_Kits_IvanTheme {
                TaskListScreen()
            }
        }
    }
}

@Composable
fun TaskListScreen() {
    val tasks = remember { mutableStateListOf("Task 1", "Task 2", "Task 3") }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Список завдань")

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(tasks) { task ->
                    TaskListItem(task = task)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun TaskListItem(task: String) {
    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.surface) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = task)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    IPZ32CW4_Kits_IvanTheme {
        TaskListScreen()
    }
}
