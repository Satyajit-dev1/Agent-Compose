package com.rupeek.agentapp.presentation.customDrawer.domain.models

import com.rupeek.agentapp.R

enum class NavigationItem(
    val title: String,
    val icon: Int
){

    Home(
        title = "Home",
        icon = R.drawable.home
    ),

    VerifyAsset(
        title = "Verify Asset",
        icon = R.drawable.verified_check
    ),

    PrintQueue(
        title = "Print Queue",
        icon = R.drawable.print_queue
    ),

    MoneyRelease(
        title = "Release Retention",
        icon = R.drawable.money_release
    ),

    SmartDNA(
        title = "Smart DNA",
        icon = R.drawable.qrcode_solid
    ),

    Notification(
        title = "Notification Center",
        icon = R.drawable.notification
    ),

    Settings(
        title = "Settings",
        icon = R.drawable.settings
    ),

    Logout(
        title = "Logout",
        icon = R.drawable.logout
    ),
}
