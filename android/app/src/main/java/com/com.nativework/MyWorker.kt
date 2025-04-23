package com.com.nativework

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        Log.d("MyWorker", "Background work is running")

        runBlocking {
            delay(3000)
        }

        
        Log.d("MyWorker", "Background work is ending")

        return Result.success()
    }
}