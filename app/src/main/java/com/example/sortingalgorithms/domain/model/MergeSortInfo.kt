package com.example.sortingalgorithms.domain.model

import androidx.compose.ui.layout.IntrinsicMeasurable

data class MergeSortInfo(
    val id : String,
    val depth : Int,
    val sortPart : List<Int>,
    val sortState : MergeSortState
)

enum class MergeSortState(value : Int){
    DIVIDED(0),MERGED(1)
}