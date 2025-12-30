package com.rupeek.agentapp.presentation.customDrawer.component

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rupeek.agentapp.presentation.customDrawer.domain.models.NavigationItem

@Composable
fun CustomDrawer(
    modifier: Modifier = Modifier,
    selectedNavigationItem: NavigationItem,
    maxWidth: Float = 0.6f,
    onNavigationItemClick: (NavigationItem) -> Unit,
    onCloseClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = maxWidth)
            .padding(horizontal = 12.dp)
            .testTag("drawer"),
        horizontalAlignment = Alignment.Start
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ) {
            IconButton(
                onClick = onCloseClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Arrow back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            InitialAvatar(
                modifier = Modifier.padding(end = 4.dp),
                size = 70.dp,
                name = "Satyajit Biswal",
            )
            Column(
                modifier = Modifier.padding(4.dp),
            ) {
                Text(
                    modifier = Modifier.basicMarquee(),
                    text = "Satyajit Biswal",
                    maxLines = 2,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Loan Manager",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

//        Spacer(Modifier.height(12.dp))
//
//        Row(
//            modifier = Modifier.padding(horizontal = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = "Today's Transaction",
//                    style = MaterialTheme.typography.titleSmall,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    fontWeight = FontWeight.Bold
//                )
//                Spacer(Modifier.height(4.dp))
//                Text(
//                    text = "12",
//                    style = MaterialTheme.typography.titleLarge,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = "Completion Rate",
//                    style = MaterialTheme.typography.titleSmall,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    fontWeight = FontWeight.Bold
//                )
//                Spacer(Modifier.height(4.dp))
//                Text(
//                    text = "96%",
//                    style = MaterialTheme.typography.titleLarge,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    fontWeight = FontWeight.Bold
//                )
//            }
//        }

        Spacer(Modifier.height(36.dp))

        NavigationItem.entries.toTypedArray().take(4).forEach { navigationItem ->
            NavigationItemView(
                navigationItem = navigationItem,
                selected = navigationItem == selectedNavigationItem,
                onClick = {
                    onNavigationItemClick(navigationItem)
                }
            )
            Spacer(Modifier.height(4.dp))
        }

        Spacer(Modifier.weight(1f))

        NavigationItem.entries.toTypedArray().takeLast(3).forEach { navigationItem ->
            NavigationItemView(
                navigationItem = navigationItem,
                selected = navigationItem == selectedNavigationItem,
                onClick = {
                    when(navigationItem){
                        NavigationItem.Settings ->{
                            onNavigationItemClick(NavigationItem.Settings)
                        }
                        NavigationItem.Notification ->{
                            onNavigationItemClick(NavigationItem.Notification)
                        }
                        else -> {
                            onNavigationItemClick(NavigationItem.Logout)
                        }
                    }
                }
            )
            Spacer(Modifier.height(4.dp))
        }
        
        Spacer(Modifier.height(8.dp))

        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp),
            text = "Version 1.0.0",
            textAlign = TextAlign.Center,
        )
    }

    
}

@Preview(showBackground = true)
@Composable
private fun CustomDrawerpreview() {
    CustomDrawer(
        maxWidth = 1f,
        selectedNavigationItem = NavigationItem.VerifyAsset,
        onNavigationItemClick = {},
        onCloseClick = {}
    )
}