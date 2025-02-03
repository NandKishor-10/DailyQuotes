package com.nandkishor.dailyquotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item{
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.primary)){
                Text("primary")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.onPrimary)){
                Text("onPrimary")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ){
                Text("primaryContainer")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
            ){
                Text("onPrimaryContainer")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.secondary)){
                Text("secondary")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.onSecondary)){
                Text("onSecondary")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            ){
                Text("secondaryContainer")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.onSecondaryContainer)
            ){
                Text("onSecondaryContainer")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.tertiary)){
                Text("tertiary")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.onTertiary)){
                Text("onTertiary")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            ){
                Text("tertiaryContainer")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.onTertiaryContainer)
            ){
                Text("onTertiaryContainer")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.surface)){
                Text("Surface")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ){
                Text("surfaceVariant")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
            ){
                Text("surfaceContainer")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.onSurface)){
                Text("onSurface")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.scrim)){
                Text("scrim")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.inversePrimary)
            ){
                Text("inversePrimary")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.inverseSurface)
            ){
                Text("inverseSurface")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.inverseOnSurface)
            ){
                Text("inverseOnSurface")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.outline)){
                Text("outline")
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.outlineVariant)
            ){
                Text("outlineVariant")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.surfaceDim)){
                Text("surfaceDim")
            }
            Box(modifier = Modifier
                .size(150.dp)
                .background(MaterialTheme.colorScheme.surfaceTint)){
                Text("surfaceTint")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomePrev() {
    HomeScreen(rememberNavController())
}