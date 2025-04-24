package com.com.nativework.Location

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.util.concurrent.TimeUnit

class LocationWorkManagerModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String = "LocationWorkManagerModule"

    @ReactMethod
    fun startLocationLogging() {
        val workRequest = PeriodicWorkRequestBuilder<LocationWorker>(5, TimeUnit.SECONDS)
            .setConstraints(Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            ).build()

        WorkManager.getInstance(reactApplicationContext).enqueueUniquePeriodicWork(
            "LocationLogger",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
    }
}