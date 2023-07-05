package com.example.sortingalgorithms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sortingalgorithms.presentation.MainViewModel
import com.example.sortingalgorithms.presentation.MergeSortViewModel
import com.example.sortingalgorithms.presentation.screens.BubbleSortScreen
import com.example.sortingalgorithms.presentation.screens.MergeSortScreen
import com.example.sortingalgorithms.ui.theme.SortingAlgorithmsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SortingAlgorithmsTheme {

                val mainViewModel = MainViewModel()
                val mergeSortViewModel = MergeSortViewModel()
                val uiState by mainViewModel.bubbleSortUiState.collectAsState()



                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        var expandedState by remember {
                            mutableStateOf(false)
                        }
                        AnimatedVisibility(visible = !uiState.isSortingCompleted) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(25.dp)
                            ) {
                                Box {
                                    Button(onClick = {
                                        expandedState = true
                                    }) {
                                        Text(text = uiState.algorithm.name)
                                        Icon(
                                            imageVector = Icons.Default.KeyboardArrowDown,
                                            contentDescription = ""
                                        )
                                    }
                                    DropdownMenu(
                                        expanded = expandedState,
                                        onDismissRequest = {
                                            expandedState = !expandedState
                                        }
                                    )
                                    {
                                        DropdownMenuItem(text = {
                                            Text(text = "Merge Sort")
                                        }, onClick = {
                                            mainViewModel.algorithmChange(Algorithms.MERGE_SORT)
                                            expandedState = !expandedState

                                        })
                                        DropdownMenuItem(text = {
                                            Text(text = "Bubble Sort")
                                        }, onClick = {
                                            mainViewModel.algorithmChange(Algorithms.BUBBLE_SORT)
                                            expandedState = !expandedState

                                        })

                                    }
                                }
                                Button(onClick = {
                                   if(uiState.algorithm == Algorithms.MERGE_SORT) mergeSortViewModel.startMergeSorting() else mainViewModel.startSorting()
                                }) {
                                    Text(text = "Start sorting")
                                }
                            }
                        }
                        if(uiState.algorithm == Algorithms.MERGE_SORT){
                            Column(
                                horizontalAlignment = CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    "${mergeSortViewModel.listToSort}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                MergeSortScreen(
                                    startSorting = mergeSortViewModel::startMergeSorting,
                                    sortInfoUiItemList = mergeSortViewModel.sortInfoUiItemList,
                                    listToSort = mergeSortViewModel.listToSort
                                )
                            }

                        }else{
                            BubbleSortScreen(
                                typeWritingCompleted =  mainViewModel::typeWritingCompleted ,
                                listToSort = mainViewModel.listToSort,
                                uiState = uiState
                            )
                        }

                    }
                }
            }
        }
    }
}


enum class Algorithms{
    BUBBLE_SORT,MERGE_SORT
}