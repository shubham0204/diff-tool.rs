package com.projects.pict.fitnesstracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.projects.pict.fitnesstracker.ui.theme.FitnessTrackerTheme

@Composable
fun HomeScreen() {
    FitnessTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(text = "Home")
            Column(
                horizontalAlignment= Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ){
                Button(onClick = {
                    //your onclick code here
                },colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    modifier = Modifier
                    .height(80.dp)
                    .width(200.dp)
                    .padding(vertical = 16.dp)// Adjust the height as needed
                    ) {
                    Text(text = "Download weights")
                }
                Button(onClick = {
                    //your onclick code here
                },colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    modifier = Modifier
                        .height(80.dp)
                        .width(200.dp)
                        .padding(vertical = 16.dp)) {
                    Text(text = "Upload weights")
                }
                Button(onClick = {
                    //your onclick code here
                },colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    modifier = Modifier
                        .height(80.dp)
                        .width(200.dp)
                        .padding(vertical = 16.dp)) {
                    Text(text = "Train model")
                }

            }

        }


    }
}