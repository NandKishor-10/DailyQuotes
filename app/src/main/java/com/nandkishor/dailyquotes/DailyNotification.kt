package com.nandkishor.dailyquotes

//fun scheduleDailyNotification(context: Context) {
//    val constraints = Constraints.Builder()
//        .setRequiredNetworkType(NetworkType.CONNECTED)
//        .build()
//
//    val workRequest =
//        PeriodicWorkRequestBuilder<QuoteNotificationWorker>(24, HOURS)
//            .setConstraints(constraints)
//            .setInitialDelay(calculateInitialDelay(), MILLISECONDS)
//            .build()
//
//    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
//        "daily_quote_notification",
//        ExistingPeriodicWorkPolicy.REPLACE,
//        workRequest
//    )
//}

//fun calculateInitialDelay(): Long {
//    val currentTime = Calendar.getInstance()
//    val targetTime = currentTime.clone() as Calendar
//    targetTime.set(HOUR_OF_DAY, 6)
//    targetTime.set(MINUTE, 0)
//    targetTime.set(SECOND, 0)
//
//    if (currentTime.after(targetTime)) {
//        targetTime.add(DAY_OF_YEAR, 1)
//    }
//    return targetTime.timeInMillis - currentTime.timeInMillis
//}