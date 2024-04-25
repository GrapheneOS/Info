package app.grapheneos.info

import android.net.http.HttpResponseCache
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.grapheneos.info.ui.releasenotes.TAG
import app.grapheneos.info.ui.theme.InfoTheme
import java.io.File
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        try {
            val httpCacheDir = File(application.cacheDir, "http")
            val httpCacheSize = (10 * 1024 * 1024).toLong() // 10 MiB
            HttpResponseCache.install(httpCacheDir, httpCacheSize)
        } catch (e: IOException) {
            Log.e(TAG, "HTTP response cache installation failed", e)
        }

        setContent {
            InfoTheme {
                InfoApp()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val cache = HttpResponseCache.getInstalled()
        cache?.flush()
    }
}
