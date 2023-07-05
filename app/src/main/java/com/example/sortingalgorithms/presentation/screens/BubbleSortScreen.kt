package com.example.sortingalgorithms.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sortingalgorithms.Algorithms
import com.example.sortingalgorithms.presentation.MainViewModel
import com.example.sortingalgorithms.presentation.composables.TypewriterText
import com.example.sortingalgorithms.presentation.state.BubbleSortUiState
import com.example.sortingalgorithms.presentation.state.ListUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BubbleSortScreen(
    typeWritingCompleted : () -> Unit,
    listToSort : MutableList<ListUiState>,
    modifier: Modifier = Modifier,
    uiState: BubbleSortUiState,
) {
    val listOffsetValue by animateDpAsState(targetValue = if(uiState.isSortingCompleted) (-90).dp else 0.dp,
        label = "",
        animationSpec = tween(2000, easing = LinearOutSlowInEasing)

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ){
            LazyColumn(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(listOffsetValue, 0.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                items(
                    listToSort,
                    key = {
                        it.id
                    }
                ) {
                    val border = if (it.isCurrentlyCompared) {
                        BorderStroke(2.dp, Color.White)
                    } else {
                        BorderStroke(0.dp, Color.Transparent)
                    }

                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(it.color, RoundedCornerShape(15.dp))
                            .border(border, RoundedCornerShape(15.dp))
                            .animateItemPlacement(
                                tween(300)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${it.value}", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    }
                }
            }
              
           androidx.compose.animation.AnimatedVisibility(
               modifier = Modifier.align(Alignment.TopEnd),
               visible = uiState.isSortingCompleted
           ) {

               TypewriterText(
                   modifier = Modifier.fillMaxWidth(0.65f),
                   text = "Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in the wrong order. This algorithm is not suitable for large data sets as its average and worst-case time complexity is quite high.",
                   typeWritingCompleted = {
                       typeWritingCompleted()
                   }
               )

           }
        }
      
    }
}
