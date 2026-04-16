package com.example.unscramble.ui

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun AddWordScreen(viewModel: GameViewModel) {

    var inputWord by remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("WORDS", Context.MODE_PRIVATE)

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Tambah Kata Baru")

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = inputWord,
            onValueChange = { inputWord = it },
            label = { Text("Masukkan kata") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            // 🔥 masuk ke ViewModel
            viewModel.addWord(inputWord)

            // 🔥 simpan persistent
            val savedWords = sharedPref
                .getStringSet("DATA", mutableSetOf())!!
                .toMutableSet()

            savedWords.add(inputWord)

            sharedPref.edit()
                .putStringSet("DATA", savedWords)
                .apply()

            inputWord = ""

        }) {
            Text("Simpan")
        }
    }
}