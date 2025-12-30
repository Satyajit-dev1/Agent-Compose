package com.rupeek.agentapp.presentation.customDrawer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

/**
 * Created by Satyajit on 23/12/25
 **/
@Composable
fun InitialAvatar(
    modifier: Modifier = Modifier,
    name: String,
    size: Dp
) {

    val initials = remember(name) {
        name.split(" ")
            .filter { it.isNotBlank() }
            .take(2)
            .joinToString(""){it.first().uppercase()}
    }

    val bgColor = remember(name) {
        val colors = listOf(
            Color(0xFFEF5350),
            Color(0xFFAB47BC),
            Color(0xFF5C6BC0),
            Color(0xFF29B6F6),
            Color(0xFF66BB6A),
            Color(0xFFFFCA28),
            Color(0xFFFF7043)
        )
        colors[kotlin.math.abs(name.hashCode()) % colors.size]
    }

    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(bgColor),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = initials,
            color = Color.White,
            fontSize = (size.value/2).sp,
            fontWeight = FontWeight.Bold
        )
    }

}