package com.greek.kino.view.liveResult

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Suppress("ktlint:standard:function-naming")
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun GreekKinoWebView(url: String) {
    AndroidView(
        modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.cacheMode = WebSettings.LOAD_NO_CACHE
                settings.domStorageEnabled = true
                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        },
    )
}
