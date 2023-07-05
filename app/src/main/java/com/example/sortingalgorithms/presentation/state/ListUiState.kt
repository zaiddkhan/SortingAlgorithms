package com.example.sortingalgorithms.presentation.state

import androidx.compose.ui.graphics.Color

data class ListUiState(
    val id:Int,
    val isCurrentlyCompared : Boolean,
    val value : Int,
    val color: Color
)
