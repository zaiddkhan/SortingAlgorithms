package com.example.sortingalgorithms.domain

import com.example.sortingalgorithms.domain.model.MergeSortInfo
import com.example.sortingalgorithms.domain.model.MergeSortState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.UUID

class MergeSortAlgo {

    val sortFlow = MutableSharedFlow<MergeSortInfo>()
    suspend operator fun invoke(list: List<Int>,depth:Int):List<Int>{
        delay(500)
        sortFlow.emit(
            MergeSortInfo(
                UUID.randomUUID().toString(),
                depth = depth,
                sortPart = list,
                sortState = MergeSortState.DIVIDED
            )
        )

        val listSize = list.size
        if(listSize<=1){
            return list
        }
        var leftList = list.slice(0 until (listSize+1)/2)
        var rightList = list.slice((listSize+1)/2 until listSize)
        leftList = this(leftList,depth+1)
        rightList = this(rightList,depth+1)
        return merge(leftList.toMutableList(), rightList.toMutableList(),depth)
    }

    private suspend fun merge(
        leftList : MutableList<Int>,
        rightList : MutableList<Int>,
        depth: Int
    ) : List<Int> {

        val mergeList = mutableListOf<Int>()
        while (leftList.isNotEmpty() && rightList.isNotEmpty()){
            if(leftList.first() <= rightList.first()){
                mergeList.add(mergeList.size,leftList.removeFirst())
            }else{
                mergeList.add(mergeList.size,rightList.removeFirst())
            }
        }
        mergeList.addAll(leftList)
        mergeList.addAll(rightList)
        delay(500)
        sortFlow.emit(
            MergeSortInfo(
                UUID.randomUUID().toString(),
                depth = depth,
                sortPart = mergeList,
                sortState = MergeSortState.MERGED
            )
        )
        return mergeList
    }
}