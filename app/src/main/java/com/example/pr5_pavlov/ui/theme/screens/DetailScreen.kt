package com.example.pr5_pavlov.ui.theme.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pr5_pavlov.data.SpecialtyRepository
import com.example.pr5_pavlov.ui.theme.Pr5_PavlovTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(specialtyId: Int, onBack: () -> Unit) {
    val specialty = SpecialtyRepository.getSpecialtyById(specialtyId)
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(specialty?.name ?: "Не найдено") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        if (specialty != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    "Код: ${specialty.code}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Срок обучения: ${specialty.duration}")
                Text("Квалификация: ${specialty.qualification}")
                Spacer(Modifier.height(8.dp))
                Text("Описание:", style = MaterialTheme.typography.titleMedium)
                Text(specialty.description)
                Spacer(Modifier.height(24.dp))
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${specialty.phone}")
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Phone, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Позвонить в приёмную комиссию")
                }
            }
        } else {
            Text(
                "Специальность не найдена",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "Детали")
@Composable
fun DetailScreenPreview() {
    Pr5_PavlovTheme {
        DetailScreen(specialtyId = 1, onBack = {})
    }
}