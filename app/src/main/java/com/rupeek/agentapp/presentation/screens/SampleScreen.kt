package com.rupeek.agentapp.presentation.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class RequestState {
    PENDING, SUCCESS, ERROR
}

data class RequestData(
    val id: String = "#APR-2024-0123",
    val type: String = "Part Release Jewel",
    val submittedDate: String = "Jan 15, 2024",
    val submittedTime: String = "Jan 15, 2024 - 09:45 AM",
    val rejectionReason: String = "The submitted documents are incomplete. Please ensure all required fields are filled and supporting documents are properly attached."
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleScreen(modifier: Modifier = Modifier) {
    var currentState by remember { mutableStateOf(RequestState.PENDING) }
    val requestData = RequestData()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = "Status",
                        fontSize = 20.sp
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search"
                    )
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "search"
                    )
                },
                navigationIcon ={
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back")
                },
                backgroundColor = Color(0xFFF8F9FA)
            )
        },
        bottomBar = {
            ActionButtons(state = currentState)
        }
    ) { contentPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // Status Card
            StatusCard(state = currentState, rejectionReason = requestData.rejectionReason)

            // Request Timeline
            RequestTimeline(state = currentState, requestData = requestData)

            // Request Details
            RequestDetails(requestData = requestData)
        }
    }
}

@Composable
fun StatusCard(state: RequestState, rejectionReason: String) {
    val (backgroundColor, iconColor, icon, title, subtitle, description) = when (state) {
        RequestState.PENDING -> StatusCardData(
            backgroundColor = Color(0xFFFDF2E9),
            iconColor = Color(0xFFE67E22),
            icon = Icons.Filled.Info,
            title = "Pending Approval",
            subtitle = "Under Review",
            description = "Estimated wait time: 24-48 hours"
        )

        RequestState.SUCCESS -> StatusCardData(
            backgroundColor = Color(0xFFE8F5E8),
            iconColor = Color(0xFF4CAF50),
            icon = Icons.Filled.CheckCircle,
            title = "Request Approved",
            subtitle = "Completed",
            description = "Your request has been successfully approved"
        )

        RequestState.ERROR -> StatusCardData(
            backgroundColor = Color(0xFFFFEBEE),
            iconColor = Color(0xFFF44336),
            icon = Icons.Filled.Close,
            title = "Approval Rejected",
            subtitle = "Rejected",
            description = "Your request has been rejected"
        )
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            var animationTrigger by remember { mutableStateOf(0) }

            LaunchedEffect(state) {
                animationTrigger++
            }

            val scale by animateFloatAsState(
                targetValue = if (animationTrigger > 0) 1f else 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                label = "icon_scale"
            )

            val rotation by animateFloatAsState(
                targetValue = when (state) {
                    RequestState.SUCCESS -> if (animationTrigger > 0) 360f else 0f
                    RequestState.ERROR -> if (animationTrigger > 0) 180f else 0f
                    RequestState.PENDING -> if (animationTrigger > 0) 720f else 0f
                },
                animationSpec = tween(durationMillis = 800),
                label = "icon_rotation"
            )

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier
                        .size(28.dp)
                        .scale(scale)
                        .rotate(rotation)
                )
            }

            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Color.Black)
                )
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            if (state == RequestState.ERROR) {
                Text(
                    text = "Rejection Reason:",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = rejectionReason,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            } else {
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun RequestTimeline(state: RequestState, requestData: RequestData) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Request Timeline",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TimelineItem(
                title = "Request Submitted",
                subtitle = requestData.submittedTime,
                isCompleted = true,
                isCurrent = false
            )

            when (state) {
                RequestState.PENDING -> {
                    TimelineItem(
                        title = "Under Review",
                        subtitle = "Current Status",
                        isCompleted = false,
                        isCurrent = true
                    )
                    TimelineItem(
                        title = "Final Approval",
                        subtitle = "Pending",
                        isCompleted = false,
                        isCurrent = false
                    )
                }

                RequestState.SUCCESS -> {
                    TimelineItem(
                        title = "Under Review",
                        subtitle = "Completed",
                        isCompleted = true,
                        isCurrent = false
                    )
                    TimelineItem(
                        title = "Final Approval",
                        subtitle = "Approved",
                        isCompleted = true,
                        isCurrent = true
                    )
                }

                RequestState.ERROR -> {
                    TimelineItem(
                        title = "Review Completed",
                        subtitle = "Jan 16, 2024 - 02:30 PM",
                        isCompleted = true,
                        isCurrent = false
                    )
                    TimelineItem(
                        title = "Request Rejected",
                        subtitle = "Jan 16, 2024 - 02:30 PM",
                        isCompleted = false,
                        isCurrent = true,
                        isError = true
                    )
                }
            }
        }
    }
}

@Composable
fun TimelineItem(
    title: String,
    subtitle: String,
    isCompleted: Boolean,
    isCurrent: Boolean,
    isError: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(
                    when {
                        isError -> Color(0xFFF44336)
                        isCompleted -> Color(0xFF4CAF50)
                        isCurrent -> Color.Black
                        else -> Color.Gray.copy(alpha = 0.3f)
                    }
                )
        )

        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Text(
                text = subtitle,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun RequestDetails(requestData: RequestData) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Request Details",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DetailRow("Request ID", requestData.id)
            DetailRow("Type", requestData.type)
            DetailRow("Submitted", requestData.submittedDate)
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ActionButtons(state: RequestState) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedButton(
            onClick = { },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = if (state == RequestState.ERROR) "Contact Support" else "Contact Support",
                color = Color.Black
            )
        }

        Button(
            onClick = { },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE67E22)
            )
        ) {
            Text(
                text = when (state) {
                    RequestState.PENDING -> "View Details"
                    RequestState.SUCCESS -> "Download"
                    RequestState.ERROR -> "Resubmit Request"
                },
                color = Color.White
            )
        }
    }
}

data class StatusCardData(
    val backgroundColor: Color,
    val iconColor: Color,
    val icon: ImageVector,
    val title: String,
    val subtitle: String,
    val description: String
)

@Preview(showBackground = true)
@Composable
private fun SampleScreenPreview() {
    SampleScreen()
}