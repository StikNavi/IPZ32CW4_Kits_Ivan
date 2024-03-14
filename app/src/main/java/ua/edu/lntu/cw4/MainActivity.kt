package ua.edu.lntu.cw4

import android.annotation.SuppressLint
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import ua.edu.lntu.cw4.ui.theme.IPZ32CW4_Kits_IvanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ32CW4_Kits_IvanTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "taskList") {
                    composable("taskList") {
                        TaskListScreen(navController)
                    }
                    composable("taskDetails/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")
                        taskId?.let { TaskDetailsScreen(navController, it) }
                    }
                }
            }
        }
    }
}

data class Task(
    val id: String,
    val name: String,
    val description: String,
    val date: String,
    var status: TaskStatus
)

enum class TaskStatus {
    ACTIVE,
    COMPLETE
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(navController: NavHostController) {
    val tasks = remember { mutableStateListOf(
        Task("1", "Task 1", "Description 1", "2024-03-14", TaskStatus.ACTIVE),
        Task("2", "Task 2", "Description 2", "2024-03-15", TaskStatus.ACTIVE),
        Task("3", "Task 3", "Description 3", "2024-03-16", TaskStatus.COMPLETE),
        Task("4", "Task 4", "Description 4", "2024-03-17", TaskStatus.ACTIVE)
    ) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Список завдань") })
        }
    ) {
        TaskList(tasks) { taskId ->
            navController.navigate("taskDetails/$taskId")
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>, onItemClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        for (task in tasks) {
            TaskItem(task = task, onItemClick)
        }
    }
}

@Composable
fun TaskItem(task: Task, onItemClick: (String) -> Unit) {
    val backgroundColor = if (task.status == TaskStatus.ACTIVE) Color.Blue else Color.Red

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(backgroundColor)
            .fillMaxWidth()
            .clickable { onItemClick(task.id) }
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(navController: NavHostController, taskId: String) {
    val task = remember { mutableStateOf(Task("0", "", "", "", TaskStatus.ACTIVE)) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Деталі завдання") })
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Опис: ${task.value.description}")
                Text(text = "Дата: ${task.value.date}")
                Spacer(modifier = Modifier.height(16.dp))
                if (task.value.status == TaskStatus.ACTIVE) {
                    Button(onClick = {
                        task.value.status = TaskStatus.COMPLETE
                        navController.popBackStack()
                    }) {
                        Text("Done")
                    }
                }
            }
        }
    )

    LaunchedEffect(taskId) {
        // Simulating fetching task details from a repository
        task.value = Task(taskId, "Task $taskId", "Description $taskId", "2024-03-${14 + taskId.toInt()}", TaskStatus.ACTIVE)
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    IPZ32CW4_Kits_IvanTheme {
        TaskListScreen(rememberNavController())
    }
}
