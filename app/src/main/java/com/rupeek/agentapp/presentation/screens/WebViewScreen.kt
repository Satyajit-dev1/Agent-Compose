package com.rupeek.agentapp.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen() {
    val mUrl =
        "https://digitalkycunified.digitap.work/?unified_transaction_id=1a416b88-3854-48ec-82c4-53e67fcff34c&token=eyJhbGciOiJSUzI1NiJ9.eyJhY2Nlc3NfdG9rZW4iOiI3ZDAzZmRhZi02ZGE3LTRmMDQtYWQzYy1iMGNjN2I4NGY2NzYiLCJpc3MiOiJzZWxmIiwic3ViIjoiMWE0MTZiODgtMzg1NC00OGVjLTgyYzQtNTNlNjdmY2ZmMzRjIiwiZXhwIjoxNzU0NTc5NzExLCJpYXQiOjE3NTQ0OTMzMTF9.wb3PDTrLeAwS4R76pbxqmgcJrt0EeLFhVQbyal25Iz9WfeUZz1r2uoAKQRzB9AoR2CHmc6ZkQR4Jnl4XtJ0zRyC3XESXpXkA7zMHIkWXlG1_qfl3P1cSS3Cornvy2g4MljpCLC2VwT8dpdqaZtqP5vvhKq2dcO16D9pBYXG1pqP4nwZ0o3qSbkeMsfocqP87yC6TXRA5kTCLBJ3ZtzuLTwi-jZYk03fHlDQ7MhQl6wA3WsYZ5C-t6BaubMP9hcbs7ucDRVXam8bBB8UU0-D_8bC-CB6Ipg-h6jzpzZG-1Mu9HUqINFTlKoatbMK2yGG6HDJrSDvYVf3prSByKmeS4w"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Web View")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                },
            )
        }
    ) { contentPadding ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.loadsImagesAutomatically = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    settings.javaScriptCanOpenWindowsAutomatically = true
                    settings.allowFileAccess = true
                    settings.allowContentAccess = true
                    webChromeClient = WebChromeClient()

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                            Log.d("WebPage", "Webview started loading: ${url}")
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            Log.d("WebPage", "Webview finished: ${url}")
                        }
                    }
                }
            }, update = { webview ->
                webview.loadUrl(mUrl)
            }
        )
    }


}