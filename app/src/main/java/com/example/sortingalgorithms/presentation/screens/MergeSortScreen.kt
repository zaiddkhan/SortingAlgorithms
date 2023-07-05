package com.example.sortingalgorithms.presentation.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sortingalgorithms.domain.model.MergeSortUiState

@Composable
fun MergeSortScreen(
    startSorting : () -> Unit,
    sortInfoUiItemList : MutableList<MergeSortUiState>,
    listToSort : MutableList<Int>
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            itemsIndexed(
                sortInfoUiItemList,
                key = { _, it ->
                    it.id
                }
            ){ index, it ->
                val depthParts = it.sortParts
                if(index == 0){
                    Text(
                        "Dividing",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 26.sp,
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
                if(index == 4){
                    Text(
                        "Merging",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 26.sp,
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ){
                    for(part in depthParts){
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                            modifier = Modifier
                                .padding(start = if (depthParts.indexOf(part) == 0) 0.dp else 17.dp)
                                .background(it.color, RoundedCornerShape(10.dp))
                                .padding(5.dp)

                        ){
                            for(numberInformation in part){
                                if (part.indexOf(numberInformation) != part.size-1){
                                    Text(
                                        "$numberInformation |",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 19.sp
                                    )
                                }else{
                                    Text(
                                        "$numberInformation",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 19.sp
                                    )
                                }

                            }
                        }
                    }
                }

            }
        }

    }
}
