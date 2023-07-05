package com.example.sortingalgorithms.domain

import com.example.sortingalgorithms.domain.model.SortInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BubbleSortAlgo {

    operator fun invoke(list: MutableList<Int>,sortingCompleted : () -> Unit) : Flow<SortInfo> {
        return flow {
            var listSizeToCompare = list.size-1

            while(listSizeToCompare > 1){

                var innerIterator = 0
                while (innerIterator<listSizeToCompare){

                    val currentListItem = list[innerIterator]
                    val nextItem = list[innerIterator+1]
                    emit(
                        SortInfo(
                            innerIterator,shouldSwap = false,hadNoEffect = false
                        )
                    )
                    delay(500)
                    if(currentListItem > nextItem){
                        list.swap(innerIterator,innerIterator+1)
                        emit(
                            SortInfo(
                                innerIterator,shouldSwap = true,hadNoEffect = false
                            )
                        )
                    }else{
                        emit(
                            SortInfo(
                                innerIterator,shouldSwap = false,hadNoEffect = true
                            )
                        )
                    }
                    delay(500)
                    innerIterator += 1
                }

                listSizeToCompare -= 1
            }
            sortingCompleted()
        }
    }
}
fun <T> MutableList<T>.swap(firstIndex : Int,secondIndex:Int){
    val temp = this[firstIndex]
    this[firstIndex] = this[secondIndex]
    this[secondIndex] = temp
}