package com.nandkishor.dailyquotes

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navController: NavController,
    showBackButton: Boolean = false,
    showNotificationButton: Boolean = false,
    showHistoryButton: Boolean = false
) {
    val context = LocalContext.current
    var notificationEnabled by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = "Daily Quotes",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
            )
        },
        navigationIcon =  {
            if (showBackButton) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "Navigation Icon",
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        },
        actions = {
            if (showNotificationButton) {
                IconButton(onClick = {
                    val text =
                        if (notificationEnabled) "Daily Notifications Disabled" else "Daily Notifications Enabled"
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    notificationEnabled = !notificationEnabled
                }) {
                    Icon(
                        painter = painterResource(
                            if (notificationEnabled) R.drawable.bell_ring
                            else R.drawable.bell_off
                        ),
                        contentDescription = "Notifications",
                    )
                }
            }
            if (showHistoryButton) {
                IconButton(onClick = {
                    navController.navigate(HistoryScreen)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.history),
                        contentDescription = "Settings",
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
    )
}


@SuppressLint("NewApi")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DiceFAB(
    viewModel: QuoteViewModel,
) {
    val diceIcons = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6,
    )

    var currentDiceIndex by remember { mutableIntStateOf(0) }


    FloatingActionButton(
        onClick = {
            Log.i("Log FAB", "Clicked")
            viewModel.loadQuote()
            Log.i("Log FAB", "Success")
            currentDiceIndex = (0..5).random()
        },
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    ) {
        AnimatedContent(
            targetState = currentDiceIndex,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) + scaleIn() togetherWith fadeOut(animationSpec = tween(300))
            }, label = ""
        ) { targetIndex ->
            Icon(
                painter = painterResource(id = diceIcons[targetIndex]),
                contentDescription = "Dice ${targetIndex + 1}",
                modifier = Modifier.size(35.dp)
            )
        }
    }
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QuoteCard(quote: QuoteResponseItem, modifier: Modifier = Modifier) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .padding(8.dp)
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(R.drawable.quote),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                alpha = .25f,
                alignment = Alignment.TopEnd,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = quote.author,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "\"${quote.content}\"",
                    style = MaterialTheme.typography.bodyLarge,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    quote.tags.forEach {
                        Tags(it)
                    }
                }
            }
            Text(
                "-${quote.dateAdded}",
                style = MaterialTheme.typography.bodySmall.copy(fontFamily = FontFamily.Monospace),
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .alpha(.75f)
            )
        }
    }
}

@Composable
fun Tags(tag: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 2.5.dp)
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = tag,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.size(48.dp),
        color = MaterialTheme.colorScheme.primary
    )
}