package ua.edu.lntu.cw4

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.lntu.cw4.ui.theme.IPZ32CW4_Kits_IvanTheme

@Composable
fun TaskDetailScreen(task: String) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Деталі завдання")

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = task)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskDetailPreview() {
    IPZ32CW4_Kits_IvanTheme {
        TaskDetailScreen(task = "Task 1")
    }
}
