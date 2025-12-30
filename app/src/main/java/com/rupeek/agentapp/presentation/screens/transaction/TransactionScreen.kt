package com.rupeek.agentapp.presentation.screens.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rupeek.agentapp.presentation.screens.transaction.common.FilterChipGroupWithList
import com.rupeek.agentapp.presentation.screens.transaction.common.StatusCard
import com.rupeek.agentapp.presentation.screens.transaction.data.StatusItem

@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier
) {

    val statusItem = listOf(
        StatusItem(
            title = "Active",
            numberOfItem = "12",
            status = "+2 Today"
        ),
        StatusItem(
            title = "Completed",
            numberOfItem = "8",
            status = "+2 Today"
        ),
        StatusItem(
            title = "Pending",
            numberOfItem = "4",
            status = "-1 Today"
        ),
        StatusItem(
            title = "Avg Time",
            numberOfItem = "42min",
            status = "-5 Min"
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ){
//        LazyRow(
//            modifier = Modifier.fillMaxWidth()
//                .padding(horizontal = 8.dp),
//            state = rememberLazyListState(),
//            contentPadding = PaddingValues(4.dp)
//        ) {
//            items(statusItem.size) { index ->
//                statusItem[index].let { statusItem ->
//                    StatusCard(
//                        modifier = Modifier.width(150.dp)
//                            .padding(horizontal = 8.dp),
//                        statusItem = statusItem
//                    )
//                }
//            }
//        }

        FilterChipGroupWithList(
            modifier = Modifier.fillMaxSize()
                .padding(8.dp)
        )

    }

}