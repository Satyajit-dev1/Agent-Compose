package com.rupeek.agentapp.presentation.screens.transaction.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rupeek.agentapp.R
import com.rupeek.agentapp.utils.FilterType

@Composable
fun FilterChipGroup(
    modifier: Modifier = Modifier,
    selectedFilter: FilterType,
    onFilterSelected: (FilterType) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        FilterType.entries.forEach { filter ->
            val isSelected = filter == selectedFilter

            Surface(
                modifier = Modifier.clickable{
                    onFilterSelected(filter)
                },
                shape = RoundedCornerShape(50),
                color = if(isSelected) Color(0xFFFFF0EC) else Color(0xFFF1F3F6),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    if (isSelected) {
                        Icon(
                            painter = if (filter == FilterType.ALL) painterResource(R.drawable.filter_solid) else painterResource(R.drawable.check_solid),
                            contentDescription = "Selected",
                            tint = Color(0xFFFF5A1F),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text(
                        text = filter.label,
                        color = if (isSelected) Color(0xFFFF5A1F) else Color(0xFF1C1C1E),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

}