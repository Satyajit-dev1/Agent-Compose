package com.rupeek.agentapp.presentation.screens.transaction.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rupeek.agentapp.utils.FilterType

@Composable
fun FilterChipGroupWithList(
    modifier: Modifier = Modifier
) {

    var selectedFilter by remember { mutableStateOf(FilterType.ALL) }

    val allItem = listOf(
        "Fresh Item 1", "Today Item", "Repeat Item", "Fresh Item 2", "General Item"
    )

    val filteredItems = when (selectedFilter) {
        FilterType.ALL -> allItem
        FilterType.TODAY -> allItem.filter { it.contains("Today") }
        FilterType.FRESH -> allItem.filter { it.contains("Fresh") }
        FilterType.REPEAT -> allItem.filter { it.contains("Repeat") }
    }

    Column(
        modifier = modifier
    ) {
        FilterChipGroup(
            selectedFilter = selectedFilter,
            onFilterSelected = {
                selectedFilter = it
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(filteredItems){ item ->
                Text(text = item, modifier = Modifier.padding(8.dp))
            }
        }
    }
}