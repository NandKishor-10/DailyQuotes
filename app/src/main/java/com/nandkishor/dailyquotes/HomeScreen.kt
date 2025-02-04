package com.nandkishor.dailyquotes

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    quote: QuoteResponseItem?,
    navController: NavController,
    applicationContext: Context,
    viewModel: QuoteViewModel
) {
    Scaffold(
        topBar = { AppBar(
            navController,
            showHistoryButton = true,
            showNotificationButton = true
        ) },
        floatingActionButton = { DiceFAB(viewModel) },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface,
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (quote != null)
                QuoteCard(quote, modifier = Modifier.clickable{
                    viewModel.insertQuote(
                        quote.id,
                        quote.author,
                        quote.content,
                        quote.dateAdded,
                        quote.tags
                    )
                })
            else
                Loading()
        }
    }
}


@Preview
@Composable
private fun QuoteCardPreview() {
    val quote = QuoteResponseItem(
        "...",
        "-dev",
//        "Time Pass",
        "Life is a Lie",
        "yesterday",
//        "today",
//        10,
        listOf("Life Lesson", "Black Magic")
    )

    QuoteCard(quote)
}