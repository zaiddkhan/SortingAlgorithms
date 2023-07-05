package com.example.sortingalgorithms.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sortingalgorithms.domain.MergeSortAlgo
import com.example.sortingalgorithms.domain.model.MergeSortInfo
import com.example.sortingalgorithms.domain.model.MergeSortUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.UUID

class MergeSortViewModel(
    private val mergeSortAlgo: MergeSortAlgo = MergeSortAlgo()
) : ViewModel(){

    var listToSort = mutableListOf<Int>()
    init {
        for(i in 1..7){
            listToSort.add((10..99).random())
        }
    }

    var sortInfoUiItemList = mutableStateListOf<MergeSortUiState>()

    fun startMergeSorting(){
        sortInfoUiItemList.clear()
        subscribeToChanges()
        viewModelScope.launch {
            mergeSortAlgo(listToSort,0)

        }

    }

    private var job:Job? = null
    private fun subscribeToChanges(){
        job?.cancel()
        job = viewModelScope.launch {
            mergeSortAlgo.sortFlow.collect{ sortInfo ->
                val depthAlreadyExistsListIndex = sortInfoUiItemList.indexOfFirst {
                    it.depth == sortInfo.depth && it.sortUiState == sortInfo.sortState
                }
                if(depthAlreadyExistsListIndex == -1){
                    sortInfoUiItemList.add(
                        MergeSortUiState(
                            UUID.randomUUID().toString(),
                            depth = sortInfo.depth,
                            sortUiState = sortInfo.sortState,
                            sortParts = listOf(sortInfo.sortPart),
                            color = Color(
                                (0..255).random(),
                                (0..255).random(),
                                (0..255).random(),
                                255
                            )
                        )
                    )
                }else{
                    val currentPartList = sortInfoUiItemList[depthAlreadyExistsListIndex].sortParts.toMutableList()
                    currentPartList.add(sortInfo.sortPart)
                    sortInfoUiItemList.set(
                        depthAlreadyExistsListIndex,
                        sortInfoUiItemList[depthAlreadyExistsListIndex].copy(
                            sortParts = currentPartList
                        )
                    )
                }
                sortInfoUiItemList.sortedWith(
                    compareBy(
                        {
                            it.sortUiState
                        },
                        {
                            it.depth
                        }
                    )
                )
            }
        }
    }
}