package com.nandkishor.dailyquotes

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController, applicationContext: Context) {
    val context = LocalContext.current
    var notificationEnabled by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = "My App",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                    contentDescription = "Navigation Icon",
                    modifier = Modifier.size(36.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                val text = if (notificationEnabled) "Daily Notifications Disabled" else "Daily Notifications Enabled"
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
            IconButton(onClick = {
                    Toast.makeText(context, "Not Implemented", Toast.LENGTH_SHORT).show()
            }) {
                Icon(
                    painter = painterResource(R.drawable.history),
                    contentDescription = "Settings",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
    )
}


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

    var currentDiceIndex by remember { mutableStateOf(0) }


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

@Composable
fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.size(48.dp),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 3.dp
    )
}