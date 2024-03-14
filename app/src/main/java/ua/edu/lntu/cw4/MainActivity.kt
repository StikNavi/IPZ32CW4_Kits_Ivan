package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.lntu.cw4.ui.theme.IPZ32CW4_Kits_IvanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ32CW4_Kits_IvanTheme {
                // Initialize a list of tasks
                val tasks = remember { mutableStateListOf(
                    Task("Task 1", "Description 1", "2024-03-14", TaskStatus.ACTIVE),
                    Task("Task 2", "Description 2", "2024-03-15", TaskStatus.ACTIVE),
                    Task("Task 3", "Description 3", "2024-03-16", TaskStatus.COMPLETE),
                    Task("Task 4", "Description 4", "2024-03-17", TaskStatus.ACTIVE)
                ) }

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TaskList(tasks)
                }
            }
        }
    }
}

data class Task(
    val name: String,
    val description: String,
    val date: String,
    var status: TaskStatus
)

enum class TaskStatus {
    ACTIVE,
    COMPLETE
}

@Composable
fun TaskList(tasks: List<Task>) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Список завдань")

        for (task in tasks) {
            TaskItem(task = task)
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    val backgroundColor = if (task.status == TaskStatus.ACTIVE) Color.Blue else Color.Red

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor)
            .fillMaxWidth()
            .clickable { /* Navigate to task details */ }
            .padding(16.dp)
    ) {
        Text(
            text = task.name,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = task.status.toString(),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    IPZ32CW4_Kits_IvanTheme {
        TaskList(
            listOf(
                Task("Task 1", "Description 1", "2024-03-14", TaskStatus.ACTIVE),
                Task("Task 2", "Description 2", "2024-03-15", TaskStatus.ACTIVE),
                Task("Task 3", "Description 3", "2024-03-16", TaskStatus.COMPLETE),
                Task("Task 4", "Description 4", "2024-03-17", TaskStatus.ACTIVE)
            )
        )
    }
}
