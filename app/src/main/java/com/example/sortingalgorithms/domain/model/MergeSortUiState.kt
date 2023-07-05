package com.example.sortingalgorithms.domain.model

import androidx.compose.ui.graphics.Color
import com.example.sortingalgorithms.presentation.state.BubbleSortUiState

data class MergeSortUiState(
    val id : String,
    val depth : Int,
    val sortUiState: MergeSortState,
    val sortParts : List<List<Int>>,
    val color: Color
)
