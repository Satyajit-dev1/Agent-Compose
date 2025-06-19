package com.rupeek.agentapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.rupeek.agentapp.presentation.screens.transaction.TransactionScreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val tabItems = listOf(
        TabItem(title = "Transaction"),
        TabItem(title = "Collateral")
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(
        selectedTabIndex
    ) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress){
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = modifier
    ) {
        TabRow(
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(99.dp)),
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.LightGray,
            divider = {},
            indicator = { tabPositions ->
                val tabPosition = tabPositions[selectedTabIndex]
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPosition)
                        .fillMaxHeight()
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(99.dp))
                        .background(MaterialTheme.colorScheme.primary)
                )
            }
        ) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier
                        .zIndex(1f)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(99.dp))
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (index == selectedTabIndex) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        ) { index ->
            when (index) {
                0 -> {
                    TransactionScreen()
                }

                1 -> {
                    CollateralScreen()
                }
            }
        }
    }



}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

data class TabItem(
    val title: String,
)