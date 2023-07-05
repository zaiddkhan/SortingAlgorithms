package com.example.sortingalgorithms.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sortingalgorithms.Algorithms
import com.example.sortingalgorithms.domain.BubbleSortAlgo
import com.example.sortingalgorithms.presentation.state.BubbleSortUiState
import com.example.sortingalgorithms.presentation.state.ListUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
  val bubbleSortAlgo: BubbleSortAlgo = BubbleSortAlgo()
): ViewModel() {

    private val _bubbleSortUiState = MutableStateFlow(BubbleSortUiState(false))
    val bubbleSortUiState = _bubbleSortUiState.asStateFlow()


    var listToSort = mutableStateListOf<ListUiState>()

    init {
        for(i in 0..6){
            listToSort.add(
                ListUiState(
                   id = i,
                    false,
                    value = (0..150).random(),
                    color = Color(
                        (0..143).random(),
                        (0..155).random(),
                        (0..200).random()
                    )
                )
            )
        }
    }
    fun startSorting() {
        viewModelScope.launch {
            bubbleSortAlgo.invoke(list = listToSort.map { listToSort ->
               listToSort.value
            }.toMutableList(),
                sortingCompleted = {
                    _bubbleSortUiState.update {
                        BubbleSortUiState(
                            isSortingCompleted = true
                        )
                    }
            }).collect{sortInfo ->

                val currentItemIndex = sortInfo.currentItem

                listToSort[currentItemIndex] = listToSort[currentItemIndex].copy(isCurrentlyCompared = true)

                listToSort[currentItemIndex+1] = listToSort[currentItemIndex+1].copy(isCurrentlyCompared = true)

                if(sortInfo.shouldSwap){
                    val firstItem = listToSort[currentItemIndex].copy(isCurrentlyCompared = false)
                    listToSort[currentItemIndex] = listToSort[currentItemIndex+1].copy(isCurrentlyCompared = false)
                    listToSort[currentItemIndex+1] = firstItem
                }

                if(sortInfo.hadNoEffect){
                    listToSort[currentItemIndex] = listToSort[currentItemIndex].copy(isCurrentlyCompared = false)

                    listToSort[currentItemIndex+1] = listToSort[currentItemIndex+1].copy(isCurrentlyCompared = false)
                }
            }
        }
    }

    fun typeWritingCompleted(){

        viewModelScope.launch {
            delay(5000)
            _bubbleSortUiState.update {
                BubbleSortUiState(
                    isSortingCompleted = false
                )
            }
            resetListValues()
        }
    }
    fun resetListValues(){
        listToSort.removeAll(listToSort)
        for(i in 0..3){
            listToSort.add(
                ListUiState(
                    id = i,
                    false,
                    value = (0..150).random(),
                    color = Color(
                        (0..143).random(),
                        (0..155).random(),
                        (0..200).random()
                    )
                )
            )
        }
    }
    fun algorithmChange(algorithms: Algorithms){
        _bubbleSortUiState.update {
            BubbleSortUiState(
                isSortingCompleted = false,
                algorithm = algorithms
            )
        }
    }
}
