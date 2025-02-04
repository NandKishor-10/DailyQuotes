package com.nandkishor.dailyquotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.nandkishor.dailyquotes.ui.theme.DailyQuotesTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        scheduleDailyNotification(this)
        val viewModel = QuoteViewModel(quoteRepository = QuoteRepository())
        setContent {
            DailyQuotesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(viewModel, this)
                }
            }
        }
    }
}
