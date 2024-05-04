package app.grapheneos.info

import android.net.http.HttpResponseCache
import android.util.Log
import app.grapheneos.info.ui.releasenotes.TAG
import java.io.File
import java.io.IOException

class Application : android.app.Application() {
    override fun onCreate() {
        super.onCreate()

        try {
            val httpCacheDir = File(applicationContext.cacheDir, "http")
            val httpCacheSize = (10 * 1024 * 1024).toLong() // 10 MiB
            HttpResponseCache.install(httpCacheDir, httpCacheSize)
        } catch (e: IOException) {
            Log.e(TAG, "HTTP response cache installation failed", e)
        }
    }
}