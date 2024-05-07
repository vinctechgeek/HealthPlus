package com.example.healthplus

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Locale
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecipeNotesPage(viewModel: RecipeNotesViewModel){
    val recipenotesList by viewModel.recipenotesList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }
    var inputText2 by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter Recipe Name") }
            )
            OutlinedTextField(
                value = inputText2,
                onValueChange = { inputText2 = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Enter Recipe Content") }
            )
            Button(
                onClick = {
                    viewModel.addRecipeNotes(inputText, inputText2)
                    inputText = ""
                    inputText2 = ""
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp)
            ) {
                Text(text = "Add")
            }
        }
        recipenotesList?.let {
            LazyColumn(content = {
                itemsIndexed(it) { index: Int, item: RecipeNotes ->
                    RecipeNoteItem(item = item, onDelete = { viewModel.deleteRecipeNotes(item.id) })
                }
            })
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No Recipes Yet"
        )
    }
//    Text(text = recipeNotesList.toString())
}

@Composable
fun RecipeNoteItem( item : RecipeNotes, onDelete : ()-> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(text = SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.content,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) { // Toggle the expanded state
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_24),
                 contentDescription = "Delete",
                tint = Color.White)
            
        }
    }
//    Text(text = item.toString())
}