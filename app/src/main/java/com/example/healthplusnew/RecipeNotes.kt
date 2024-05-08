package com.example.healthplusnew

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
@Composable
fun RecipeNotes(subjectViewModel: SubjectViewModel) {
    val subjects by subjectViewModel.allSubjects.observeAsState(emptyList())
    val selectedSubject = remember { mutableStateOf<Subject?>(null) }
    val insertDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn {
            itemsIndexed(subjects) { index, subject ->
                SubjectItem(
                    subject = subject,
                    onEdit = { selectedSubject.value = subject },
                    onDelete = { subjectViewModel.deleteSubject(subject) }
                )
                Divider(color = Color.Gray, thickness = 5.dp)
            }
        }
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Button(onClick = { insertDialog.value = true }) {
                Text("Add Recipe")
            }
        }
    }
    if (insertDialog.value) {
        InsertSubjectDialog(
            onDismiss = { insertDialog.value = false },
            onSave = { subjectName,subjectContent ->
                subjectViewModel.insertSubject(Subject(name = subjectName, content = subjectContent))
            }
        )
    }
}
@Composable
fun InsertSubjectDialog(
    onDismiss: () -> Unit,
    onSave: (String,String) -> Unit
) {
    var subjectName by remember { mutableStateOf("") }
    var subjectContent by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Recipe") },
        confirmButton = {
            Button(
                onClick = {
                    onSave(subjectName,subjectContent)
                    onDismiss()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        text = {
            Column {
                TextField(
                    value = subjectName,
                    onValueChange = { subjectName = it },
                    label = { Text("Recipe Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = subjectContent,
                    onValueChange = { subjectContent = it },
                    label = { Text("Recipe Content") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}
