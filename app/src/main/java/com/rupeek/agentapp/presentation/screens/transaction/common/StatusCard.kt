package com.rupeek.agentapp.presentation.screens.transaction.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rupeek.agentapp.presentation.screens.transaction.data.StatusItem
import com.rupeek.agentapp.ui.theme.correctLight


@Composable
fun StatusCard(
    modifier: Modifier = Modifier,
    statusItem: StatusItem
) {

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            5.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {

            Text(
                text = statusItem.title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = statusItem.numberOfItem,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = statusItem.status,
                style = MaterialTheme.typography.titleSmall,
                color = correctLight
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun StatusCardPreview() {
    StatusCard(
        modifier = Modifier.width(120.dp)
            .padding(4.dp),
        statusItem = StatusItem(
            title = "Active",
            numberOfItem = "12",
            status = "+2 Today"
        )
    )
}