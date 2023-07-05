package com.example.sortingalgorithms.presentation.state

import com.example.sortingalgorithms.Algorithms

data class BubbleSortUiState(
    val isSortingCompleted : Boolean,
    val algorithm: Algorithms = Algorithms.BUBBLE_SORT
)
