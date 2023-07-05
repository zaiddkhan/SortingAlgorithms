package com.example.sortingalgorithms.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun TypewriterText(
    typeWritingCompleted : () -> Unit,
    modifier: Modifier = Modifier,
    text: String,
) {
    var textToDisplay by remember {
        mutableStateOf("")
    }

    LaunchedEffect(
        key1 = text,
    ) {
            text.forEachIndexed { charIndex, _ ->
                textToDisplay = text
                    .substring(
                        startIndex = 0,
                        endIndex = charIndex + 1,
                    )
                delay(60)
            }
            delay(1000)
        if(textToDisplay.length == text.length){
            typeWritingCompleted()
        }
    }



   BasicTextField(
       value =  textToDisplay,
       onValueChange = {},
       decorationBox = {innerTextField ->
           Box(modifier = modifier){
               innerTextField()
           }
       },
       textStyle = TextStyle(
           color = Color.White,
           fontWeight = FontWeight.Bold,
           fontSize = 20.sp
       ) ,
   )
}