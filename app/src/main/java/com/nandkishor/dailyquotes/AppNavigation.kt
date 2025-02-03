package com.nandkishor.dailyquotes

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(viewModel: QuoteViewModel, applicationContext: Context) {
    val navController = rememberNavController()

    Log.i("Log AppNavigation()", "Inside of AppNavigation()")

    NavHost(navController, startDestination = QuoteListScreen) {
        Log.i("Log AppNavigation()", "NavHost()")

        composable<HomeScreen> {
            HomeScreen(navController)
        }

        composable<QuoteListScreen> {
            Log.i("Log AppNavigation()", "QuoteListScreen()")
            val quote by viewModel.quote.collectAsState()
            QuoteListScreen(quote, navController, applicationContext, viewModel)
        }
    }
}

@Serializable
object HomeScreen

@Serializable
object QuoteListScreen