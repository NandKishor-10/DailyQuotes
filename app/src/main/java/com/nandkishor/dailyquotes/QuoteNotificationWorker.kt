package com.nandkishor.dailyquotes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

//class QuoteNotificationWorker(
//    context: Context, params: WorkerParameters,
//    private val quoteRepository: QuoteRepository
//): CoroutineWorker(context, params) {
//
//    override suspend fun doWork(): Result {
//        val quote = quoteRepository.fetchQuoteOfTheDay()
//
//        sendNotification(
//            author = quote.author,
//            content = quote.content
//        )
//
//        return Result.success()
//    }
//
//    private fun sendNotification(author: String, content: String) {
//
//        val notificationManager =
//            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        val channelID = "Normal"
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            val channel =
//                NotificationChannel(channelID, "Stander", NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val notification = NotificationCompat.Builder(applicationContext, channelID)
//            .setContentTitle(author)
//            .setContentText(content)
//            .setSmallIcon(R.drawable.quote)
//            .build()
//        notificationManager.notify(1, notification)
//
//    }
//}

fun sendNotification(author: String, content: String, applicationContext: Context) {

    val notificationManager =
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val channelID = "Normal"

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val channel =
            NotificationChannel(channelID, "Stander", NotificationManager.IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(channel)
    }

    val notification = NotificationCompat.Builder(applicationContext, channelID)
        .setSmallIcon(R.drawable.quote)
        .setContentTitle(author)
        .setContentText(content)
        .setStyle(NotificationCompat.BigTextStyle().bigText(content))
        .build()
    notificationManager.notify(1, notification)

}