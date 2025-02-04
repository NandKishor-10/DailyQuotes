package com.nandkishor.dailyquotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HistoryScreen(viewModel: QuoteViewModel, navController: NavController) {

    val quoteListState = viewModel.quoteList.collectAsState()
    val quoteList = quoteListState.value

    Scaffold(
        topBar = { AppBar(navController, showBackButton = true) },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) {innerPadding->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(quoteList) {
                Spacer(modifier = Modifier.height(5.dp))
                QuoteCard(
                    QuoteResponseItem(
                        it.id,
                        it.author,
                        it.content,
                        it.dateAdded,
                        it.tags
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePrev() {
    HistoryScreen(
        QuoteViewModel(QuoteRepository()),
        rememberNavController()
    )
}