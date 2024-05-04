package app.grapheneos.info

import android.net.http.HttpResponseCache
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import app.grapheneos.info.ui.theme.InfoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            InfoTheme {
                InfoApp()
            }
        }
    }

    private val cacheFlushCoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onStop() {
        super.onStop()

        cacheFlushCoroutineScope.launch {
            val cache = HttpResponseCache.getInstalled()
            cache?.flush()
        }
    }
}
