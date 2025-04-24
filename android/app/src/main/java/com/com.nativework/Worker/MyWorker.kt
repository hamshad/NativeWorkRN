package com.com.nativework

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
    override fun doWork(): Result {

        Handler(Looper.getMainLooper()).post {
            Log.d("MyWorker", "Background work is running")
            Toast.makeText(this.applicationContext, "Work Manager Running", Toast.LENGTH_SHORT).show()
        }

        runBlocking {
            delay(3000)
        }


        Handler(Looper.getMainLooper()).post {
            Log.d("MyWorker", "Background work is ending")
            Toast.makeText(this.applicationContext, "Work Manager Ending!", Toast.LENGTH_SHORT).show()
        }

        return Result.success()
    }
}
