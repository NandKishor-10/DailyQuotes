package com.nandkishor.dailyquotes

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(viewModel: QuoteViewModel, applicationContext: Context) {
    val navController = rememberNavController()

    Log.i("Log AppNavigation()", "Inside of AppNavigation()")

    NavHost(navController, startDestination = HomeScreen) {
        Log.i("Log AppNavigation()", "NavHost()")

        composable<HomeScreen> {
            Log.i("Log AppNavigation()", "QuoteListScreen()")
            val quote by viewModel.quote.collectAsState()
            HomeScreen(quote, navController, applicationContext, viewModel)
        }

        composable<HistoryScreen> {
            HistoryScreen(viewModel, navController)
        }
    }
}

@Serializable
object HomeScreen

@Serializable
object HistoryScreen