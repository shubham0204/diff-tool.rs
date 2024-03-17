package com.projects.pict.fitnesstracker.ui.screens

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.pict.fitnesstracker.ui.theme.DarkTurq
import com.projects.pict.fitnesstracker.ui.theme.ExtraLightTurq
import com.projects.pict.fitnesstracker.ui.theme.FitnessTrackerTheme
import com.projects.pict.fitnesstracker.viewmodels.UsersViewModel


@Composable
fun HomeScreen() {
    FitnessTrackerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val usersViewModel: UsersViewModel =
                viewModel(LocalContext.current as ComponentActivity)
            val googleFitData by usersViewModel.currentGoogleFitData.observeAsState()
            Log.e("APP", "Triggerd ............................. $googleFitData")
            val user by usersViewModel.currentUser.observeAsState()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                CircularProgress(googleFitData?.stepData?.last()?.second ?: 0, 8000)
                Spacer(modifier = Modifier.height(40.dp))
                val paddingModifier = Modifier.padding(20.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Card(
                        shape = RoundedCornerShape(20.dp), modifier = Modifier
                            .weight(1f)
                    ) {
                        Column(modifier = paddingModifier) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Calories",
                                    textAlign = TextAlign.Left
                                )
                                Spacer(modifier = Modifier.width(2.dp)) // Adjust the spacing between text and icon
                                Icon(
                                    imageVector = Icons.Default.LocalFireDepartment,
                                    contentDescription = null // You can provide a description if needed
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "${googleFitData?.caloriesData?.last()?.second?.toInt() ?: 0} kcal",
                                textAlign = TextAlign.Left,
                                fontSize = 20.sp
                            )

                        }


                    }

                    Spacer(modifier = Modifier.width(10.dp))
                    Card(
                        shape = RoundedCornerShape(20.dp), modifier = Modifier
                            .weight(1f)
                    ) {

                        Column(modifier = paddingModifier) {

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Move Minutes",
                                    textAlign = TextAlign.Left
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(
                                    imageVector = Icons.Default.Timer,
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "${googleFitData?.moveMinutesData?.last()?.second ?: 0} mins",
                                textAlign = TextAlign.Left,
                                fontSize = 20.sp
                            )

                        }

                    }
                }

                Spacer(modifier = Modifier.height(35.dp))
                StepGoalCard(
                    user?.stepCount ?: 0 ,
                    googleFitData?.stepData?.map { it.second }?.toList() ?: emptyList()
                )
            }

        }


    }
}

@Composable
fun CircularProgress(
    stepsCompleted: Int,
    totalSteps: Int
) {
    Box(
        modifier = Modifier.size(250.dp) // Adjust size of the box
    ) {
        CircularProgressIndicator(
            progress = { stepsCompleted.toFloat() / totalSteps.toFloat() },
            modifier = Modifier
                .size(250.dp) // Adjust size of the CircularProgressIndicator
                .align(Alignment.Center),
            color = DarkTurq,
            strokeWidth = 25.dp,
            strokeCap = StrokeCap.Round,
        )
        Text(
            text = "${stepsCompleted}/${totalSteps}",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center)
        )

    }
}


@Composable
fun StepGoalCard(
    numStepsGoal: Int,
    stepsData: List<Int>
) {
    var targetAchieved: Array<Boolean?> = stepsData.map { it >= numStepsGoal }.toTypedArray()
    if( targetAchieved.size < 7 ) {
        targetAchieved += List( 7 - targetAchieved.size ) { null }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            targetAchieved.forEachIndexed { index, achieved ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .background(color = ExtraLightTurq, shape = CircleShape)
                        .size(24.dp)
                ) {
                    if ( achieved != null ) {
                        Icon(
                            modifier = Modifier.align(Alignment.Center),
                            imageVector = Icons.Default.Check,
                            tint = if( achieved ) Color.Black else Color.White,
                            contentDescription = "Step goal achieved"
                        )
                    }
                }
            }
        }
    }
}