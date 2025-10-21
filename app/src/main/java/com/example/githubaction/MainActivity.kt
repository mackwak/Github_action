package com.example.githubaction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubaction.ui.theme.GithubActionTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubActionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel() // 2. Get ViewModel from Hilt
) {
    var result by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the result
        Text(text = "Result: ${result ?: "..."}", modifier = Modifier.padding(bottom = 16.dp))

        // Button to perform addition
        Button(onClick = {
            result = viewModel.performAddition(10, 5) // 3. Use the ViewModel
        }) {
            Text("Calculate 10 + 5")
        }

        // Button to perform subtraction
        Button(onClick = {
            result = viewModel.performSubtraction(10, 5) // 3. Use the ViewModel
        }) {
            Text("Calculate 10 - 5")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubActionTheme {
        CalculatorScreen()
    }
}